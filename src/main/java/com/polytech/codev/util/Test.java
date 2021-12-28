package com.polytech.codev.util;

public class Test {

    public static void main(String[] args) {
        ElectricityURLBuilder builder = new ElectricityURLBuilder();

        builder.setLang("FR");
        builder.setRows(2);
        builder.setStart(10);

        builder.setSort("date_heure", false);
        builder.setTimeZone("Europe");

        System.out.println(builder);
        System.out.println("https://opendata.reseaux-energies.fr/api/records/1.0/search/?dataset=eco2mix-metropoles-tr&q=&lang=FR&rows=2&start=10&sort=-date_heure&timezone=Europe");

        System.out.println(builder.toString().compareTo("https://opendata.reseaux-energies.fr/api/records/1.0/search/?dataset=eco2mix-metropoles-tr&q=&lang=FR&rows=2&start=10&sort=-date_heure&timezone=Europe"));


        System.out.println("----------------------");
        builder = new ElectricityURLBuilder();

        builder.setLang("FR");
        builder.setRows(2);
        builder.setSort("date_heure", false);

        builder.addFacet("libelle_metropole");
        builder.addFacet("nature");
        builder.addFacet("date_heure");

        builder.addRefine("date_heure","5");
        builder.addRefine("nature","bg10");

        builder.addExclude("nature", "coucou");

        builder.setTimeZone("Europe");

        String res = "https://opendata.reseaux-energies.fr/api/records/1.0/search/?dataset=eco2mix-metropoles-tr&q=&lang=FR&rows=2&sort=-date_heure&facet=libelle_metropole&facet=nature&facet=date_heure&refine.date_heure=5&refine.nature=bg10&exclude.nature=coucou&timezone=Europe";
        System.out.println(builder);
        System.out.println(res);
        System.out.println(builder.toString().compareTo(res));
    }

}
