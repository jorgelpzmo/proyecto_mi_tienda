package grupospring.tiendaspring.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "historial")
@JsonIgnoreProperties({"cliente", "producto"})
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

//    @Transient
//    private String nameCliente;

    @JsonProperty("nameCliente")
//    public void setNameCliente(String nameCliente) {
//        this.nameCliente = nameCliente;
//    }
    public String getNameCliente() {
        return cliente!=null?cliente.getNombre():null;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

//    @Transient
//    private String nameProducto;

    @JsonProperty("nameProducto")
//    public void setNameProducto(String nameProducto) {
//        this.nameProducto = nameProducto;
//    }

    public String getNameProducto() {
        return producto!=null?producto.getNombre():null;
    }





    @NotNull
    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra;

    @ColumnDefault("1")
    @Column(name = "cantidad")
    private Integer cantidad;

    @Size(max = 100)
    @NotNull
    @Column(name = "tipo", nullable = false, length = 100)
    private String tipo;

    @Size(max = 200)
    @Column(name = "descripcion", length = 200)
    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public grupospring.tiendaspring.Modelo.Producto getProducto() {
        return producto;
    }

    public void setProducto(grupospring.tiendaspring.Modelo.Producto producto) {
        this.producto = producto;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Override
    public String toString() {
        return "Historial{" +
                "id=" + id +
                ", nameCliente='" + getNameCliente() + '\'' +
                ", nameProducto='" + getNameProducto() + '\'' +
                ", fechaCompra=" + fechaCompra +
                ", cantidad=" + cantidad +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }




}