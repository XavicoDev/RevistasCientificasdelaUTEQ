package com.example.revistascientficasdelauteq.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Seccion {
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("section_id")
    @Expose
    private String sectionId;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public Seccion(String section, String sectionId) {
        this.section = section;
        this.sectionId = sectionId;
    }

    public Seccion(JSONObject item) throws JSONException {
        section=item.getString("section");
        sectionId= item.getString("section_id");
    }

    public static ArrayList<Seccion> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Seccion> ediciones = new ArrayList<>();
        for (int i = 0; i < datos.length() ; i++) {
            ediciones.add(new Seccion(datos.getJSONObject(i)));
        }
        return ediciones;
    }
}
