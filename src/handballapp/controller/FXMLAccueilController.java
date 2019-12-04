/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handballapp.controller;

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
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Peio 
 */
public class FXMLAccueilController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btn_addMatch;
    
    @FXML 
    private GridPane gridPane_LiveMatch;
    
    
    @FXML void addMatch(ActionEvent event) throws IOException{
            Parent root1 = FXMLLoader.load(getClass().getResource("FXMLCreateMatch.fxml"));
            Stage StageCreateMatch = new Stage();
            StageCreateMatch.setTitle("Création / Modification des équipes");  
            StageCreateMatch.setScene(new Scene(root1));
            
            Stage Stage_Accueil = (Stage) btn_addMatch.getScene().getWindow(); //Get the Stage Accueil to set as parent
            
            StageCreateMatch.initModality(Modality.WINDOW_MODAL);  // Specifies the modality for new window
            StageCreateMatch.initOwner(Stage_Accueil);// Specifies the owner Window (parent) for new window
            StageCreateMatch.show();            
        }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
    }    
    
}
