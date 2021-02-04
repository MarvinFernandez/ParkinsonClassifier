/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;

public class Patient implements Serializable,  Comparable<Patient>  {
    
    private String name;
    private String surname;
    private String age;
    private String sex;
    private String both_sides;
    private String tremor_score;
    private String posture_score;
    private String daily_activities_score;
    private String gait_score;
    private String voice_score;
    private String final_score;
    private String stage;
    private String limp;
    private String slow_walking;
    private String falling_freq;
    private String eat;
    private String dress;
    private String dependency;
    private String retropulsion;
    private String pulltest;
    private String stoopedover;
    private String softvoice;
    private String stutter;
    private String fasttalk;
    private String diagnosis;
    private String questions;
    private String controlsides;
    private String treatment;

    public Patient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBoth_sides() {
        return both_sides;
    }

    public void setBoth_sides(String both_sides) {
        this.both_sides = both_sides;
    }

    public String getTremor_score() {
        return tremor_score;
    }

    public void setTremor_score(String tremor_score) {
        this.tremor_score = tremor_score;
    }

    public String getPosture_score() {
        return posture_score;
    }

    public void setPosture_score(String posture_score) {
        this.posture_score = posture_score;
    }

    public String getDaily_activities_score() {
        return daily_activities_score;
    }

    public void setDaily_activities_score(String daily_activities_score) {
        this.daily_activities_score = daily_activities_score;
    }

    public String getGait_score() {
        return gait_score;
    }

    public void setGait_score(String gait_score) {
        this.gait_score = gait_score;
    }

    public String getVoice_score() {
        return voice_score;
    }

    public void setVoice_score(String voice_score) {
        this.voice_score = voice_score;
    }

    public String getFinal_score() {
        return final_score;
    }

    public void setFinal_score(String final_score) {
        this.final_score = final_score;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getLimp() {
        return limp;
    }

    public void setLimp(String limp) {
        this.limp = limp;
    }

    public String getSlow_walking() {
        return slow_walking;
    }

    public void setSlow_walking(String slow_walking) {
        this.slow_walking = slow_walking;
    }

    public String getFalling_freq() {
        return falling_freq;
    }

    public void setFalling_freq(String falling_freq) {
        this.falling_freq = falling_freq;
    }

    public String getEat() {
        return eat;
    }

    public void setEat(String eat) {
        this.eat = eat;
    }

    public String getDress() {
        return dress;
    }

    public void setDress(String dress) {
        this.dress = dress;
    }

    public String getDependency() {
        return dependency;
    }

    public void setDependency(String dependency) {
        this.dependency = dependency;
    }

    public String getRetropulsion() {
        return retropulsion;
    }

    public void setRetropulsion(String retropulsion) {
        this.retropulsion = retropulsion;
    }

    public String getPulltest() {
        return pulltest;
    }

    public void setPulltest(String pulltest) {
        this.pulltest = pulltest;
    }

    public String getStoopedover() {
        return stoopedover;
    }

    public void setStoopedover(String stoopedover) {
        this.stoopedover = stoopedover;
    }

    public String getSoftvoice() {
        return softvoice;
    }

    public void setSoftvoice(String softvoice) {
        this.softvoice = softvoice;
    }

    public String getStutter() {
        return stutter;
    }

    public void setStutter(String stutter) {
        this.stutter = stutter;
    }

    public String getFasttalk() {
        return fasttalk;
    }

    public void setFasttalk(String fasttalk) {
        this.fasttalk = fasttalk;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getControlsides() {
        return controlsides;
    }

    public void setControlsides(String controlsides) {
        this.controlsides = controlsides;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    

    @Override
    public String toString() {
        return "Patient{" + "name=" + name + ", surname=" + surname + ", age=" + age 
                + ",\n tremor_score=" + tremor_score + ", posture_score=" + posture_score
                + ", \n daily_activities_score=" + daily_activities_score + ", gait_score=" +
                gait_score + ", \n voice_score=" + voice_score + ", final_score=" + 
                final_score + ", \n stage=" + stage + ", treatment=" + treatment + "}\n";
    }

    @Override
    public int compareTo(Patient o) {
       if(this.getName().equals(o.getName())){
           return -1;
       }else{
           return this.getName().compareTo(o.getName());
       }
    }
    
    
    
    
    
    
    
}
