package com.anthonytepach.app.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class M_Posts {
    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("cuerpo")
    @Expose
    private String cuerpo;
    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("image_post")
    @Expose
    private String imagePost;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImagePost() {
        return imagePost;
    }

    public void setImagePost(String imagePost) {
        this.imagePost = imagePost;
    }
}
