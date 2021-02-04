/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinsonclassifier;

import java.io.IOException;
import static java.lang.System.exit;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import net.sf.clipsrules.jni.CLIPSException;
import pojo.Fecha;
import pojo.Patient;
import pojo.PatientsList;


public class FXMLDiagnosisVistaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField ps_TF;
    @FXML
    private TextField das_TF;
    @FXML
    private TextField gs_TF;
    @FXML
    private TextField ts_TF;
    @FXML
    private TextField vas_TF;
    @FXML
    private TextField finalScore_TF;
    @FXML
    private TextField stage_TF;
    @FXML
    private TextField treatment_TF;
    @FXML
    private Button finalScore_BUT;
    @FXML
    private Button treatment_BUT;
    @FXML
    private Button newP_BUT;
    @FXML
    private Button exit_BUT;
    @FXML
    private Button save_BUT;
    @FXML
    private Text message_txt;
    
    
   
    
    @FXML 
    private void savePatientsInfo(ActionEvent event){
        Fecha date = Main.persistence.generateDate();
        Main.patientsList.setDom(date);
        String dia, mes, annio,hour,min;     
        dia = Integer.toString(date.getDia());
        mes = Integer.toString(date.getMes() - 20);
        annio = Integer.toString(date.getAño());
        hour = Integer.toString(date.getHour());
        min = Integer.toString(date.getMin());
            
        String path_summary = hour+"_"+min+"_"+dia+"_"+mes+"_"+annio+"_Summary.txt";
        String path_Info = hour+"_"+min+"_"+dia+"_"+mes+"_"+annio+"_AllInfo.txt";
        
        Main.persistence.saveSummary(path_summary , Main.patientsList);
        Main.persistence.save(path_Info, Main.patientsList);
        
        newP_BUT.setDisable(true);
        save_BUT.setDisable(true);
        message_txt.setText("Patients saved, now you can exit the program");
        exit_BUT.setDisable(false);
        
    }
    
    
    @FXML
    private void addNewPatient(ActionEvent event){
        Parent parent2;
        try { 
            Main.clips.reset(); 
            parent2 = FXMLLoader.load(getClass().getResource("FXMLVistaSides.fxml"));
            Scene scene2 = new Scene(parent2);
            Main.stage_Questions.setScene(scene2);
            Main.stage_Questions.show(); 
            
        } catch (CLIPSException ex) {
            Logger.getLogger(FXMLDiagnosisVistaController.class.getName()).log(Level.SEVERE, null, ex);}
          catch (IOException ex) {
            Logger.getLogger(FXMLGaitEvaluationController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Problems opening Vista Sides");
            }
    }
    
    private static Patient savePatientInfo(){
        Patient pa = new Patient();
        try {
            Main.patient = Main.clips.findFact("Patient");
           
            String name = Main.patient.getSlotValue("name").toString();
            pa.setName(name);
            String surname = Main.patient.getSlotValue("surname").toString();
            pa.setSurname(surname);
            String age = Main.patient.getSlotValue("age").toString();
            pa.setAge(age);
            String sex = Main.patient.getSlotValue("sex").toString();
            pa.setSex(sex);
            String both_sides = Main.patient.getSlotValue("both_sides").toString();
            pa.setBoth_sides(both_sides);
            String gait = Main.patient.getSlotValue("gait").toString();
            pa.setGait_score(gait);
            String daily_activities = Main.patient.getSlotValue("daily_activities").toString();
            pa.setDaily_activities_score(daily_activities);
            String posture = Main.patient.getSlotValue("posture").toString();
            pa.setPosture_score(posture);
            String tremor = Main.patient.getSlotValue("tremor").toString();
            pa.setTremor_score(tremor);
            String voice = Main.patient.getSlotValue("voice").toString();
            pa.setVoice_score(voice);
            String final_score = Main.patient.getSlotValue("final_score").toString();
            pa.setFinal_score(final_score);
            String stage = Main.patient.getSlotValue("stage").toString();
            pa.setStage(stage);
            String limp = Main.patient.getSlotValue("limp").toString();
            pa.setLimp(limp);
            String slow_walking = Main.patient.getSlotValue("slow_walking").toString();
            pa.setSlow_walking(slow_walking);
            String falling_freq = Main.patient.getSlotValue("falling_freq").toString();
            pa.setFalling_freq(falling_freq);
            String eat = Main.patient.getSlotValue("eat").toString();
            pa.setEat(eat);
            String dress = Main.patient.getSlotValue("dress").toString();
            pa.setDress(dress);
            String dependency = Main.patient.getSlotValue("dependency").toString();
            pa.setDependency(dependency);
            String retropulsion = Main.patient.getSlotValue("retropulsion").toString();
            pa.setRetropulsion(retropulsion);
            String pulltest = Main.patient.getSlotValue("pulltest").toString();
            pa.setPulltest(pulltest);
            String stoopedover = Main.patient.getSlotValue("stoopedover").toString();
            pa.setStoopedover(stoopedover);
            String softvoice = Main.patient.getSlotValue("softvoice").toString();
            pa.setSoftvoice(softvoice);
            String stutter = Main.patient.getSlotValue("stutter").toString();
            pa.setStutter(stutter);
            String fasttalk = Main.patient.getSlotValue("fasttalk").toString();
            pa.setFasttalk(fasttalk);
            String diagnosis = Main.patient.getSlotValue("diagnosis").toString();
            pa.setDiagnosis(diagnosis);
            String questions = Main.patient.getSlotValue("questions").toString();
            pa.setQuestions(questions);
            String controlsides = Main.patient.getSlotValue("controlsides").toString();
            pa.setControlsides(controlsides);
            String treatment = Main.patient.getSlotValue("treatment").toString();
            pa.setTreatment(treatment);
            
            System.out.println(pa.toString());
            
        } catch (CLIPSException ex) {
            Logger.getLogger(FXMLDiagnosisVistaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pa; 
    }
    
    
    @FXML
    private void finishQuestionaire(ActionEvent event){
        
        /*String path_Info = "prueba.txt";
        PatientsList prueba;
        try {
            prueba = Main.persistence.load(path_Info);
            for (Patient patient: prueba.getPatientsList()){
                System.out.println(patient.toString());   
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Problems cargando file");
            Logger.getLogger(FXMLDiagnosisVistaController.class.getName()).log(Level.SEVERE, null, ex);
        }
       */

        int length = Main.patientsList.getPatientsList().size();
        System.out.println("The number of patient is: "+ length);
        System.out.println("End program");
        exit(0);
    }
    
    
    @FXML
    private void checkTreatment(ActionEvent event){
        Patient pa;
        try {
            Main.clips.run();
            Main.patient = Main.clips.findFact("Patient");
            String treatment = Main.patient.getSlotValue("treatment").toString();
          
            treatment_TF.setText(treatment);
            treatment_TF.setDisable(true);
            treatment_BUT.setDisable(true);
            
            pa = new Patient();
            pa = savePatientInfo();
            
            message_txt.setText("Save only if you want to exit the program");
            
            Main.patientsList.addPatient(pa);
            newP_BUT.setDisable(false);
            save_BUT.setDisable(false);
            
            } 
        catch (CLIPSException ex) {
            Logger.getLogger(FXMLGaitEvaluationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    @FXML
    private void checkFinalScore(ActionEvent event){
        try {
            Main.clips.run();
            Main.patient = Main.clips.findFact("Patient");
            String final_score = Main.patient.getSlotValue("final_score").toString();
            String stage = Main.patient.getSlotValue("stage").toString();
            
            finalScore_TF.setText(final_score);
            stage_TF.setText(stage);
            
            finalScore_TF.setDisable(true);
            stage_TF.setDisable(true);
            finalScore_BUT.setDisable(true);
            
            treatment_BUT.setDisable(false);
          
            } 
        catch (CLIPSException ex) {
            Logger.getLogger(FXMLGaitEvaluationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Main.patient = Main.clips.findFact("Patient");
        } catch (CLIPSException ex) {
            Logger.getLogger(FXMLDiagnosisVistaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String gait = Main.patient.getSlotValue("gait").toString();
        String daily_activities = Main.patient.getSlotValue("daily_activities").toString();
        String posture = Main.patient.getSlotValue("posture").toString();
        String tremor = Main.patient.getSlotValue("tremor").toString();
        String voice = Main.patient.getSlotValue("voice").toString();
        
        gs_TF.setText(gait);
        das_TF.setText(daily_activities);
        ps_TF.setText(posture);
        ts_TF.setText(tremor);
        vas_TF.setText(voice);
        
        gs_TF.setDisable(true);
        das_TF.setDisable(true);
        ps_TF.setDisable(true);
        ts_TF.setDisable(true);
        vas_TF.setDisable(true);
        
        treatment_BUT.setDisable(true);
        exit_BUT.setDisable(true);
        newP_BUT.setDisable(true);
        save_BUT.setDisable(true);
        
    }    
    
}
