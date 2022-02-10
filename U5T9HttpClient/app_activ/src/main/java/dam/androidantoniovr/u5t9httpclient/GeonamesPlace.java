package dam.androidantoniovr.u5t9httpclient;

public class GeonamesPlace {

    private double latitud;
    private double longitud;
    private String place;

    public GeonamesPlace() {
    }

    public GeonamesPlace(double latitud, double longitud, String place) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.place = place;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
