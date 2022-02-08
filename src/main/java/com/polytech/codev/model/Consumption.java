package com.polytech.codev.model;

import java.util.Date;

public class Consumption implements Comparable{

    private Date date;

    private double value;

    public Consumption(Date date, double value) {
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        Consumption consumption = (Consumption) o;
        return this.date.compareTo(consumption.date);
    }
}
