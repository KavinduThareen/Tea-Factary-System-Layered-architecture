package lk.ijse.teaFactory.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.teaFactory.Entity.Employee;
import lk.ijse.teaFactory.Entity.Salary;
import lk.ijse.teaFactory.bo.BOFactory;
import lk.ijse.teaFactory.bo.custome.PaymentBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.Impl.EmployeDAOImpl;
import lk.ijse.teaFactory.dao.custome.SalaryDAO;
import lk.ijse.teaFactory.db.DbConnection;
import lk.ijse.teaFactory.dto.*;
import lk.ijse.teaFactory.dto.tm.SalaryTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static lk.ijse.teaFactory.bo.BOFactory.BOType.PAYMENT;
import static lk.ijse.teaFactory.dao.DAOFactory.DAOType.SALARY;

public class PaymentController {

    @FXML
    private TableColumn<?, ?> coldate;

    @FXML
    private TableColumn<?, ?> coldelete;

    @FXML
    private TableColumn<?, ?> colempId;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colsCount;

    @FXML
    private TextField countTxt;

    @FXML
    private DatePicker dateTxt;

    @FXML
    private JFXComboBox<String > empIdTxt;

    @FXML
    private TextField idTxt;

    @FXML
    private TableView<SalaryTm> tbl;

    @FXML
    private AnchorPane root;

    ErrorAnimation errora = new ErrorAnimation();
    NotificationAnimation notifi = new NotificationAnimation();
   // SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getDaoFactory().getDAO(SALARY);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(PAYMENT);

    @FXML
    void addOnAction(ActionEvent event) {
        String id = idTxt.getText();
        String empId = String.valueOf(empIdTxt.getValue());
        Date date = Date.valueOf(dateTxt.getValue().toString());
        int count = Integer.parseInt(countTxt.getText());
        String payment = String.valueOf(count * 30);

        var dto = new SalaryDto(id,empId,date,payment);
        boolean isValidated = validate();

        if (isValidated) {
            try {
                boolean isSaved = paymentBO.save(dto);
                if (isSaved) {
                   notifi.showNotification("Saved");
                    loadAllEmployees();
                    clearFields();
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

        String addressText = countTxt.getText();
        boolean isAddressValidated = Pattern.matches("[0-9]{3,}", addressText);
        if (!isAddressValidated) {
            errora.animateError(countTxt);
            new Alert(Alert.AlertType.ERROR, "Invalid address").show();
            return false;
        }

        return true;
    }

    @FXML
    void updateOnAction(ActionEvent event) {

        String id = idTxt.getText();
        String empId = (String) empIdTxt.getValue();
        Date date = Date.valueOf(dateTxt.getValue());
        String count = countTxt.getText();


        var dto = new SalaryDto(id,empId,date,count);
        try {
            boolean isUpdated = paymentBO.update(dto);
            System.out.println(isUpdated);
            if(isUpdated) {
                notifi.showNotification("Update");

                loadAllEmployees();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void initialize() {
        setCellValueFactory();
        loadAllEmployees();
        loadEmpId();
        generateNextPaymentId();
    }

    public void loadAllEmployees(){
        ObservableList<SalaryTm> obList = FXCollections.observableArrayList();

        try {
            List<SalaryDto> dtoList =paymentBO.getAll();
            for (SalaryDto dto : dtoList){


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
                        String id = (String) colid.getCellData(selectedIndex);

                        deleteItem(id);   //delete item from the database

                        obList.remove(selectedIndex);   //delete item from the JFX-Table
                        tbl.refresh();
                    }
                });

                obList.add(
                        new SalaryTm(
                                dto.getId(),
                                dto.getEmpId(),
                                dto.getDate(),
                                dto.getCount(),
                                btnDelete
                        )
                );

            }
            tbl.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {

        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colempId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colsCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        coldelete.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("btnDelete"));

    }

    private void deleteItem(String id) {
        try {
            boolean isDeleted = paymentBO.delete(id);
            if(isDeleted)
                notifi.showNotification("Delete");
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadEmpId() {
        EmployeDAOImpl employeDAO = new EmployeDAOImpl();
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<Employee> empList = employeDAO.getAll();

            for (Employee empDto : empList) {
                obList.add(empDto.getEmployeeId());
            }

            empIdTxt.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextPaymentId() {
        try {
            String orderId = paymentBO.generateID();
            idTxt.setText(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        idTxt.setText("");
       // dateTxt.setText("");
        countTxt .setText("");
    }

    @FXML
    void searchOnAction(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {

            String id = idTxt.getText();
            try {
                Salary salaryDto = paymentBO.search(id);
                if (salaryDto != null) {
                    idTxt.setText(salaryDto.getId());
                    empIdTxt.setValue(salaryDto.getEmpId());
                    dateTxt.setValue(salaryDto.getDate().toLocalDate());
                    countTxt.setText(salaryDto.getCount());

                } else {
                    notifi.showNotification("salary not found");
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void repoateOnAction(ActionEvent event) {

        try {
            // Load the JasperReport template
            InputStream resourceAsStream = getClass().getResourceAsStream("/report/SalaryRepoat.jrxml");
            JasperDesign load = JRXmlLoader.load(resourceAsStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    null,
                    DbConnection.getInstance().getConnection());

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
