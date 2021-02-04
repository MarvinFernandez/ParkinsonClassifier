/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinsonclassifier;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.clipsrules.jni.Environment;
import net.sf.clipsrules.jni.FactAddressValue;
import persistence.Persistence;
import pojo.PatientsList;


public class Main extends Application {
    
    private Stage primaryStage = new Stage();
    public static Stage stage_Questions = new Stage(); 
    
    public static Environment clips;
    public static  FactAddressValue patient;
    public static PatientsList patientsList = new PatientsList();
    public static Persistence persistence = new Persistence();
    
    
    
    
    
    @Override
    public void start(Stage stage) throws Exception{
        this.primaryStage = stage;
        this.primaryStage.initStyle(StageStyle.UTILITY);
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("Parkinson Classifier");
        initProgram();
    }
    
    public void initProgram(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLVistaInit.fxml"));
            Scene scene = new Scene(root);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Problems with Init Vista");
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
