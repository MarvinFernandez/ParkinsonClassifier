/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;


public class PatientsList implements Serializable {
    
    private Set<Patient> patientsList = new TreeSet();
    
    private Fecha dom;

    public PatientsList() {
    }

    public void setDom(Fecha dom) {
        this.dom = dom;
    }

    public Fecha getDom() {
        return dom;
    }
    
    public Set<Patient> getPatientsList() {
        return patientsList;
    }

    public void setPatientsList(Set<Patient> patientsList) {
        this.patientsList = patientsList;
    }
    
    public void addPatient(Patient patient){
        this.patientsList.add(patient);
    }
    
    public void removePatient(Patient patient){
        this.patientsList.remove(patient);
    }
    
    
    
}
