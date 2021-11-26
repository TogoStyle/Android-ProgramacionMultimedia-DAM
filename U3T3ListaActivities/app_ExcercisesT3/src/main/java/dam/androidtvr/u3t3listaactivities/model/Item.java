package dam.androidtvr.u3t3listaactivities.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Item implements Serializable {

    private int id;
    private  String version;
    private String nombreVersion;
    private int year;
    private int numeroAPI;
    private String URL;


    public Item() {
    }

    public Item(int id, String version, String nombreVersion, int year, int numeroAPI, String URL) {
        this.id = id;
        this.version = version;
        this.nombreVersion = nombreVersion;
        this.year = year;
        this.numeroAPI = numeroAPI;
        this.URL = URL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNombreVersion() {
        return nombreVersion;
    }

    public void setNombreVersion(String nombreVersion) {
        this.nombreVersion = nombreVersion;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNumeroAPI() {
        return numeroAPI;
    }

    public void setNumeroAPI(int numeroAPI) {
        this.numeroAPI = numeroAPI;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", nombreVersion='" + nombreVersion + '\'' +
                ", year=" + year +
                ", numeroAPI=" + numeroAPI +
                ", URL='" + URL + '\'' +
                '}';
    }
}
