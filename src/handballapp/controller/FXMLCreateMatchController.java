/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handballapp.controller;


import handballapp.model.Player;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Peio
 */
public class FXMLCreateMatchController implements Initializable {
    
ObservableList listTeam = FXCollections.observableArrayList();
ObservableList listLigue = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @FXML 
    private TableView tab_team1;
    
    @FXML
    private ComboBox<String> choiceBox_team1;
        
    @FXML
    private ComboBox<String> choiceBox_ligue;
    
    @FXML
    private TextField txtField_number1; //ajout du numéro de joueur équipe 1
    
    @FXML
    private TextField txtField_post1;    //ajout du poste/position de joueur équipe 1
    
    @FXML
    private TextField txtField_name1;  //ajout du nom de joueur équipe 1
      
    @FXML
    private TextField txtField_teamName1; //champ d'ajout du nom de l'équipe 1
    
    @FXML
    private Button btn_addPlayer1;
    
    
    @FXML
    private TableColumn numberColumn1;
    
    @FXML
    private TableColumn nameColumn1;
    
    @FXML
    private TableColumn postColumn1;
             
    @FXML 
    private void addPlayer1(){

        if((txtField_number1.getText().isEmpty()) && (txtField_post1.getText().isEmpty()) && (txtField_name1.getText().isEmpty()))
        {
             System.out.println("Missing player information.TextField empty.");
             //A voir afficher un message d'alerte ou un simple label en rouge à côté du bouton ajouter 
   
        }else
        {
            System.out.println("Send information to the Player 1 table.");
            
            //We get the TextField values
            txtField_number1.getText();
            txtField_name1.getText();
            txtField_post1.getText();
            
            // This Part is a test to add data to the table1
            // ---- Player Data Table Team 1 ------ //        
            Player player1 = new Player(txtField_number1.getText(), txtField_name1.getText(), txtField_post1.getText());

            // Assigne la colonne et la varible de l'Object Player.number
            numberColumn1.setCellValueFactory(new PropertyValueFactory<>("number")); 
            nameColumn1.setCellValueFactory(new PropertyValueFactory<>("lastName")); 
            postColumn1.setCellValueFactory(new PropertyValueFactory<>("post"));

            //Adding Datas to Table1
            tab_team1.getItems().add(player1);  
            
            //Display info 
            System.out.println(tab_team1.getColumns().get(0));
        }       
    }

    @FXML
    private void onActionValider(){
        //Check if there is enough player(7player/team) or team(2)
    }
   
    private void loadData(){
        //Initialize Checkbox Data Team Choice
        listTeam.removeAll(listTeam);
        String str_equipe1 = "Rennes";
        String str_equipe2 = "Bordeaux";
        String str_equipe3 = "Paris Saint-Germain";
        String str_equipe4 = "Limoges";
        listTeam.addAll(str_equipe1, str_equipe2, str_equipe3, str_equipe4);
        choiceBox_team1.getItems().addAll(listTeam);
        //choiceBox_team1.setValue('Rennes'); -> permet de préremplir un champ
        
        //Initialize Checkbox Data Ligue Choice
        listLigue.removeAll(listLigue);
        String str_ligue1 = "Lidl Starligue";
        String str_ligue2 = "Proligue";

        listLigue.addAll(str_ligue1, str_ligue2);
        choiceBox_ligue.getItems().addAll(listLigue);
        
        
//        // This Part is a test to add data to the table1
//        // ---- Player Data Table Team 1 ------ //        
//        Player player1 = new Player("1", "Karabatic", "arrière");
//        Player player2 = new Player("2", "BOB", "attaquant");
//  
//        TableColumn numberColumn = new TableColumn("#");
//        TableColumn nameColumn = new TableColumn("Nom");
//        TableColumn postColumn = new TableColumn("Poste");
//        
//        // Assigne la colonne et la varible de l'Object Player.number
//        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number")); 
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName")); 
//        postColumn.setCellValueFactory(new PropertyValueFactory<>("post"));
//        
//        //Adding columns to table1
//        tab_team1.getColumns().addAll(numberColumn, nameColumn, postColumn);
//       
//        //Adding Datas to Table1
//        tab_team1.getItems().add(player1); 
//        tab_team1.getItems().add(player2);       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }    
       
}
