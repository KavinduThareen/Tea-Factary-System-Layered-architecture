package lk.ijse.teaFactory.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.teaFactory.dao.customer.CustomerDAO;
import lk.ijse.teaFactory.dao.customer.Impl.CustomerDAOImpl;
import lk.ijse.teaFactory.dao.customer.Impl.EmployeDAOImpl;
import lk.ijse.teaFactory.dto.CustomerDto;
import lk.ijse.teaFactory.dto.EmployeeDto;
import lk.ijse.teaFactory.dto.ErrorAnimation;
import lk.ijse.teaFactory.dto.NotificationAnimation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
public class CustomerAddPageController {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField cusAddressTxt;

    @FXML
    private TextField cuscontacTxt;

    @FXML
    private TextField cusidTxt;

    @FXML
    private TextField cusnameTxt;

    @FXML
    private JFXComboBox<String> empidTxt;

    CustomerDAO customerDAO = new CustomerDAOImpl();
    EmployeDAOImpl employeDAO = new EmployeDAOImpl();

    ErrorAnimation errorAnimation = new ErrorAnimation();
    NotificationAnimation notification = new NotificationAnimation();

    @FXML
    void addcusBackeBtn(ActionEvent event) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/customer_page.fxml"))));

    }

    public int  count = 0;

    @FXML
    void cusSaveOnAction(ActionEvent event) {

        String cusid = cusidTxt.getText();
        String empid = (String) empidTxt.getValue();
        String cusname = cusnameTxt.getText();
        String cusAddress = cusAddressTxt.getText();
        String cusCantac = cuscontacTxt.getText();

        var dto = new CustomerDto(cusid,empid,cusname,cusAddress,cusCantac);

       boolean isValidated = validate();

        if (isValidated) {
            try {
                boolean isSaved = customerDAO.save(dto);
                if (isSaved) {
                    notification.showNotification("saved");
                    count++;
                    clearFields();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
                throw new RuntimeException(e);
            }
        }

   }

    private boolean validate() {

        String idText = cusidTxt.getText();
        boolean isIDValidated = Pattern.matches("[C][0-9]{3,}", idText);
        if (!isIDValidated) {
            errorAnimation.animateError(cusidTxt);
            new Alert(Alert.AlertType.ERROR, "Invalid Customer ID!").show();
            return false;
        }

        String empText = empidTxt.getValue();
        boolean isUIDValidated = Pattern.matches("[E][0-9]{3,}", empText);
        if (!isUIDValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid employee ID!").show();
            return false;
        }

        String nameText = cusnameTxt.getText();
        boolean isNameValidated = Pattern.matches("[A-Za-z]{3,}", nameText);
        if (!isNameValidated) {
            errorAnimation.animateError(cusnameTxt);
            new Alert(Alert.AlertType.ERROR, "Invalid customer name").show();
            return false;
        }

        String cantacText = cuscontacTxt.getText();
        boolean isCantacValidated = Pattern.matches("[0-9]{10}", cantacText);
        if (!isCantacValidated) {
            errorAnimation.animateError(cuscontacTxt);
            new Alert(Alert.AlertType.ERROR, "Invalid contac").show();
            return false;
        }

        String addressText = cusAddressTxt.getText();
        boolean isAddressValidated = Pattern.matches("[A-Za-z0-9/.\\s]{3,}", addressText);
        if (!isAddressValidated) {
            errorAnimation.animateError(cusAddressTxt);
            new Alert(Alert.AlertType.ERROR, "Invalid  address").show();
            return false;
        }

        return true;
    }


    private void loadEmpId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<EmployeeDto> empList = employeDAO.getAll();

            for (EmployeeDto empDto : empList) {
                obList.add(empDto.getEmployeeId());
            }

            empidTxt.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextCusId() {
        try {
            String cusId = customerDAO.generateID();
            cusidTxt.setText(cusId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {

        String cusid = cusidTxt.getText();
        String empid = (String) empidTxt.getValue();
        String cusname = cusnameTxt.getText();
        String cusAddress = cusAddressTxt.getText();
        String cusCantac = cuscontacTxt.getText();

        var dto = new CustomerDto(cusid,empid,cusname,cusAddress,cusCantac);

        try {
            boolean isUpdated = customerDAO.update(dto);
            System.out.println(isUpdated);
            if(isUpdated) {
                notification.showNotification("update");
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public void initialize() {
        loadEmpId();
       generateNextCusId();
    }

    void clearFields() {
        cusidTxt.setText("");
        empidTxt.setValue("");
        cusnameTxt.setText("");
        cusAddressTxt.setText("");
        cuscontacTxt.setText("");
    }

    @FXML
    void searchOnAction(ActionEvent event) {
        searchCustomer();
    }

    public void searchCustomer(){

        String id = cusidTxt.getText();

        try {
            CustomerDto customerDto = customerDAO.search(id);

            if (customerDto != null) {
                cusidTxt.setText(customerDto.getCusid());
                empidTxt.setValue(customerDto.getEmpid());
                cusnameTxt.setText(customerDto.getCusname());
                cusAddressTxt.setText(customerDto.getCusAddress());
                cuscontacTxt.setText(customerDto.getCusCantac());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void searchcusOnAction(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            searchCustomer();
        }
    }



}
