package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomeController implements Initializable {

    @FXML
    Label home_error;
    @FXML
    Label home_hello;
    
    @FXML
    public void showUsername(String username){
        home_hello.setText("Hello " + username + "!");
    }
    
    @FXML
    private void handleGameAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        try{
            Stage stage = (Stage)home_error.getScene().getWindow();
            
            FXMLLoader f1 = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
            Parent root = f1.load();
            f1.<TableController>getController().setDifficulty(button.getText().toUpperCase(), home_hello.getText(), 0);

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
        
            stage.setTitle("Game");
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e) {
            e.printStackTrace(System.out);
        }
    }
    
    @FXML
    private void handleLogoutAction(ActionEvent event) {

        try{
            Stage stage = (Stage)home_error.getScene().getWindow();
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

    }    
    
}
