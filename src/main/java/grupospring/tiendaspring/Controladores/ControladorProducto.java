package grupospring.tiendaspring.Controladores;

import grupospring.tiendaspring.Modelo.Cliente;
import grupospring.tiendaspring.Modelo.Producto;
import grupospring.tiendaspring.Servicios.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ControladorProducto {
    @Autowired
    private ServicioProducto servicioProducto;

    @GetMapping()
    public ResponseEntity<List<Producto>> obtenerProductos() {
        return ResponseEntity.ok(this.servicioProducto.obtenerProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable int id) {
        Producto producto = this.servicioProducto.obtenerProductoPorId(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Producto> obtenerProductoPorNombre(@PathVariable String nombre) {
        Producto producto = this.servicioProducto.obtenerProductoPorNombre(nombre).orElse(null);
        return ResponseEntity.ok(producto);
    }

    @PostMapping()
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto productoPersistido = this.servicioProducto.insertarProducto(producto);
        return ResponseEntity.ok(productoPersistido);
    }

    @PutMapping()
    public ResponseEntity<Producto> actualizarProducto(@RequestBody Producto producto) {
        Producto productoPersistido = this.servicioProducto.actualizarProducto(producto);
        return ResponseEntity.ok(productoPersistido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable int id) {
        this.servicioProducto.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
