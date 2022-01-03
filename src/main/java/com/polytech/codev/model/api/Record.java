package com.polytech.codev.model.api;

public class Record {
    private String datasetid;
    private String recordid;
    private String record_timestamp;

    private Fields fields;

    public static class Fields{
        private String libelle_metropole;
        private String heures;
        private String nature;
        private String date;
        private Double consommation;
        private String code_insee_epci;
        private String date_heure;

        public Fields(){}

        public Fields(String libelle_metropole, String heures, String nature, String date, Double consommation, String code_insee_epci, String date_heure) {
            this.libelle_metropole = libelle_metropole;
            this.heures = heures;
            this.nature = nature;
            this.date = date;
            this.consommation = consommation;
            this.code_insee_epci = code_insee_epci;
            this.date_heure = date_heure;
        }

        public String getLibelle_metropole() {
            return libelle_metropole;
        }

        public void setLibelle_metropole(String libelle_metropole) {
            this.libelle_metropole = libelle_metropole;
        }

        public String getHeures() {
            return heures;
        }

        public void setHeures(String heures) {
            this.heures = heures;
        }

        public String getNature() {
            return nature;
        }

        public void setNature(String nature) {
            this.nature = nature;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Double getConsommation() {
            return consommation;
        }

        public void setConsommation(Double consommation) {
            this.consommation = consommation;
        }

        public String getCode_insee_epci() {
            return code_insee_epci;
        }

        public void setCode_insee_epci(String code_insee_epci) {
            this.code_insee_epci = code_insee_epci;
        }

        public String getDate_heure() {
            return date_heure;
        }

        public void setDate_heure(String date_heure) {
            this.date_heure = date_heure;
        }
    }

    public Record(){}

    public Record(String datasetid, String recordid, String record_timestamp, Fields fields) {
        this.datasetid = datasetid;
        this.recordid = recordid;
        this.record_timestamp = record_timestamp;
        this.fields = fields;
    }

    public String getDatasetid() {
        return datasetid;
    }

    public void setDatasetid(String datasetid) {
        this.datasetid = datasetid;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public String getRecord_timestamp() {
        return record_timestamp;
    }

    public void setRecord_timestamp(String record_timestamp) {
        this.record_timestamp = record_timestamp;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }
}
