package com.polytech.codev.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.polytech.codev.model.Consumption;
import com.polytech.codev.model.Data;
import com.polytech.codev.model.DataDetail;
import com.polytech.codev.model.Metropolis;
import com.polytech.codev.repository.MetropolisRepository;
import com.polytech.codev.util.ElectricityURLBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.polytech.codev.model.api.Record;

@Service
public class DataService {

    private final MetropolisRepository metropolisRepository;

    @Autowired
    public DataService(MetropolisRepository metropolisRepository) {
        this.metropolisRepository = metropolisRepository;
    }

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

        while (codeMetropolis.size() < 20 && page < 3 ) {
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

        if (codeMetropolis.size() != 21){
            List<Metropolis> metropolises = this.metropolisRepository.findAll();
            Iterator iterator = metropolises.iterator();
            while (iterator.hasNext() && codeMetropolis.size() < 21){
                String code = ((Metropolis)iterator.next()).getCode();
                if(!codeMetropolis.contains(code)){
                    data.add(this.getConsumption(code));
                    codeMetropolis.add(code);
                }
            }
        }

        data.sort(Comparator.comparing(Data::getDate_hour).reversed());
        return data;
    }

    public DataDetail getConsumptionPeriod(long code, int number, String start, String end){
        // query = consommation>=0 AND libelle_metropole:"Métropole du Grand Nancy" AND date_heure>="2021-12-03T16:15:00.000+00:00"
        // query = date_heure>="2022-01-03T14:00:00+00:00" AND libelle_metropole:"Métropole du Grand Nancy"
        // date_heure = 2021-12-29

        DataDetail data = new DataDetail();
        List<Consumption> values = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        StringBuilder builder = new StringBuilder();
        builder.append("consommation>=0 AND code_insee_epci:").append(code);
        builder.append(" AND date_heure>=\"").append(start).append('"');
        builder.append(" AND date_heure<=\"").append(end).append('"');

        ElectricityURLBuilder electricityURLBuilder = new ElectricityURLBuilder();
        electricityURLBuilder.setQuery(builder.toString());
        electricityURLBuilder.setRows(number);

        URL url = null;
        try {
            url = electricityURLBuilder.getURL();
            System.out.println(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JSONObject json = null;
        try {
            json = new JSONObject(this.urlGet(url));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Gson gson = new GsonBuilder().create();
            JSONArray records = json.getJSONArray("records");
            SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            for (int i = 0; i < records.length(); i++) {
                try {
                    Date date = dateTimeFormatter.parse(records.getJSONObject(i).getJSONObject("fields").get("date_heure").toString());
                    Double val = Double.valueOf(records.getJSONObject(i).getJSONObject("fields").get("consommation").toString());
                    values.add(new Consumption(date, val));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Collections.sort(values);
        data.setHistoric(values);
        return data;
    }

    public DataDetail getConsumptionPeriod(long id, int number, String start){
        return this.getConsumptionPeriod(id, number, start, null);
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
