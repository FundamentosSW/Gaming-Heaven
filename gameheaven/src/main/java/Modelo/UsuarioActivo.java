package Modelo;

public class UsuarioActivo {
    private static UsuarioActivo instancia; // Instancia única
    private Angel usuario; // Usuario logueado

    // Constructor privado para evitar instanciación directa
    private UsuarioActivo() {}

    // Método estático para obtener la instancia única
    public static synchronized UsuarioActivo getInstance() {
        if (instancia == null) {
            instancia = new UsuarioActivo();
        }
        return instancia;
    }

    // Método para establecer el usuario logueado
    public void iniciarSesion(Angel usuario) {
        this.usuario = usuario;
    }

    // Método para cerrar la sesión
    public void cerrarSesion() {
        this.usuario = null;
    }

    // Método para obtener el usuario logueado
    public Angel getUsuario() {
        return usuario;
    }

    // Método para verificar si hay un usuario logueado
    public boolean estaLogueado() {
        return usuario != null;
    }
}
