/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Fecha;
import pojo.Patient;
import pojo.PatientsList;



public class Persistence {
    
    
    public Persistence() {
    }
  
    
    public PatientsList load(String path) throws ClassNotFoundException {
        File f = null;
        FileInputStream fileInputStream = null;     
        ObjectInputStream objectInputStream = null;
        PatientsList p1=null;

        try{    
                f = new File(path);
                fileInputStream = new FileInputStream (f);     
                objectInputStream = new ObjectInputStream(fileInputStream);
                p1=(PatientsList)objectInputStream.readObject();
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        finally{
            if(objectInputStream!=null){
               try{
               objectInputStream.close();
               }
               catch(IOException ex){
               System.out.println(ex);          
                }
            }
            
        if(fileInputStream!=null){
              try{
                fileInputStream.close();}
              catch(IOException ex){
                  System.out.println(ex);}}
        }
        return p1; 
    }
        
        
     public void save(String path,PatientsList p1){// binario
        File f;
        FileOutputStream fileOutputStream = null;     
        ObjectOutputStream objectOutputStream = null;  
      
        try{
            f = new File(path);
            fileOutputStream = new FileOutputStream (f);     
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(p1);
            System.out.println("Saved AllInfo");
        }
      
        catch(IOException ex){
          System.out.println("Error writing the file");
        }
      
        finally{
            if(objectOutputStream!=null){
                try{
                    objectOutputStream.close();
                    }
                catch(IOException ex){
                    System.out.println(ex);;
                }
             }
            
            if(fileOutputStream!=null){
                try{
                    fileOutputStream.close();}
                catch(IOException ex){
                    System.out.println(ex);
                }
            }
        }
    }
    
    
    public void saveSummary(String id,PatientsList patientsList){
        PrintWriter pw = null ;
      
        try {
            pw = new PrintWriter(id);
            pw.println("                      Results Summary                    \n");
            
            for (Patient patient: patientsList.getPatientsList()){
                pw.println(patient.toString());
                System.out.println(patient.toString());   
            }
            
            System.out.println("Saved Summary");
            
        } catch (IOException ex) {
            System.out.println("Problems saving");
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            pw.close();
        }
    
    }
    
     
    public Fecha generateDate(){
            Date d = new Date();
            Calendar c = new GregorianCalendar(); 
            c.setTime(d);
            int dia, mes, annio,hour,min;
            dia = c.get(Calendar.DATE);
            mes = c.get(Calendar.MONTH);
            annio = c.get(Calendar.YEAR);
            hour = c.get(Calendar.HOUR);
            min = c.get(Calendar.MINUTE);
            Fecha today=new Fecha(dia,mes,annio,hour,min);
            return today;
    }
    
  
     
}
    
    
 