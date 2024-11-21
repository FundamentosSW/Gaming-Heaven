package Modelo;


import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;

public class Angel extends Persona{
    private String Username;
    private String Password;
    //puede tener una lista de juegos con los q se tenga convenio
    private double points;
    private String tipo_usuario;
    public Angel() {
        //constructor vacio
    }

    public Angel(String Username, String Password, double points, String tipo_usuario, String Nombre, String Apellido, String Correo, LocalDate FechaNacimiento, double numdoc, long telefono){
        super(Nombre,Apellido,Correo,FechaNacimiento,numdoc,telefono);
        this.Username = Username;
        this.points= points;
        this.tipo_usuario=tipo_usuario;
        this.Password = Password;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public double getPoints() {
        return points;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public Angel verificarLogin(String Username){
        return BaseDatos.getInstance().buscarUsuario(Username);

    }

}
