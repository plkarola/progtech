package Controller;

import Model.UserDAO;
import Model.UserDAOFactory;
import Model.UserValidator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController implements Initializable {

    UserDAO userDao;
    
    @FXML
    Label reg_error;
    
    @FXML
    TextField reg_usernamefield;
    
    @FXML
    PasswordField reg_passwordfield;
    
    @FXML
    PasswordField reg_cpasswordfield;
    
    @FXML
    private void handleRegisterAction(ActionEvent event) {

        UserValidator validator = new UserValidator(userDao);
        if (validator.regValidateUN(reg_usernamefield.getText()) && validator.regValidatePW(reg_passwordfield.getText(), reg_cpasswordfield.getText())){
            userDao.createUser(reg_usernamefield.getText(), reg_passwordfield.getText());
        try{
            Stage stage = (Stage)reg_error.getScene().getWindow();
            
            FXMLLoader f1 = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root = f1.load();
            f1.<LoginController>getController().regSuccessful();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
        
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e) {
            e.printStackTrace(System.out);
        }
        }
        else{
            if (!validator.regValidateUN(reg_usernamefield.getText())) reg_error.setText("Incorrect Username,please, choose another");
            if (!validator.regValidatePW(reg_passwordfield.getText(), reg_cpasswordfield.getText())) reg_error.setText("Passwords do not match");
        }
    }
    
    @FXML
    private void handleCancelAction(ActionEvent event) {
        try{
            Stage stage = (Stage)reg_error.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
        
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e) {
            e.printStackTrace(System.out);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userDao = UserDAOFactory.getInstance().createUserDAO();
    }    
    
}
