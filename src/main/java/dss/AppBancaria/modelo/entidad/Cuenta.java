package dss.AppBancaria.modelo.entidad;
import org.eclipse.persistence.annotations.CascadeOnDelete;

import javax.persistence.*;
import java.util.*;
import java.io.Serializable;

@Entity
@TableGenerator(name="tab", initialValue=100000, allocationSize=1)
@Table(name = "Cuenta")
public class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
    @Column(name = "id_cuenta", nullable = false)
    private Integer id;

    @Column(name = "saldo_actual", nullable = false)
    private Double saldoActual;

    @OneToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    @OneToMany(mappedBy = "cuentaEmisora", cascade = CascadeType.ALL)
    @CascadeOnDelete
    private List<Transferencia> listaTransferencias;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    @CascadeOnDelete
    private List<Movimiento> listaMovimientos;

    public Cuenta(){
    }

    public Cuenta( Double saldoActual, Usuario usuario){
        this.saldoActual = saldoActual;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }


    public Double getSaldoActual() {
        return saldoActual;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Transferencia> getListaTransferencias() {
        return listaTransferencias;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setListaTransferencias(List<Transferencia> listaTransferencias) {
        this.listaTransferencias = listaTransferencias;
    }

    public void setSaldoActual(Double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cuenta)) return false;
        Cuenta cuenta = (Cuenta) o;
        return Objects.equals(id, cuenta.id) &&
                Objects.equals(saldoActual, cuenta.saldoActual)&&
                Objects.equals(usuario, cuenta.usuario) &&
                Objects.equals(listaTransferencias, cuenta.listaTransferencias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saldoActual, usuario, listaTransferencias);
    }
}