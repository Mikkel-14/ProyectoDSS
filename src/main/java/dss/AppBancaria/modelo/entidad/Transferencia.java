package dss.AppBancaria.modelo.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Transferencia implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "emisorId")
    private  Cuenta cuentaEmisora;

    @ManyToOne
    @JoinColumn(name="receptorId")
    private Cuenta cuentaReceptora;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Transferencia() {
    }

    public Transferencia(Cuenta cuentaEmisora, Cuenta cuentaReceptora, Double monto, Date fecha) {
        this.cuentaEmisora = cuentaEmisora;
        this.cuentaReceptora = cuentaReceptora;
        this.monto = monto;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public Cuenta getCuentaEmisora() {
        return cuentaEmisora;
    }

    public Cuenta getCuentaReceptora() {
        return cuentaReceptora;
    }

    public Double getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCuentaEmisora(Cuenta cuentaEmisora) {
        this.cuentaEmisora = cuentaEmisora;
    }

    public void setCuentaReceptora(Cuenta cuentaReceptora) {
        this.cuentaReceptora = cuentaReceptora;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transferencia)) return false;
        Transferencia that = (Transferencia) o;
        return id.equals(that.id) && cuentaEmisora.equals(that.cuentaEmisora) && cuentaReceptora.equals(that.cuentaReceptora) && monto.equals(that.monto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cuentaEmisora, cuentaReceptora, monto);
    }

    @Override
    public String toString() {
        return "Transferencia{" +
                "id=" + id +
                ", cuentaEmisora=" + cuentaEmisora +
                ", cuentaReceptora=" + cuentaReceptora +
                ", monto=" + monto +
                '}';
    }
}
