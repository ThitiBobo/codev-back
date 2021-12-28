package com.polytech.codev.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

//TODO à améliorer
public class ElectricityURLBuilder extends OpenDataURLBuilder{

    private static final String dataset = "eco2mix-metropoles-tr";

    private String query;
    private String lang;

    private int rows;
    private int start;

    private boolean ascendingSort;
    private String sort;

    private Set<String> facets;

    private Map<String, String> refine;

    private Map<String, String> exclude;

    private String timeZone;

    public ElectricityURLBuilder(){
        super(ElectricityURLBuilder.dataset);
        this.query = "";
        this.rows = 10;
        this.start = 0;
        this.facets = new LinkedHashSet<>();
        this.refine = new LinkedHashMap<>();
        this.exclude = new LinkedHashMap<>();
    }

    public String getLang() {
        return lang;
    }

    public ElectricityURLBuilder setLang(String lang) {
        this.lang = lang;
        return this;
    }

    public int getRows() {
        return rows;
    }

    public ElectricityURLBuilder setRows(int rows) {
        this.rows = rows;
        return this;
    }

    public int getStart() {
        return start;
    }

    public ElectricityURLBuilder setStart(int start) {
        this.start = start;
        return this;
    }

    public String getSort() {
        return sort;
    }

    public ElectricityURLBuilder setSort(String sort, boolean ascending) {
        this.ascendingSort = ascending;
        this.sort = sort;
        return this;
    }

    public ElectricityURLBuilder setSort(String sort) {
        this.setSort(sort, true);
        return this;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public ElectricityURLBuilder setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public ElectricityURLBuilder addFacet(String facet){
        this.facets.add(facet);
        return this;
    }

    public ElectricityURLBuilder addRefine(String key, String value){
        this.refine.put(key, value);
        return this;
    }

    public ElectricityURLBuilder addExclude(String key, String value){
        this.exclude.put(key, value);
        return this;
    }

    private String buildURL(){
        StringBuilder builder = new StringBuilder();

        builder.append(this.baseURL).append("?");
        builder.append("dataset=").append(super.dataset);

        builder.append("&q=").append(this.query);

        if (this.lang != null) builder.append("&lang=").append(lang);

        builder.append("&rows=").append(rows);
        if(this.start != 0) builder.append("&start=").append(start);

        if (this.sort != null){
            builder.append("&sort=");
            if (!this.ascendingSort) builder.append("-");
            builder.append(sort);
        }

        facets.forEach((value) -> builder.append("&facet=").append(value));

        refine.forEach((key, value) -> builder.append("&refine.").append(key).append("=").append(value));

        exclude.forEach((key, value) -> builder.append("&exclude.").append(key).append("=").append(value));

        if (this.timeZone != null) builder.append("&timezone=").append(timeZone);

        return builder.toString();
    }

    public URL getURL() throws MalformedURLException {
        return new URL(this.buildURL().toString());
    }

    @Override
    public String toString() {
        return this.buildURL();
    }
}
