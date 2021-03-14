package dss.AppBancaria.modelo.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

@Entity
@NamedQuery(name = "listarMovimientos", query = "SELECT m FROM Movimiento m WHERE m.cuenta= :cuenta")
public class Movimiento implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "monto")
    private BigInteger monto;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "cuentaId")
    private Cuenta cuenta;

    @Column(name = "tipoMov")
    private Character tipo;

    public Movimiento() {
    }

    public Movimiento(BigInteger monto, Date fecha, Cuenta cuenta) {
        this.monto = monto;
        this.fecha = fecha;
        this.cuenta = cuenta;
    }
    public Movimiento(BigInteger monto, Date fecha, Cuenta cuenta, Character tipo) {
        this.monto = monto;
        this.fecha = fecha;
        this.cuenta = cuenta;
        this.tipo = tipo;
    }

    public Character getTipo() {
        return tipo;
    }

    public Integer getId() {
        return id;
    }

    public BigInteger getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMonto(BigInteger monto) {
        this.monto = monto;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movimiento)) return false;
        Movimiento that = (Movimiento) o;
        return Objects.equals(id, that.id) && Objects.equals(monto, that.monto) && Objects.equals(fecha, that.fecha) && Objects.equals(cuenta, that.cuenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, monto, fecha, cuenta);
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "id=" + id +
                ", monto=" + monto +
                ", fecha=" + fecha +
                ", cuenta=" + cuenta +
                '}';
    }
}
