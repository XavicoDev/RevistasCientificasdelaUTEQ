package com.example.revistascientficasdelauteq.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Revista {
    @SerializedName("journal_id")
    @Expose
    private String journalId;
    @SerializedName("portada")
    @Expose
    private String portada;
    @SerializedName("abbreviation")
    @Expose
    private String abbreviation;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("journalThumbnail")
    @Expose
    private String journalThumbnail;
    @SerializedName("name")
    @Expose
    private String name;

    public String getJournalId() {
        return journalId;
    }

    public void setJournalId(String journalId) {
        this.journalId = journalId;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJournalThumbnail() {
        return journalThumbnail;
    }

    public void setJournalThumbnail(String journalThumbnail) {
        this.journalThumbnail = journalThumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Revista(String journalId, String name, String description, String portada) {
        this.journalId = journalId;
        this.portada = portada;
        this.description = description;
        this.name = name;
    }

    public Revista(JSONObject item) throws JSONException {
        journalId=item.getString("journal_id");
        name= item.getString("name");
        description= item.getString("description");
        portada= item.getString("portada");
    }

    public static ArrayList<Revista> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Revista> revistas = new ArrayList<>();

        for (int i = 0; i < datos.length() ; i++) {
            revistas.add(new Revista(datos.getJSONObject(i)));
        }
        return revistas;
    }
}
