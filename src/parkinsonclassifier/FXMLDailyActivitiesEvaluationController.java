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


public class FXMLDailyActivitiesEvaluationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private CheckBox yes_Eat;
    @FXML
    private CheckBox no_Eat;
    @FXML
    private CheckBox yes_Dress;
    @FXML
    private CheckBox no_Dress;
    @FXML
    private CheckBox yes_LA;
    @FXML
    private CheckBox no_LA;
    @FXML
    private Button edit_BUT;
    @FXML
    private Text message_txt;
    @FXML
    private Button check_BUT;
    @FXML
    private Button continue_BUT;
    @FXML
    private Button check_DABUT;
    @FXML
    private TextField dailyActivities_scoreTF;
    
    
    
    private int eat = 0;
    private int dress = 0;
    private int la = 0;
    
    
    
    @FXML
    private void continueQuestionaire(ActionEvent event){
        Parent parent2;
        try {
            parent2 = FXMLLoader.load(getClass().getResource("FXMLPostureTremorEvaluation.fxml"));
            Scene scene2 = new Scene(parent2);
            Main.stage_Questions.setScene(scene2);
            Main.stage_Questions.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLGaitEvaluationController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Problems opening Posture and Tremor Vista");
        }
    }
    
    
    @FXML
    private void check(ActionEvent event){
        if( (!yes_Eat.isSelected()&& !no_Eat.isSelected())||
            (!yes_Dress.isSelected()&& !no_Dress.isSelected()) ||(!yes_LA.isSelected()&& !no_LA.isSelected())){
            message_txt.setText("Please complete all your questions");
        }
        else{
            message_txt.setText("You can continue with the Questionaire");
            check_DABUT.setDisable(false);
            yes_Eat.setDisable(true);
            no_Eat.setDisable(true);
            yes_Dress.setDisable(true);
            no_Dress.setDisable(true);
            yes_LA.setDisable(true);
            no_LA.setDisable(true);
            check_BUT.setDisable(true);
            edit_BUT.setDisable(false);
        }
    }
    
    @FXML
    private void checkDailyActivitiesScore(ActionEvent event){
        try {
            Main.patient = Main.clips.findFact("Patient");
            int address = (int) Main.patient.getFactIndex();
            
            String query = "(modify "+address+" (eat "+eat+")"
            + "(dress "+dress+")(dependency "+la+"))";
            Main.clips.eval(query);
            Main.clips.run();
            
            Main.patient = Main.clips.findFact("Patient");
            String da_score = Main.patient.getSlotValue("daily_activities").toString();
            dailyActivities_scoreTF.setText(da_score);
            dailyActivities_scoreTF.setDisable(true);
            continue_BUT.setDisable(false);
            check_DABUT.setDisable(true);
            } 
        catch (CLIPSException ex) {
            Logger.getLogger(FXMLGaitEvaluationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @FXML
    private void editInfo(ActionEvent event){
        edit_BUT.setDisable(true);
        continue_BUT.setDisable(true);
        check_DABUT.setDisable(true);
        yes_Eat.setDisable(false);
        no_Eat.setDisable(false);
        yes_Dress.setDisable(false);
        no_Dress.setDisable(false);
        yes_LA.setDisable(false);
        no_LA.setDisable(false);
        check_BUT.setDisable(false);
        dailyActivities_scoreTF.setText("");
    }
    
    @FXML
    private void yesEatAlone(ActionEvent event){
        if(yes_Eat.isSelected()){
            no_Eat.setSelected(false);
            eat = 1;
        }
    }
    
    @FXML
    private void noEatAlone(ActionEvent event){
        if(no_Eat.isSelected()){
            yes_Eat.setSelected(false);
            eat = 0;
        }
    }
    
    @FXML
    private void yesDress(ActionEvent event){
        if(yes_Dress.isSelected()){
            no_Dress.setSelected(false);
            dress = 1;
        }
    }
    
    @FXML
    private void noDress(ActionEvent event){
        if(no_Dress.isSelected()){
            yes_Dress.setSelected(false);
            dress = 0;
        }
    }
    
    @FXML
    private void yesLalone(ActionEvent event){
        if(yes_LA.isSelected()){
            no_LA.setSelected(false);
            la = 1;
        }
    }
    
    @FXML
    private void noLalone(ActionEvent event){
        if(no_LA.isSelected()){
            yes_LA.setSelected(false);
            la = 0;
        }
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        continue_BUT.setDisable(true);
        check_DABUT.setDisable(true);
        edit_BUT.setDisable(true);
    }    
    
}
