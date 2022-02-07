package com.polytech.codev.model;

import java.util.*;

public class DataDetail extends Data{

    private List<Consumption> historic;

    public DataDetail() {
        super();
        this.historic = new ArrayList<>();
    }

    public DataDetail(List<Consumption> historic) {
        this.historic = historic;
    }

    public DataDetail(String code, String metropolis, Double consumption, Date date_hour, List<Consumption> historic) {
        super(code, metropolis, consumption, date_hour);
        this.historic = historic;
    }

    public List<Consumption> getHistoric() {
        return historic;
    }

    public void setHistoric(List<Consumption> historic) {
        this.historic = historic;
    }
}
