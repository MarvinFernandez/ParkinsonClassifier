/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinsonclassifier;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import net.sf.clipsrules.jni.CLIPSException;


public class FXMLVistaSidesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public enum GENDER{male,female};
    
    @FXML
    private Button continue_BUT;
    @FXML
    private Button check_BUT;
    @FXML
    private TextField name_TF;
    @FXML
    private TextField surname_TF;
    @FXML
    private TextField age_TF;
    @FXML
    private ChoiceBox<GENDER> gender_CB;
    @FXML
    private Text message_txt;
    @FXML
    private CheckBox yes_BS;
    @FXML
    private CheckBox no_BS;
    @FXML
    private Button edit_BUT;
    
    private String local_query = "";
    
    private String name;
    private String surname;
    private String age;
    private String gender;
    private String answer_BS;
    

    
    @FXML
    private void check(ActionEvent event){
        if(name_TF.getText().trim().isEmpty() ||surname_TF.getText().trim().isEmpty() ||
                age_TF.getText().trim().isEmpty()||(!yes_BS.isSelected()&& !no_BS.isSelected())){
            message_txt.setText("Please complete all your data");
        }
        else{
            name = name_TF.getText();
            surname = surname_TF.getText();
            age = age_TF.getText();
            gender = gender_CB.getValue().toString();
            
            if(yes_BS.isSelected()) { answer_BS = "yes";}
            else if(no_BS.isSelected()) {answer_BS = "no";}
            continue_BUT.setDisable(false);
            message_txt.setText("You can continue with the Questionaire");
            
            name_TF.setDisable(true);
            surname_TF.setDisable(true);
            age_TF.setDisable(true);
            gender_CB.setDisable(true);
            yes_BS.setDisable(true);
            no_BS.setDisable(true);
            check_BUT.setDisable(true);
            edit_BUT.setDisable(false);
        }
    }
    
    @FXML
    private void yesBS(ActionEvent event){
        if(yes_BS.isSelected()){
            no_BS.setSelected(false);
        }
    }
    
    @FXML
    private void noBS(ActionEvent event){
        if(no_BS.isSelected()){
            yes_BS.setSelected(false);
        }
    }
    
    @FXML
    private void editInfo(ActionEvent event){
        edit_BUT.setDisable(true);
        continue_BUT.setDisable(true);
        name_TF.setDisable(false);
        surname_TF.setDisable(false);
        age_TF.setDisable(false);
        gender_CB.setDisable(false);
        yes_BS.setDisable(false);
        no_BS.setDisable(false);
        check_BUT.setDisable(false);
    }
    
    
    @FXML
    private void startTest(ActionEvent event){
        local_query = "(assert (Patient (name " + name + ") (surname " + surname + ") (age "+age+") (sex "+gender+") (both_sides "+answer_BS+")))";
        //System.out.println("Asserting:" + local_query);
        
        try {
            Main.clips.eval(local_query);
            Main.clips.run();
        } catch (CLIPSException ex) {
            Logger.getLogger(FXMLVistaSidesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(answer_BS.equalsIgnoreCase("yes")){
            try {
                Parent parent2 = FXMLLoader.load(getClass().getResource("FXMLGaitEvaluation.fxml"));
                Scene scene2 = new Scene(parent2);
                Main.stage_Questions.setScene(scene2);
                Main.stage_Questions.show();
            
            } catch (IOException ex) {
                Logger.getLogger(FXMLVistaInitController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Problems with Sides Vista 2");
                System.out.println(ex);
            }
        }
        else if(answer_BS.equalsIgnoreCase("no")){
            try {
                Parent parent2 = FXMLLoader.load(getClass().getResource("FXMLDiagnosisVista.fxml"));
                Scene scene2 = new Scene(parent2);
                Main.stage_Questions.setScene(scene2);
                Main.stage_Questions.show();
            
            } catch (IOException ex) {
                Logger.getLogger(FXMLVistaInitController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Problems with Sides Vista 2");
                System.out.println(ex);
            }
        }
        
    }
    
    
    private void loadData(){
        gender_CB.getItems().add(GENDER.male);
        gender_CB.getItems().add(GENDER.female);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        continue_BUT.setDisable(true);
        edit_BUT.setDisable(true);
        
    }    
    
}
