package model;

import java.time.LocalDate;

public class Registro {
    //Variables
    private LocalDate fecha;
    private int codPostal;
    private String dniCliente;
    private int idRepartidor;

    //Método constructor
    public Registro(LocalDate fecha, int codPostal, String dniCliente, int idRepartidor) {
        this.fecha = fecha;
        this.codPostal = codPostal;
        this.dniCliente = dniCliente;
        this.idRepartidor = idRepartidor;
    }

    //Getter y setter
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public int getCodPostal() {
        return codPostal;
    }
    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }
    public String getDniCliente() {
        return dniCliente;
    }
    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }
    public int getIdRepartidor() {
        return idRepartidor;
    }
    public void setIdRepartidor(int idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    //Método toString
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Registro{");
        sb.append("fecha=").append(fecha);
        sb.append(", codPostal=").append(codPostal);
        sb.append(", dniCliente='").append(dniCliente).append('\'');
        sb.append(", idRepartidor=").append(idRepartidor);
        sb.append('}');
        return sb.toString();
    }
}
