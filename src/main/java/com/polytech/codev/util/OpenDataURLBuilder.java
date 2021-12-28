package com.polytech.codev.util;

public abstract class OpenDataURLBuilder {

    protected static final String baseURL = "https://opendata.reseaux-energies.fr/api/records/1.0/search/";

    protected String dataset;

    public OpenDataURLBuilder(String dataset) {
        this.dataset = dataset;
    }
}
