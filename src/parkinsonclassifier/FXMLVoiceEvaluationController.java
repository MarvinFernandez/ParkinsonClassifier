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


public class FXMLVoiceEvaluationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private CheckBox yes_SV;
    @FXML
    private CheckBox no_SV;
    @FXML
    private CheckBox yes_SS;
    @FXML
    private CheckBox no_SS;
    @FXML
    private CheckBox yes_FS;
    @FXML
    private CheckBox no_FS;
    @FXML
    private Button edit_BUT;
    @FXML
    private Text message_txt;
    @FXML
    private Button check_BUT;
    @FXML
    private Button continue_BUT;
    @FXML
    private Button check_VABUT;
    @FXML
    private TextField vocalAbility_scoreTF;
    
    private int sv = 0;
    private int ss = 0;
    private int fs = 0;
    
    
    @FXML
    private void continueQuestionaire(ActionEvent event){
        Parent parent2;
        try {
            parent2 = FXMLLoader.load(getClass().getResource("FXMLDiagnosisVista.fxml"));
            Scene scene2 = new Scene(parent2);
            Main.stage_Questions.setScene(scene2);
            Main.stage_Questions.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLGaitEvaluationController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Problems opening Diagnosis Vista");
        }
    }
    
    @FXML
    private void checkVocalAbilityScore(ActionEvent event){
        try {
            Main.patient = Main.clips.findFact("Patient");
            int address = (int) Main.patient.getFactIndex();
            
            String query = "(modify "+address+" (softvoice "+sv+")"
            + "(stutter "+ss+")(fasttalk "+fs+"))";
            Main.clips.eval(query);
            Main.clips.run();
            
            Main.patient = Main.clips.findFact("Patient");
            String va_score = Main.patient.getSlotValue("voice").toString();
            vocalAbility_scoreTF.setText(va_score);
            vocalAbility_scoreTF.setDisable(true);
            continue_BUT.setDisable(false);
            check_VABUT.setDisable(true);
            } 
        catch (CLIPSException ex) {
            Logger.getLogger(FXMLGaitEvaluationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @FXML
    private void check(ActionEvent event){
        if( (!yes_SV.isSelected()&& !no_SV.isSelected())||
            (!yes_SS.isSelected()&& !no_SS.isSelected()) ||(!yes_FS.isSelected()&& !no_FS.isSelected())){
            message_txt.setText("Please complete all your questions");
        }
        else{
            message_txt.setText("You can continue with the Questionaire");
            check_VABUT.setDisable(false);
            yes_SV.setDisable(true);
            no_SV.setDisable(true);
            yes_SS.setDisable(true);
            no_SS.setDisable(true);
            yes_FS.setDisable(true);
            no_FS.setDisable(true);
            check_BUT.setDisable(true);
            edit_BUT.setDisable(false);
        }
    }
    
    @FXML
    private void editInfo(ActionEvent event){
        edit_BUT.setDisable(true);
        continue_BUT.setDisable(true);
        check_VABUT.setDisable(true);
        yes_SV.setDisable(false);
        no_SV.setDisable(false);
        yes_SS.setDisable(false);
        no_SS.setDisable(false);
        yes_FS.setDisable(false);
        no_FS.setDisable(false);
        check_BUT.setDisable(false);
        vocalAbility_scoreTF.setText("");
    }
    
    @FXML
    private void yesSoftVoice(ActionEvent event){
        if(yes_SV.isSelected()){
            no_SV.setSelected(false);
            sv = 1;
        }
    }
    
    @FXML
    private void noSoftVoice(ActionEvent event){
        if(no_SV.isSelected()){
            yes_SV.setSelected(false);
            sv = 0;
        }
    }
    
    @FXML
    private void yesStuttered(ActionEvent event){
        if(yes_SS.isSelected()){
           no_SS.setSelected(false);
           ss = 1;
        }
    }
    
    @FXML
    private void noStuttered(ActionEvent event){
        if(no_SS.isSelected()){
            yes_SS.setSelected(false);
            ss = 0;
        }
    }
    
    @FXML
    private void yesFastSpeaking(ActionEvent event){
        if(yes_FS.isSelected()){
            no_FS.setSelected(false);
            fs = 1;
        }
    }
    
    @FXML
    private void noFastSpeaking(ActionEvent event){
        if(no_FS.isSelected()){
            yes_FS.setSelected(false);
            fs = 0;
        }
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        continue_BUT.setDisable(true);
        check_VABUT.setDisable(true);
        edit_BUT.setDisable(true);
    }    
    
}
