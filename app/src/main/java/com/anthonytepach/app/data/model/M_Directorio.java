package com.anthonytepach.app.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class M_Directorio {

    @SerializedName("id_personal")
    @Expose
    private String idPersonal;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("apat")
    @Expose
    private String apat;
    @SerializedName("amat")
    @Expose
    private String amat;
    @SerializedName("cel")
    @Expose
    private String cel;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("puesto")
    @Expose
    private String puesto;
    @SerializedName("departamento")
    @Expose
    private String departamento;
    @SerializedName("num_empleado")
    @Expose
    private String numEmpleado;
    @SerializedName("pago")
    @Expose
    private String pago;
    @SerializedName("image_profile")
    @Expose
    private String imageProfile;

    public String getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(String idPersonal) {
        this.idPersonal = idPersonal;
    }

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

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(String numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }
}
