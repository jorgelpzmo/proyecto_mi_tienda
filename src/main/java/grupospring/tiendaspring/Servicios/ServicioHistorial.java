package grupospring.tiendaspring.Servicios;

import grupospring.tiendaspring.Excepciones.StockInsuficienteException;
import grupospring.tiendaspring.Modelo.*;
import grupospring.tiendaspring.Repositorios.ClienteRepositorio;
import grupospring.tiendaspring.Repositorios.HistorialRepositorio;
import grupospring.tiendaspring.Repositorios.ProductoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioHistorial {
    @Autowired
    private HistorialRepositorio historialRepositorio;
    @Autowired
    private ProductoRepositorio productoRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<Historial> obtenerHistorial() {
        return historialRepositorio.findAll();
    }
    public Optional<Historial> obtenerHistorialporId(int id) {
        return historialRepositorio.findById(id);
    }

    @Transactional
    public Historial compraProducto(ComprarProductoDTO comprarProductoDTO, String nickname) throws StockInsuficienteException {
        Optional<Producto> optionalProducto = productoRepositorio.findByNombre(comprarProductoDTO.getNombre());
        if (optionalProducto.isEmpty()){
            throw new StockInsuficienteException("El producto no existe");
        }
        Producto producto = optionalProducto.get();
        if (producto.getStock()<=comprarProductoDTO.getCantidad()) {
            throw new StockInsuficienteException("No se puede realizar la compra por falta de stock");
        }
        Optional<Cliente> optionalCliente = clienteRepositorio.getClienteByNickname(nickname);
        if (optionalCliente.isEmpty()){
            throw new StockInsuficienteException("El cliente no existe");
        }
        producto.setStock(producto.getStock() - comprarProductoDTO.getCantidad());
        productoRepositorio.save(producto);
        Historial historial = new Historial();
        historial.setProducto(producto);
        historial.setCliente(optionalCliente.get());
        historial.setCantidad(comprarProductoDTO.getCantidad());
        historial.setTipo("compra");
        historial.setDescripcion("Compra de" + comprarProductoDTO.getNombre());
        historial.setFechaCompra(LocalDate.now());
        System.out.println(historial);
        return historialRepositorio.save(historial);
    }

    public Historial devolverProducto(DevolverProductoDTO devolverProductoDTO, String nickname) throws StockInsuficienteException {
        Optional<Producto> optionalProducto = productoRepositorio.findByNombre(devolverProductoDTO.getNombre());
        if (optionalProducto.isEmpty()){
            throw new StockInsuficienteException("El producto no existe");
        }
        Optional<Cliente> optionalCliente = clienteRepositorio.getClienteByNickname(nickname);
        if (optionalCliente.isEmpty()){
            throw new StockInsuficienteException("El cliente no existe");
        }
        Producto producto = optionalProducto.get();
        Cliente cliente = optionalCliente.get();
        Optional<Historial> optionalHistorial = historialRepositorio.findByProducto(producto);
        Historial historial = optionalHistorial.get();
        if(historial.getNameProducto().equals(devolverProductoDTO.getNombre())){
            if(historial.getFechaCompra().isAfter(LocalDate.now().plusDays(-30))) {
                if(historial.getCantidad() >= devolverProductoDTO.getCantidad()) {
                    Historial historialDevolucion = new Historial();
                    historialDevolucion.setProducto(producto);
                    historialDevolucion.setCliente(cliente);
                    historialDevolucion.setFechaCompra(LocalDate.now());
                    historialDevolucion.setCantidad(devolverProductoDTO.getCantidad());
                    historialDevolucion.setTipo("devolucion");
                    historialDevolucion.setDescripcion("devolucion de " + devolverProductoDTO.getNombre() + " motivo: " + devolverProductoDTO.getDescripcion());
                    producto.setStock(producto.getStock() + devolverProductoDTO.getCantidad());
                    return historialRepositorio.save(historialDevolucion);
                }
                else{
                    throw new StockInsuficienteException("Cantidad a devolver mayor que la compra");
                }
            }else{
                throw new StockInsuficienteException("No posible devolucion por fecha superior a 30 dias");
            }
        }else{
            throw new StockInsuficienteException("El producto no existe");
        }

    }

    public Historial actualizarHistorial(Historial historial) {
        return historialRepositorio.save(historial);
    }

    public void eliminarHistorial(int id) {
        historialRepositorio.deleteById(id);
    }



}
