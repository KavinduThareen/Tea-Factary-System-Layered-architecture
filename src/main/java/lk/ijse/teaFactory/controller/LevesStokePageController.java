package lk.ijse.teaFactory.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.teaFactory.dao.customer.Impl.LeavesStokeDAOImpl;
import lk.ijse.teaFactory.dao.customer.Impl.SupOrderDAOImpl;
import lk.ijse.teaFactory.dao.customer.LeaveStokeDAO;
import lk.ijse.teaFactory.dao.customer.SupOrdersDAO;
import lk.ijse.teaFactory.dto.*;
import lk.ijse.teaFactory.dto.tm.LeaveStokeTm;
import lk.ijse.teaFactory.model.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public class LevesStokePageController {

    @FXML
    private TextField WeigthTxt;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colEdate;

    @FXML
    private TableColumn<?, ?> colSdate;

    @FXML
    private TableColumn<?, ?> colWeigth;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private DatePicker eDateTxt;

    @FXML
    private TextField idTxt;

    @FXML
    private AnchorPane root;

    @FXML
    private DatePicker sDateTxt;

    @FXML
    private ComboBox<String > supplingidTxt;
    LeaveStokeDAO leaveStokeDAO = new LeavesStokeDAOImpl();

    ErrorAnimation errorAnimation = new ErrorAnimation();
    NotificationAnimation notifi = new NotificationAnimation();
    SupOrdersDAO supOrdersDAO = new SupOrderDAOImpl();

    @FXML
    private TableView<LeaveStokeTm> table;

    @FXML
    void addBtnOnAction(ActionEvent event) {

        String sid = supplingidTxt.getValue();
        String id = idTxt.getText();
        String weigth = WeigthTxt.getText();
        Date sDate = Date.valueOf(sDateTxt.getValue());
        Date eDate = Date.valueOf(eDateTxt.getValue());

        var supliDetail = new SupplingDetailModel();
        var dto = new LeavesStokeDto(id,weigth,sDate,eDate);
        boolean isValidated = validate();

        if (isValidated) {
            try {
                boolean isSaved = leaveStokeDAO.save(dto);
                boolean isSaved2 =supOrdersDAO.dropid(sid,weigth);

                boolean a = supliDetail.detail(sid,id,sDate);

                if (isSaved && isSaved2) {
                        notifi.showNotification("saved");
                    loadAll();
                    clearFields();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private boolean validate() {

        String idText = idTxt.getText();
        boolean isIDValidated = Pattern.matches("[L][0-9]{3,}", idText);
        if (!isIDValidated) {
            errorAnimation.animateError(idTxt);
            new Alert(Alert.AlertType.ERROR, "Invalid  ID!").show();
            return false;
        }

        String weigthTxtText = WeigthTxt.getText();
        boolean isAddressValidated = Pattern.matches("\\d+(\\.\\d+)?", weigthTxtText);
        if (!isAddressValidated) {
                errorAnimation.animateError(WeigthTxt);
            new Alert(Alert.AlertType.ERROR, "Invalid weight").show();
            return false;
        }

        return true;
    }

    private void loadSupplingId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<SupOrderDto> supList = supOrdersDAO.getAll();

            for (SupOrderDto supDto : supList) {
                obList.add(supDto.getId());
            }
            supplingidTxt.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {

        }
    }

    public void loadAll(){
        ObservableList<LeaveStokeTm> obList = FXCollections.observableArrayList();

        try {
            List<LeavesStokeDto> dtoList = leaveStokeDAO.getAll();
            for (LeavesStokeDto dto : dtoList){

                JFXButton btnDelete = new JFXButton("Deleted");
                btnDelete.setCursor(javafx.scene.Cursor.HAND);
                btnDelete.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");

                btnDelete.setPrefWidth(100);
                btnDelete.setPrefHeight(30);

                btnDelete .setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                    if(type.orElse(no) == yes) {
                        int selectedIndex = table.getSelectionModel().getSelectedIndex();
                        String id = (String) colid.getCellData(selectedIndex);

                        deleteItem(id);   //delete item from the database

                        obList.remove(selectedIndex);   //delete item from the JFX-Table
                        table.refresh();
                    }
                });
                obList.add(
                        new LeaveStokeTm(
                                dto.getId(),
                                dto.getWeigth(),
                                dto.getSDate(),
                                dto.getEDate(),
                                btnDelete
                        )
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        table.setItems(obList);
    }

    private void deleteItem(String id) {
        try {
            boolean isDeleted = leaveStokeDAO.delete(id);
            if(isDeleted)
                notifi.showNotification("Deleted");
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {

        colid.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        colWeigth.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("weigth"));
        colSdate.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("SDate"));
        colEdate.setCellValueFactory(new PropertyValueFactory<>("EDate"));
        colDelete.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("btnDelete"));

    }

    public void initialize() {
        setCellValueFactory();
        loadAll();
        generateNextId();
        loadSupplingId();
    }

    private void generateNextId() {
        try {
            String orderId = leaveStokeDAO.generateID();
            idTxt.setText(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void clearFields() {
        idTxt.setText("");
        WeigthTxt.setText("");
       // sDateTxt .setValue("");
      //  eDateTxt.setText("");
    }

    @FXML
    void updateOnAction(ActionEvent event) {

        String id = idTxt.getText();
        String weigth = WeigthTxt.getText();
        Date  sDate = Date.valueOf(sDateTxt.getValue());
        Date eDate = Date.valueOf(eDateTxt.getValue());

        var dto = new LeavesStokeDto(id,weigth,sDate,eDate);

        try {
            boolean isUpdated = leaveStokeDAO.update(dto);
            System.out.println(isUpdated);
            if(isUpdated) {
                notifi.showNotification("update");
                loadAll();
             clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void searchOnAction(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            String id = idTxt.getText();

            try {
                LeavesStokeDto leavesStokeDto = leaveStokeDAO.search(id);

                if (leavesStokeDto != null) {
                    idTxt.setText(leavesStokeDto.getId());
                    WeigthTxt.setText(leavesStokeDto.getWeigth());
                    sDateTxt.setValue(leavesStokeDto.getSDate().toLocalDate());
                    eDateTxt.setValue(leavesStokeDto.getEDate().toLocalDate());

                } else {
                    notifi.showNotification("Leavestoke not found");
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/ourStoke.fxml"))));

    }


}
