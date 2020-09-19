package com.example.revistascientficasdelauteq.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Articulo {
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("publication_id")
    @Expose
    private String publicationId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("doi")
    @Expose
    private String doi;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("date_published")
    @Expose
    private String datePublished;
    @SerializedName("submission_id")
    @Expose
    private String submissionId;
    @SerializedName("section_id")
    @Expose
    private String sectionId;
    @SerializedName("seq")
    @Expose
    private String seq;
    @SerializedName("galeys")
    @Expose
    private List<Galey> galeys = null;
    @SerializedName("keywords")
    @Expose
    private List<Keyword> keywords = null;
    @SerializedName("authors")
    @Expose
    private List<Author> authors = null;


    public Articulo(String section, String publicationId, String title, String doi, String _abstract, String datePublished, String submissionId, String sectionId, String seq, List<Galey> galeys, List<Keyword> keywords, List<Author> authors) {
        this.section = section;
        this.publicationId = publicationId;
        this.title = title;
        this.doi = doi;
        this._abstract = _abstract;
        this.datePublished = datePublished;
        this.submissionId = submissionId;
        this.sectionId = sectionId;
        this.seq = seq;
        this.galeys = galeys;
        this.keywords = keywords;
        this.authors = authors;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
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

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public List<Galey> getGaleys() {
        return galeys;
    }

    public void setGaleys(List<Galey> galeys) {
        this.galeys = galeys;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Articulo(JSONObject item) throws JSONException {
        section= item.getString("section");
        publicationId= item.getString("publication_id");
        title= item.getString("title");
        doi= item.getString("doi");
        _abstract= item.getString("abstract");
        datePublished= item.getString("date_published");
        submissionId=item.getString("submission_id");
        sectionId= item.getString("section_id");
        seq=item.getString("seq");
        //galeys=item.getString("galeys");
        //keywords=item.getString("keywords");
        //authors=item.getString("authors");
    }
    public static ArrayList<Articulo> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Articulo> revistas = new ArrayList<>();
        for (int i = 0; i < datos.length() ; i++) {
            revistas.add(new Articulo(datos.getJSONObject(i)));
        }
        return revistas;
    }
}
