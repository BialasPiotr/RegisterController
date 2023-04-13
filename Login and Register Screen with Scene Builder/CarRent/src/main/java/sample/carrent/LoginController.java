package sample.carrent;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import javafx.event.ActionEvent;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;

    public void loginButtonOnAction(ActionEvent event){
        if (usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false) {
            validateLogin();
        } else{
            loginMessageLabel.setText("Please enter username and password");
        }

    }
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + usernameTextField.getText() +"' AND password ='" + enterPasswordField.getText() +"'";
try {
    Statement statement = connectDb.createStatement();
    ResultSet queryResult = statement.executeQuery(verifyLogin);
    while(queryResult.next()){
        if(queryResult.getInt(1) == 1) {
            loginMessageLabel.setText("Congratulation!");
            createAccountForm();
        }else{
            loginMessageLabel.setText("Invalid login. Please try again.");

        }
    }
}catch(Exception e){
    e.printStackTrace();
    e.getCause();
}
    }
    public void createAccountForm(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
            Stage registerstage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 520, 476);
            registerstage.setTitle("Registration");
            registerstage.setScene(scene);
            registerstage.show();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}