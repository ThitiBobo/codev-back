package com.polytech.codev.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.polytech.codev.model.Data;
import com.polytech.codev.model.DataDetail;
import com.polytech.codev.util.ElectricityURLBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.polytech.codev.model.api.Record;

@Service
public class DataService {

    public Data getConsumption(String metropolisCode) throws IOException, JSONException {
        ElectricityURLBuilder builder = new ElectricityURLBuilder();
        builder.setQuery("consommation>=0 AND code_insee_epci:" + metropolisCode.split(" ")[0]);
        builder.setRows(1).setSort("date_heure");
        URL url = builder.getURL();
        JSONObject json = new JSONObject(this.urlGet(url));
        Gson gson = new GsonBuilder().create();
        Record record = gson.fromJson(
                json.getJSONArray("records").getJSONObject(0).toString(),
                Record.class);
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        try {
            return new Data(
                    record.getFields().getCode_insee_epci(),
                    record.getFields().getLibelle_metropole(),
                    record.getFields().getConsommation(),
                    dateTimeFormatter.parse(record.getFields().getDate_heure())
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Data> listConsumption() throws IOException, JSONException {

        List<String> codeMetropolis = new ArrayList<>();
        List<Record> recordList = new ArrayList<>();

        int page = 0;
        int rows = 21;

        while (codeMetropolis.size() != 21 && page < 4) {
            ElectricityURLBuilder builder = new ElectricityURLBuilder();
            builder.setQuery("consommation>=0");
            builder.setRows(rows).setStart(page * 21).setSort("date_heure");
            URL url = builder.getURL();
            JSONObject json = new JSONObject(this.urlGet(url));

            Gson gson = new GsonBuilder().create();
            JSONArray records = json.getJSONArray("records");

            for (int i = 0; i < records.length(); i++) {
                String code = records.getJSONObject(i).getJSONObject("fields").get("code_insee_epci").toString();
                if (!codeMetropolis.contains(code)) {
                    recordList.add(gson.fromJson(records.getJSONObject(i).toString(), Record.class));
                    codeMetropolis.add(code);
                }
            }
            page++;
            rows = rows * 2;
        }

        List<Data> data = new ArrayList<>();
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        recordList.forEach(o -> {
            try {
                data.add(new Data(
                        o.getFields().getCode_insee_epci(),
                        o.getFields().getLibelle_metropole(),
                        o.getFields().getConsommation(),
                        dateTimeFormatter.parse(o.getFields().getDate_heure())
                ));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        return data;
    }

    public DataDetail getConsumptionPeriod(String start, String end){
        // query = consommation>=0 AND libelle_metropole:"Métropole du Grand Nancy" AND date_heure>="2021-12-03T16:15:00.000+00:00"
        // query = date_heure>="2022-01-03T14:00:00+00:00" AND libelle_metropole:"Métropole du Grand Nancy"
        return null;
    }

    public DataDetail getConsumptionPeriod(String start){
        return this.getConsumptionPeriod(start, null);
    }

    private String urlGet(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        String data = "";
        if (connection.getResponseCode() == 200) {
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                data += scanner.nextLine();
            }
            scanner.close();
        }
        connection.disconnect();
        return data;
    }
}
