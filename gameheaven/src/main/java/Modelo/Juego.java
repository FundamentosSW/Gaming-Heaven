package Modelo;



public class Juego {

    private String nombre;
    private String logo_path;
    private String Juego_path;
    private Double precio;
    private int id;

    public Juego(){

    }

    public Juego(String nombre, String logo_path, String juego_path, Double precio,int id) {
        this.nombre = nombre;
        this.logo_path = logo_path;
        Juego_path = juego_path;
        this.precio = precio;
        this.id=id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public String getJuego_path() {
        return Juego_path;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public void setJuego_path(String juego_path) {
        Juego_path = juego_path;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
