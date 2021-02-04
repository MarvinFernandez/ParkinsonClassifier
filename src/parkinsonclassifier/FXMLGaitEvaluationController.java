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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import net.sf.clipsrules.jni.CLIPSException;
import net.sf.clipsrules.jni.PrimitiveValue;


public class FXMLGaitEvaluationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField patient_Name;
    @FXML
    private TextField patient_Age;
    @FXML
    private TextField patient_Gender;
    @FXML
    private CheckBox yes_L;
    @FXML
    private CheckBox no_L;
    @FXML
    private CheckBox yes_SW;
    @FXML
    private CheckBox no_SW;
    @FXML
    private CheckBox yes_FF;
    @FXML
    private CheckBox no_FF;
    @FXML
    private Button continue_BUT;
    @FXML
    private Button check_GSBUT;
    @FXML
    private TextField gait_scoreTF;
    @FXML
    private Button edit_BUT;
    @FXML
    private Text message_txt;
    @FXML
    private Button check_BUT;
     
    private int limp = 0;
    private int slow_walking = 0;
    private int fall_freq = 0;
    
    
    
    @FXML
    private void continueQuestionaire(ActionEvent event){
        Parent parent2;
        try {
            parent2 = FXMLLoader.load(getClass().getResource("FXMLDailyActivitiesEvaluation.fxml"));
            Scene scene2 = new Scene(parent2);
            Main.stage_Questions.setScene(scene2);
            Main.stage_Questions.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLGaitEvaluationController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Problems opening Daily Activities Vista");
        }
    }
        
    
    @FXML
    private void check(ActionEvent event){
        if( (!yes_L.isSelected()&& !no_L.isSelected())||
            (!yes_SW.isSelected()&& !no_SW.isSelected()) ||(!yes_FF.isSelected()&& !no_FF.isSelected())){
            message_txt.setText("Please complete all your questions");
        }
        else{
            message_txt.setText("You can continue with the Questionaire");
            check_GSBUT.setDisable(false);
            yes_L.setDisable(true);
            no_L.setDisable(true);
            yes_SW.setDisable(true);
            no_SW.setDisable(true);
            yes_FF.setDisable(true);
            no_FF.setDisable(true);
            check_BUT.setDisable(true);
            edit_BUT.setDisable(false);
        }
    }
    
    
    @FXML
    private void checkGaitScore(ActionEvent event){
        try {
            Main.patient = Main.clips.findFact("Patient");
            int address = (int) Main.patient.getFactIndex();
            
            String query = "(modify "+address+" (limp "+limp+")"
            + "(slow_walking "+slow_walking+")(falling_freq "+fall_freq+"))";
            Main.clips.eval(query);
            Main.clips.run();
            
            Main.patient = Main.clips.findFact("Patient");
            String gait_score = Main.patient.getSlotValue("gait").toString();
            
            gait_scoreTF.setText(gait_score);
            gait_scoreTF.setDisable(true);
            continue_BUT.setDisable(false);
            check_GSBUT.setDisable(true);
            
            } 
        catch (CLIPSException ex) {
            Logger.getLogger(FXMLGaitEvaluationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    @FXML
    private void editInfo(ActionEvent event){
        edit_BUT.setDisable(true);
        continue_BUT.setDisable(true);
        check_GSBUT.setDisable(true);
        yes_L.setDisable(false);
        no_L.setDisable(false);
        yes_SW.setDisable(false);
        no_SW.setDisable(false);
        yes_FF.setDisable(false);
        no_FF.setDisable(false);
        check_BUT.setDisable(false);
        gait_scoreTF.setText("");
    }
    
    
    @FXML
    private void yesLimp(ActionEvent event){
        if(yes_L.isSelected()){
            no_L.setSelected(false);
            limp = 1;
        }
    }
    
    @FXML
    private void noLimp(ActionEvent event){
        if(no_L.isSelected()){
            yes_L.setSelected(false);
            limp = 0;
        }
    }
    
    @FXML
    private void yesSlowWalking(ActionEvent event){
        if(yes_SW.isSelected()){
            no_SW.setSelected(false);
            slow_walking = 1;
        }
    }
    
    @FXML
    private void noSlowWalking(ActionEvent event){
        if(no_SW.isSelected()){
            yes_SW.setSelected(false);
            slow_walking = 0;
        }
    }
    
    @FXML
    private void yesFallFreq(ActionEvent event){
        if(yes_FF.isSelected()){
            no_FF.setSelected(false);
            fall_freq = 1;
        }
    }
    
    @FXML
    private void noFallFreq(ActionEvent event){
        if(no_FF.isSelected()){
            yes_FF.setSelected(false);
            fall_freq = 0;
        }
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           
            Main.patient = Main.clips.findFact("Patient");
            String name = Main.patient.getSlotValue("name").toString();
            String age = Main.patient.getSlotValue("age").toString();
            String gender = Main.patient.getSlotValue("sex").toString();
            
            patient_Name.setText(name);
            patient_Gender.setText(gender);
            patient_Age.setText(age);
            
            patient_Name.setDisable(true);
            patient_Gender.setDisable(true);
            patient_Age.setDisable(true);
            
            continue_BUT.setDisable(true);
            check_GSBUT.setDisable(true);
            edit_BUT.setDisable(true);
            
        } catch (CLIPSException ex) {
            Logger.getLogger(FXMLGaitEvaluationController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }    
    
}
