package lk.ijse.teaFactory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.teaFactory.Entity.Otp;
import lk.ijse.teaFactory.bo.BOFactory;
import lk.ijse.teaFactory.bo.custome.OtpBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.OtpDAO;
import lk.ijse.teaFactory.dto.OtpDto;
import lk.ijse.teaFactory.gmail.Gmailer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;

import static lk.ijse.teaFactory.dao.DAOFactory.DAOType.OTP;

public class FogetpwController {

    @FXML
    private TextField emailTxt;

    @FXML
    private AnchorPane root;
    private String email;
    public int otp;

    OtpBO otpBO = (OtpBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.OTP);

    private int otp2;

    @FXML
    void emailOnAction(ActionEvent event) throws Exception {
        email = emailTxt.getText();
        otp = generateNewOtp();
        sendOtp();

        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/otpPage.fxml"))));

    }

    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/login_page.fxml"))));
    }

    public void sendOtp() {
        boolean b1 = false;
        if (email.contains("@")){
            int index = email.indexOf("@");
            if (!email.substring(index + 1).equals("gmail.com")){
                return;
            }
        } else {
            return;
        }
        try {
            b1 = Gmailer.setEmailCom(email, otp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int generateNewOtp() throws SQLException, ClassNotFoundException {
        do {
            Random random = new Random();
            otp2 = random.nextInt(9999);

            if (otp2 > 1000){
               var entity = new OtpDto(otp2);
               boolean a =  otpBO.save(entity);
              //  System.out.println(otp2);
                return otp2;
            }
        }while (true);
    }



}
