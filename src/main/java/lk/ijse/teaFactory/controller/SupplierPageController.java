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
import lk.ijse.teaFactory.Entity.Supplier;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.customer.Impl.SupplierDAOImpl;
import lk.ijse.teaFactory.dao.customer.SupplierDAO;
import lk.ijse.teaFactory.dto.ErrorAnimation;
import lk.ijse.teaFactory.dto.NotificationAnimation;
import lk.ijse.teaFactory.dto.SupplierDto;
import lk.ijse.teaFactory.dto.tm.SupplierTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import static lk.ijse.teaFactory.dao.DAOFactory.DAOType.SUPPLIER;

public class SupplierPageController {

    @FXML
    private TextField Address;

    @FXML
    private TextField Contac;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SupplierTm> tbl;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCantac;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> cold;

    @FXML
    private TableColumn<?, ?> coldelte;

    ErrorAnimation errora = new ErrorAnimation();
    NotificationAnimation notifi = new NotificationAnimation();

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(SUPPLIER);

    @FXML
    void addSDetailOnAction(ActionEvent event) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/supplier_orders.fxml"))));

    }

    @FXML
    void addOnAction(ActionEvent event) {
        String id = idTxt.getText();
        String name = nameTxt.getText();
        String address = Address.getText();
        String contac= Contac.getText();

        var entity = new Supplier(id,name,address,contac);
        boolean isValidated = validate();

        if (isValidated) {
            try {
                boolean isSaved = supplierDAO.save(entity);
                if (isSaved) {
                     notifi.showNotification("Saved");
                    loadAll();
                    tbl.refresh();
                    clearFields();
                }
                else{
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    private boolean validate() {

        String idText = idTxt.getText();
        boolean isIDValidated = Pattern.matches("[S][0-9]{3,}", idText);
        if (!isIDValidated) {
            errora.animateError(idTxt);
            new Alert(Alert.AlertType.ERROR, "Invalid ID!").show();
            return false;
        }

        String nameText = nameTxt.getText();
        boolean isNameValidated = Pattern.matches("[A-Za-z]{3,}", nameText);
        if (!isNameValidated) {
            errora.animateError(nameTxt);
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            return false;
        }
        String cantacText = Contac.getText();
        boolean isCantacValidated = Pattern.matches("[0-9]{10}", cantacText);
        if (!isCantacValidated) {
            errora.animateError(Contac);
            new Alert(Alert.AlertType.ERROR, "Invalid contac").show();
            return false;
        }

        String addressText = Address.getText();
        boolean isAddressValidated = Pattern.matches("[A-Za-z0-9/.\\s]{3,}", addressText);
        if (!isAddressValidated) {
            errora.animateError(Address);
            new Alert(Alert.AlertType.ERROR, "Invalid address").show();
            return false;
        }
        return true;

    }


    private void generateNextSupId() {
        try {
            String orderId = supplierDAO.generateID();
            idTxt.setText(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAll(){

        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();
    try {
        List<Supplier> dtoList = supplierDAO.getAll();
        for (Supplier dto : dtoList){


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
                    int selectedIndex = tbl.getSelectionModel().getSelectedIndex();
                    String id = (String) cold.getCellData(selectedIndex);

                    deleteItem(id);   //delete item from the database

                    obList.remove(selectedIndex);//delete item from the JFX-Table
                    tbl.refresh();
                }
            });

            obList.add(
                    new SupplierTm(
                            dto.getId(),
                            dto.getName(),
                            dto.getAddress(),
                            dto.getContac(),
                            btnDelete
                    )
            );

        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
        tbl.setItems(obList);
    }

    private void deleteItem(String id) {
        try {
            boolean isDeleted = supplierDAO.delete(id);
            if(isDeleted)
                notifi.showNotification("Delete");
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {

        cold.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("address"));
        colCantac.setCellValueFactory(new PropertyValueFactory<>("contac"));
        coldelte.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("btnDelete"));

    }

    public void initialize() {
        setCellValueFactory();
        loadAll();
        generateNextSupId();
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = idTxt.getText();
        String name = nameTxt.getText();
        String address = Address.getText();
        String contac= Contac.getText();

        var dto = new Supplier(id,name,address,contac);

        try {
            boolean isUpdated = supplierDAO.update(dto);
            System.out.println(isUpdated);
            if(isUpdated) {
                notifi.showNotification("Update");
                tbl.refresh();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearFields() {
        idTxt.setText("");
        nameTxt.setText("");
        Address .setText("");
        Contac.setText("");
    }

    @FXML
    void searchOnAction(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String id = idTxt.getText();

            try {
                Supplier supplier = supplierDAO.search(id);
                if (supplier != null) {
                    idTxt.setText(supplier.getId());
                    nameTxt.setText(supplier.getName());
                    Address.setText(supplier.getAddress());
                    Contac.setText(supplier.getContac());

                } else {
                    notifi.showNotification("Supplier not found");
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
