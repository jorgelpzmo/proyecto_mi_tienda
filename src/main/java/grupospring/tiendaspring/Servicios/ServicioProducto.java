package grupospring.tiendaspring.Servicios;

import grupospring.tiendaspring.Excepciones.StockInsuficienteException;
import grupospring.tiendaspring.Modelo.Producto;
import grupospring.tiendaspring.Repositorios.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioProducto {
    @Autowired
    private ProductoRepositorio productoRepositorio;
    public List<Producto> obtenerProductos(){
        return productoRepositorio.findAll();
    }
    public Optional<Producto> obtenerProductoPorId(int id){
        return productoRepositorio.findById(id);
    }

    public Optional<Producto> obtenerProductoPorNombre(String nombre){
        return productoRepositorio.findByNombre(nombre);

    }
    public Producto insertarProducto(Producto producto){
        if(productoRepositorio.existsByNombre(producto.getNombre())){
            throw new StockInsuficienteException("El nombre del producto ya existe");
        }
        else {
            if (producto.getPrecio().compareTo(BigDecimal.valueOf(10)) < 0) {
                producto.setDescripcion(producto.getDescripcion() + " --producto en oferta--");
            }
            if (producto.getPrecio().compareTo(BigDecimal.valueOf(200)) > 0) {
                producto.setDescripcion(producto.getDescripcion() + " --producto de calidad--");
            }
            return productoRepositorio.save(producto);

        }
    }
    public Producto actualizarProducto(Producto producto){
        return productoRepositorio.save(producto);
    }
    public void eliminarProducto(int id){
        productoRepositorio.deleteById(id);
    }
}
