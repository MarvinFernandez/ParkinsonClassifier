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


public class FXMLPostureTremorEvaluationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private CheckBox yes_R;
    @FXML
    private CheckBox no_R;
    @FXML
    private CheckBox yes_PT;
    @FXML
    private CheckBox no_PT;
    @FXML
    private CheckBox yes_SO;
    @FXML
    private CheckBox no_SO;
    @FXML
    private CheckBox t_0;
    @FXML
    private CheckBox t_1;
    @FXML
    private CheckBox t_2;
    @FXML
    private CheckBox t_3;
    @FXML
    private Button edit_BUT;
    @FXML
    private Text message_txt;
    @FXML
    private Button check_BUT;
    @FXML
    private Button continue_BUT;
    @FXML
    private Button check_SBUT;
    @FXML
    private TextField posture_scoreTF;
    @FXML
    private TextField tremor_scoreTF;
    
    
    
    private int retropulsion = 0;
    private int pt = 0;
    private int so = 0;
    private int tremor = 0;
    
    
    
    @FXML
    private void continueQuestionaire(ActionEvent event){
        Parent parent2;
        try {
            parent2 = FXMLLoader.load(getClass().getResource("FXMLVoiceEvaluation.fxml"));
            Scene scene2 = new Scene(parent2);
            Main.stage_Questions.setScene(scene2);
            Main.stage_Questions.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLGaitEvaluationController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Problems opening Voice Evaluation Vista");
        }
    }
    
    
    @FXML
    private void checkScore(ActionEvent event){
        try {
            Main.patient = Main.clips.findFact("Patient");
            int address = (int) Main.patient.getFactIndex();
            
            String query = "(modify "+address+" (retropulsion "+retropulsion+")"
            + "(pulltest "+pt+")(stoopedover "+so+")(tremor "+tremor+"))";
            Main.clips.eval(query);
            Main.clips.run();
            
            Main.patient = Main.clips.findFact("Patient");
            String posture_score = Main.patient.getSlotValue("posture").toString();
            String tremor_score = Main.patient.getSlotValue("tremor").toString();
            
            posture_scoreTF.setText(posture_score);
            tremor_scoreTF.setText(tremor_score);
            
            posture_scoreTF.setDisable(true);
            tremor_scoreTF.setDisable(true);
            
            continue_BUT.setDisable(false);
            check_SBUT.setDisable(true);
            } 
        catch (CLIPSException ex) {
            Logger.getLogger(FXMLGaitEvaluationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    @FXML
    private void editInfo(ActionEvent event){
        edit_BUT.setDisable(true);
        continue_BUT.setDisable(true);
        check_SBUT.setDisable(true);
        
        yes_R.setDisable(false);
        no_R.setDisable(false);
        yes_PT.setDisable(false);
        no_PT.setDisable(false);
        yes_SO.setDisable(false);
        no_SO.setDisable(false);
        t_0.setDisable(false);
        t_1.setDisable(false);
        t_2.setDisable(false);
        t_3.setDisable(false);
       
        check_BUT.setDisable(false);
        posture_scoreTF.setText("");
        tremor_scoreTF.setText("");
    }
    
    @FXML
    private void check(ActionEvent event){
        if( (!yes_R.isSelected()&& !no_R.isSelected())||
            (!yes_PT.isSelected()&& !no_PT.isSelected()) ||(!yes_SO.isSelected()&& !no_SO.isSelected())||
             (!t_0.isSelected()&& !t_1.isSelected()&& !t_2.isSelected()&& !t_3.isSelected())){
            message_txt.setText("Please complete all your questions");
        }
        else{
            message_txt.setText("You can continue with the Questionaire");
            check_SBUT.setDisable(false);
            yes_R.setDisable(true);
            no_R.setDisable(true);
            yes_PT.setDisable(true);
            no_PT.setDisable(true);
            yes_SO.setDisable(true);
            no_SO.setDisable(true);
            t_0.setDisable(true);
            t_1.setDisable(true);
            t_2.setDisable(true);
            t_3.setDisable(true);
            
            check_BUT.setDisable(true);
            edit_BUT.setDisable(false);
        }
    }
     
    @FXML
    private void tremor_0(ActionEvent event){
        if(t_0.isSelected()){
            t_1.setSelected(false);
            t_2.setSelected(false);
            t_3.setSelected(false);
            tremor = 0;
        }
    }
    
    @FXML
    private void tremor_1(ActionEvent event){
        if(t_1.isSelected()){
            t_0.setSelected(false);
            t_2.setSelected(false);
            t_3.setSelected(false);
            tremor = 1;
        }
    }
    
    @FXML
    private void tremor_2(ActionEvent event){
        if(t_2.isSelected()){
            t_0.setSelected(false);
            t_1.setSelected(false);
            t_3.setSelected(false);
            tremor = 2;
        }
    }
    
     @FXML
    private void tremor_3(ActionEvent event){
        if(t_3.isSelected()){
            t_0.setSelected(false);
            t_1.setSelected(false);
            t_2.setSelected(false);
            tremor = 3;
        }
    }
    
    
    @FXML
    private void yesRetropulsion(ActionEvent event){
        if(yes_R.isSelected()){
            no_R.setSelected(false);
            retropulsion = 1;
        }
    }
    
    @FXML
    private void noRetropulsion(ActionEvent event){
        if(no_R.isSelected()){
            yes_R.setSelected(false);
            retropulsion = 0;
        }
    }
    
    @FXML
    private void yesPullTest(ActionEvent event){
        if(yes_PT.isSelected()){
            no_PT.setSelected(false);
            pt = 1;
        }
    }
    
    @FXML
    private void noPullTest(ActionEvent event){
        if(no_PT.isSelected()){
            yes_PT.setSelected(false);
            pt = 0;
        }
    }
    
    @FXML
    private void yesStooped(ActionEvent event){
        if(yes_SO.isSelected()){
            no_SO.setSelected(false);
            so = 1;
        }
    }
    
    @FXML
    private void noStooped(ActionEvent event){
        if(no_SO.isSelected()){
            yes_SO.setSelected(false);
            so = 0;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        continue_BUT.setDisable(true);
        check_SBUT.setDisable(true);
        edit_BUT.setDisable(true);
    }    
    
}
