package Controller;

import Model.Difficulty;
import Model.GameDAO;
import Model.GameDAOFactory;
import Model.GameValidator;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TableController implements Initializable {

    GameDAO gameDao;
    GameValidator validator;
    Difficulty diff;
    @FXML
    Button next;
    @FXML
    Label endgame;
    @FXML
    GridPane grid1;
    @FXML
    GridPane grid2;
    @FXML
    GridPane grid3;
    @FXML
    GridPane grid4;
    @FXML
    GridPane grid5;
    @FXML
    GridPane grid6;
    @FXML
    GridPane grid7;
    @FXML
    GridPane grid8;
    @FXML
    GridPane grid9;
    @FXML
    GridPane mainGrid;
    @FXML
    Label username;
    @FXML
    Label difficulty;
    
    private int fix_elements[][];
    private int mat[][];
    
    private void fillOneGrid(GridPane grid, int imin, int imax, int jmin, int jmax) {
        List<Node> list = grid.getChildren();
        int counter = 0; 
        for (int i = imin; i<imax; i++) {
            for(int j = jmin; j< jmax;j++) {
                TextField tf = (TextField) list.get(counter);
                tf.setAlignment(Pos.CENTER);
                if(mat[i][j] != 0) {
                    tf.setText(Integer.toString(mat[i][j]));
                    fix_elements[i][j] = 1;
                    tf.setEditable(false);
                    tf.setStyle("-fx-control-inner-background:beige");
                }
                else {
                    validateCell(tf);
                } 
                counter++;
            }
        }
    }
    
    public void fillTable() {
       fillOneGrid(grid1, 0,3,0,3 );
       fillOneGrid(grid2, 0,3,3,6 );
       fillOneGrid(grid3, 0,3,6,9 );
       
       fillOneGrid(grid4, 3,6,0,3 );
       fillOneGrid(grid5, 3,6,3,6 );
       fillOneGrid(grid6, 3,6,6,9 );
       
       fillOneGrid(grid7, 6,9,0,3 );
       fillOneGrid(grid8, 6,9,3,6 );
       fillOneGrid(grid9, 6,9,6,9 );
    }
    
    public void validateCell(TextField tf) {
        tf.setText(""); 
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int number,i,j;
                i = Integer.parseInt(tf.getId().substring(2, 3));
                j = Integer.parseInt(tf.getId().substring(3, 4));
                String lastValue = validator.validateCell(oldValue,newValue);
                tf.setText(lastValue);
                if (lastValue.isEmpty()) { 
                    number = 0;
                    mat[i][j] = number;
                }
                else
                    number = Integer.parseInt(lastValue);
                
                int errors[][] = new int[9][9];
                errors = validator.getErrors(mat, number, i, j);
                if(!validator.validateTable(mat,number ,i ,j )) {
                    showErrors(errors,"-fx-control-inner-background:#80c800");
                    tf.setStyle("-fx-control-inner-background:#80c800");
                }
                else {
                    tf.setStyle("-fx-control-inner-background:#ffffff");
                    mat[i][j] = number;
                    validateCompletion(mat);
                }
                if (!oldValue.equals("") && !oldValue.equals(lastValue)) {
                        errors = validator.getErrors(mat, Integer.parseInt(oldValue), i, j);
                        showErrors(errors,"-fx-control-inner-background:#ffffff"); 
                    }
            }
        });
    }
    
    public void showErrors(int errors[][], String color){
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                if (errors[i][j] == 1 ) {
                    ObservableList grids = mainGrid.getChildren();
                    for (int g = 0; g < grids.size(); g++) {
                        GridPane grid = (GridPane)grids.get(g);
                        ObservableList fields = grid.getChildren();
                        for (int t = 0; t < fields.size(); t++){
                            TextField tf = (TextField) fields.get(t);
                            if (tf.getId().contains(Integer.toString(i)+Integer.toString(j))) {
                                tf.setStyle(color);
                                if(!tf.isEditable() && color.contains("#ffffff"))
                                    tf.setStyle("-fx-control-inner-background:beige");
                            }
                        }
                    } 
                }
            }
        }
    }
    
    public void validateCompletion(int mat[][]){
        if(validator.isTableComplete(mat)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Level Comlete");
            alert.show();
        }
    }
    
    @FXML
    private void handleLogoutAction(ActionEvent event) {

        try{
            Stage stage = (Stage)username.getScene().getWindow();
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
    
    @FXML
    private void handleNextTable(ActionEvent event) {
        try{
            Stage stage = (Stage)username.getScene().getWindow();
            
            FXMLLoader f1 = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
            Parent root = f1.load();
            f1.<TableController>getController().setDifficulty(diff.toString(), username.getText(), Integer.parseInt(difficulty.getText().substring(difficulty.getText().length() - 1)) + 1 );
            
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
    private void handleGoHome(ActionEvent event) {
        try{
            Stage stage = (Stage)username.getScene().getWindow();

            String un = username.getText().replaceAll("Hello ", "");
            un = un.replaceAll("!", "");
            FXMLLoader f1 = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
            Parent root = f1.load();
            f1.<HomeController>getController().showUsername(un);
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
        
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void setDifficulty(String d, String uname, int id){
        diff = Difficulty.valueOf(d);
        difficulty.setText(diff.toString() + " " + Integer.toString(id));
        username.setText(uname);
        
        try {
            mat = gameDao.getBoard(diff, id);
            fillTable();
        } catch (Exception e) {
            showEndGame();
        }
    }
    
    public void showEndGame(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("No more " + diff.toString() + " tables");
        alert.show();
        next.setDisable(true);
        mainGrid.setDisable(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameDao = GameDAOFactory.getInstance().createGameDAO();
        validator = new GameValidator(gameDao);
        fix_elements = new int[9][9];
    }     
}
