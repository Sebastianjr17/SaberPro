package com.SaberPro.app.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Document(collection = "coordinador")
public class Coordinador {

    @Id
    private String id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellidos;

    @NotEmpty
    private String documento;

    @NotEmpty
    private String celular;

    @Email // Validación de correo electrónico
    @NotEmpty
    private String correo;

    @NotEmpty
    private String user;

    @NotEmpty
    @Size(min = 8) // Ejemplo de validación de longitud mínima para la contraseña
    private String password;

    // CONSTRUCTOR
    public Coordinador() {
    }

    public Coordinador(String nombre, String apellidos, String documento, String celular, String correo, String user, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.documento = documento;
        this.celular = celular;
        this.correo = correo;
        this.user = user;
        this.password = password;
    }

    // METODOS GET y SET
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Métodos toString, equals, y hashCode
    @Override
    public String toString() {
        return "Coordinador{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", documento='" + documento + '\'' +
                ", celular='" + celular + '\'' +
                ", correo='" + correo + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinador)) return false;

        Coordinador that = (Coordinador) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
