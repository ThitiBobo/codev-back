package com.polytech.codev.appels;

import com.polytech.codev.util.ElectricityURLBuilder;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class AppelApi {

    public String getData() throws Exception {
        String data = "";

        ElectricityURLBuilder builder = new ElectricityURLBuilder();
        builder.setSort("code_insee_epci", false);
        builder.addFacet("libelle_metropole").addFacet("nature").addFacet("date_heure").addFacet("date");
        URL url = builder.getURL();
        //new URL("https://opendata.reseaux-energies.fr/api/records/1.0/search/?dataset=eco2mix-metropoles-tr&q=&sort=-code_insee_epci&facet=libelle_metropole&facet=nature&facet=date_heure&facet=date");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responsecode = conn.getResponseCode();

        if(responsecode == 200) {
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                data += scanner.nextLine();
            }

            scanner.close();
        }

        conn.disconnect();

        return data;
    }

}
