package com.polytech.codev.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.polytech.codev.model.Data;
import com.polytech.codev.util.ElectricityURLBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.polytech.codev.model.api.Record;

@Service
public class DataService {

    public List<Data> listConsumption() throws IOException, JSONException {

        List<String> codeMetropolis = new ArrayList<>();
        List<Record> recordList = new ArrayList<>();

        int page = 0;

        while (codeMetropolis.size() != 21) {
            ElectricityURLBuilder builder = new ElectricityURLBuilder();
            builder.setQuery("consommation>=0");
            builder.setRows(21).setStart(page).setSort("date_heure");
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
        }
        
        List<Data> data = new ArrayList<>();
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        recordList.forEach(o -> {
            try {
                data.add(new Data(
                        o.getFields().getCode_insee_epci(),
                        o.getFields().getLibelle_metropole(),
                        o.getFields().getConsommation(),
                        dateTimeFormatter.parse(o.getFields().getDate_heure()),
                        dateFormatter.parse(o.getFields().getDate()),
                        timeFormatter.parse(o.getFields().getHeures())
                ));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        return data;
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
