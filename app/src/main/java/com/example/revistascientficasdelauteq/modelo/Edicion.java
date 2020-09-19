package com.example.revistascientficasdelauteq.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Edicion {
    @SerializedName("issue_id")
    @Expose
    private String issueId;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("date_published")
    @Expose
    private String datePublished;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("doi")
    @Expose
    private String doi;
    @SerializedName("cover")
    @Expose
    private String cover;

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }


    public Edicion(JSONObject item) throws JSONException {
        issueId=item.getString("issue_id");
        volume= item.getString("volume");
        number= item.getString("number");
        year= item.getString("year");
        datePublished= item.getString("date_published");
        title= item.getString("title");
        doi= item.getString("doi");
        cover= item.getString("cover");
    }

    public static ArrayList<Edicion> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Edicion> ediciones = new ArrayList<>();
        for (int i = 0; i < datos.length() ; i++) {
            ediciones.add(new Edicion(datos.getJSONObject(i)));
        }
        return ediciones;
    }

}
