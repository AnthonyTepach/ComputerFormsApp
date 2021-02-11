package com.anthonytepach.app.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class M_Personal {
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("apat")
    @Expose
    private String apat;
    @SerializedName("amat")
    @Expose
    private String amat;
    @SerializedName("image_profile")
    @Expose
    private String imageProfile;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApat() {
        return apat;
    }

    public void setApat(String apat) {
        this.apat = apat;
    }

    public String getAmat() {
        return amat;
    }

    public void setAmat(String amat) {
        this.amat = amat;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }
}