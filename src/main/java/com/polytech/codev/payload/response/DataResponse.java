package com.polytech.codev.payload.response;

import com.polytech.codev.model.Data;

import java.util.ArrayList;
import java.util.List;

public class DataResponse {

    private List<Data> preferences;

    private List<Data> recentData;

    private List<Data> otherData;

    public DataResponse(){
        this.preferences = new ArrayList<>();
        this.recentData = new ArrayList<>();
        this.otherData = new ArrayList<>();
    }

    public DataResponse(List<Data> preferences, List<Data> recentData, List<Data> otherData) {
        this.preferences = preferences;
        this.recentData = recentData;
        this.otherData = otherData;
    }

    public List<Data> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Data> preferences) {
        this.preferences = preferences;
    }

    public List<Data> getRecentData() {
        return recentData;
    }

    public void setRecentData(List<Data> recentData) {
        this.recentData = recentData;
    }

    public List<Data> getOtherData() {
        return otherData;
    }

    public void setOtherData(List<Data> otherData) {
        this.otherData = otherData;
    }

    public void addPreferenceData( Data data){
        this.preferences.add(data);
    }

    public void addRecentData( Data data){
        this.recentData.add(data);
    }

    public void addOtherData( Data data){
        this.otherData.add(data);
    }
}
