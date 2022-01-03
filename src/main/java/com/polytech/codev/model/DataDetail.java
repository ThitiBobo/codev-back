package com.polytech.codev.model;

import java.util.*;

public class DataDetail extends Data{

    private Map<Date,Double> historic;

    public DataDetail() {
        super();
        this.historic = new HashMap<>();
    }

    public DataDetail(String code,
                      String metropolis,
                      Double consumption,
                      Date date_hour,
                      Map<Date, Double> historic) {
        super(code, metropolis, consumption, date_hour);
        this.historic = historic;
    }

    public Map<Date, Double> getHistoric() {
        return historic;
    }

    public void setHistoric(Map<Date, Double> historic) {
        this.historic = historic;
    }
}
