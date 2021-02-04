/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;

/**
 *
 * @author diego
 */
public class Fecha implements Serializable{
    private int dia;
    private int mes;
    private int año;
    private int hour;
    private int min;
    
    public Fecha(){
        this.dia=0;
        this.mes=0;
        this.año=0;
        this.hour=0;
        this.min=0;
    }
    
    public Fecha(int day, int month, int year,int hour,int min){
        this.dia=day;
        this.mes=month;
        this.año=year;
        this.hour=hour;
        this.mes=min;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "Fecha{" + "dia=" + dia + ", mes=" + mes + ", a\u00f1o=" + año + ", hour=" + hour + ", min=" + min + '}';
    }
    
    
    
    
    
}
