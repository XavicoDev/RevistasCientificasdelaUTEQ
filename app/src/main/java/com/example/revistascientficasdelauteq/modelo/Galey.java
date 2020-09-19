package com.example.revistascientficasdelauteq.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Galey {
    @SerializedName("galley_id")
    @Expose
    private String galleyId;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("file_id")
    @Expose
    private String fileId;
    @SerializedName("UrlViewGalley")
    @Expose
    private String urlViewGalley;

    public String getGalleyId() {
        return galleyId;
    }

    public void setGalleyId(String galleyId) {
        this.galleyId = galleyId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getUrlViewGalley() {
        return urlViewGalley;
    }

    public void setUrlViewGalley(String urlViewGalley) {
        this.urlViewGalley = urlViewGalley;
    }
}
