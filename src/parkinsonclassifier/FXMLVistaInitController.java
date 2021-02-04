/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinsonclassifier;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import net.sf.clipsrules.jni.*;


public class FXMLVistaInitController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button start_BUT;
    
    
    @FXML
    private void startProgram(ActionEvent event){
        Boolean loadCLP =  false;
        System.out.println("Hello lets begin");
        Main.clips = new Environment();
        
        try {
            Main.clips.clear();
            Main.clips.load("project3.CLP"); //as we load the clp file to netbeans
            Main.clips.reset();
            Main.clips.run();
            loadCLP = true;
      
        } catch (CLIPSLoadException ex) {
            System.out.println("Problem loading the .CLP");
            Logger.getLogger(FXMLVistaInitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CLIPSException ex) {
            Logger.getLogger(FXMLVistaInitController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        if(loadCLP){
            try {
                ((Node)(event.getSource())).getScene().getWindow().hide();
                Parent parent = FXMLLoader.load(getClass().getResource("FXMLVistaSides.fxml"));
                Scene scene = new Scene(parent);
                Main.stage_Questions.setScene(scene);
                Main.stage_Questions.setTitle("Parkinson Classifier");
                Main.stage_Questions.show();
              
            } catch (IOException ex) {
                Logger.getLogger(FXMLVistaInitController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Problems with Vista Sides");
                System.out.println(ex);
            }
        
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
