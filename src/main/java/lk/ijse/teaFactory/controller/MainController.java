package lk.ijse.teaFactory.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.teaFactory.bo.BOFactory;
import lk.ijse.teaFactory.bo.custome.EmployeAttendentBO;
import lk.ijse.teaFactory.dao.custome.EmployeeAttendensDAO;
import lk.ijse.teaFactory.dao.custome.Impl.EmployeeAttendensDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import static lk.ijse.teaFactory.bo.BOFactory.BOType.EMPLOYEEATTENEDENT;

public class MainController {

    @FXML
    private AnchorPane dashbordRoot;

    @FXML
    private JFXButton singoutbtn;

    @FXML
    private AnchorPane root;

    EmployeAttendentBO employeAttendentBO = (EmployeAttendentBO) BOFactory.getBoFactory().getBO(EMPLOYEEATTENEDENT);


    /*
    public AnchorPane getDashbordRoot() {
        return dashbordRoot;
    }
     */

    public void initialize() throws IOException {
        initializeDashboard();
    }

    private void initializeDashboard() throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/dashboard1.fxml"));

        this.dashbordRoot.getChildren().clear();
        this.dashbordRoot.getChildren().add(node);
    }

    @FXML
    void dashboardOnAction(ActionEvent event) throws IOException {
        initializeDashboard();
    }

    @FXML
    void profileOnAction(ActionEvent event) throws IOException {

        dashbordRoot.getChildren().clear();
        dashbordRoot.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/userProfile.fxml"))));

    }

    @FXML
    void SupplierOnAction(ActionEvent event) throws IOException {
        dashbordRoot.getChildren().clear();
       dashbordRoot.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/supplier_page.fxml"))));

    }

    @FXML
    void customerOnAction(ActionEvent event) throws IOException {
        dashbordRoot.getChildren().clear();
        dashbordRoot.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/customer_page.fxml"))));

    }

    @FXML
    void employeeOnBtn(ActionEvent event) throws IOException {
        dashbordRoot.getChildren().clear();
        dashbordRoot.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/employee_page.fxml"))));

    }

    @FXML
    void ordersOnAction(ActionEvent event) throws IOException {

        dashbordRoot.getChildren().clear();
        dashbordRoot.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/customerOrders.fxml"))));

    }

    @FXML
    void paymentOnAction(ActionEvent event) throws IOException {
        dashbordRoot.getChildren().clear();
        dashbordRoot.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/payment.fxml"))));

    }

    @FXML
    void singoutbtnOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
       // EmpAttendensModel empAttendensModel = new EmpAttendensModel();
        boolean a = employeAttendentBO.delete();

        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/login_page.fxml"))));
    }

    @FXML
    void stokebtnOnAction(ActionEvent event) throws IOException {

        dashbordRoot.getChildren().clear();
        dashbordRoot.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/ourStoke.fxml"))));

    }

}
