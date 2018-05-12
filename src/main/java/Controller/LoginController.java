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

public class LoginController implements Initializable {
    
    UserDAO userDao;
    
    @FXML
    private Label login_error;
    
    @FXML
    private Label login_regsuccess;
    
    @FXML
    private TextField login_usernamefield;
    
    @FXML
    private PasswordField login_passwordfield;
    
    
    @FXML
    private void handleLoginAction(ActionEvent event) {
        UserValidator validator = new UserValidator(userDao);
        if(validator.loginValidate(login_usernamefield.getText(), login_passwordfield.getText())){
        try{
            Stage stage = (Stage)login_error.getScene().getWindow();

            FXMLLoader f1 = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
            Parent root = f1.load();
            f1.<HomeController>getController().showUsername(login_usernamefield.getText());
            
            
            Scene scene = new Scene(root);

            scene.getStylesheets().add("/styles/Styles.css");
        
            stage.setTitle("Welcome!");
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e) {
            e.printStackTrace(System.out);
        }
        }
        else {
            login_error.setText("Something went wrong.");
        }
    }
    
    @FXML
    private void handleRegisterAction(ActionEvent event) {
        try{
            Stage stage = (Stage)login_error.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Register.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
        
            stage.setTitle("Registration");
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void regSuccessful(){
        login_regsuccess.setText("Successful registration");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userDao = UserDAOFactory.getInstance().createUserDAO();
    }    
}
