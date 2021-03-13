package dss.AppBancaria.modelo.entidad;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Usuario implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "numCedula")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String numCedula;

    @Column(name = "password")
    private String password;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @CascadeOnDelete
    private Cuenta cuenta;

    public Usuario(){}

    public Usuario (String numCedula,String password, String nombre, String apellido, Cuenta cuenta){
        this.numCedula = numCedula;
        this.password = password;
        this.nombre = nombre;
        this. apellido = apellido;
        this.cuenta = cuenta;
    }
    public Usuario (String numCedula,String password, String nombre, String apellido){
        this.numCedula = numCedula;
        this.password = password;
        this.nombre = nombre;
        this. apellido = apellido;
        this.cuenta = new Cuenta(0.0,this);
    }

    public String getNumCedula() {
        return this.numCedula;
    }

    public String getPassword() {
        return this.password;
    }
    public String getNombre() {
        return this.nombre;
    }
    public String getApellido() {
        return this.apellido;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public void setNumCedula(String numCedula) {
        this.numCedula = numCedula;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
        result = prime * result + ((numCedula == null) ? 0 : numCedula.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario) {
            Usuario usuario = (Usuario) obj;
            return usuario.getNumCedula().equals(this.numCedula);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Usuario [numCedula=" + numCedula + ", nombre=" + nombre + " apellido="
                + apellido + "]";
    }

}
