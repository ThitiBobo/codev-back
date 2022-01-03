package com.polytech.codev.model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {

    private String code;

    private String metropolis;

    private Double consumption;

    private Date date_hour;


    public Data(){}

    public Data(String code, String metropolis, Double consumption, Date date_hour) {
        this.code = code;
        this.metropolis = metropolis;
        this.consumption = consumption;
        this.date_hour = date_hour;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMetropolis() {
        return metropolis;
    }

    public void setMetropolis(String metropolis) {
        this.metropolis = metropolis;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    public Date getDate_hour() {
        return date_hour;
    }

    public void setDate_hour(Date date_hour) {
        this.date_hour = date_hour;
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date_hour);
    }

    public String getHour() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date_hour);
    }

}
