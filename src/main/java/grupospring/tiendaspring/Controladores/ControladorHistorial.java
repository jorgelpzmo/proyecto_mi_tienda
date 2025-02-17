package grupospring.tiendaspring.Controladores;

import grupospring.tiendaspring.Modelo.ComprarProductoDTO;
import grupospring.tiendaspring.Modelo.DevolverProductoDTO;
import grupospring.tiendaspring.Modelo.Historial;
import grupospring.tiendaspring.Servicios.ServicioHistorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial")
public class ControladorHistorial {
    @Autowired
    private ServicioHistorial servicioHistorial;

    @GetMapping()
    public ResponseEntity<List<Historial>> obtenerHistorial() {
        return ResponseEntity.ok(servicioHistorial.obtenerHistorial());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historial> obtenerHistorial(@PathVariable int id) {
        Historial historial = this.servicioHistorial.obtenerHistorialporId(id).orElseThrow(() -> new RuntimeException("Historial no encontrado"));
        return ResponseEntity.ok(historial);
    }

    @PostMapping("/comprar/{nickname}")
    public ResponseEntity<Historial> compraProducto(@RequestBody ComprarProductoDTO comprarProductoDTO, @PathVariable String nickname) {
        Historial historialPersistido = this.servicioHistorial.compraProducto(comprarProductoDTO, nickname);
        return ResponseEntity.ok(historialPersistido);
    }
    @PostMapping("/devolver/{nickname}")
    public ResponseEntity<Historial> devolverProducto(@RequestBody DevolverProductoDTO devolverProductoDTO, @PathVariable String nickname) {
        Historial historialPersistido = this.servicioHistorial.devolverProducto(devolverProductoDTO, nickname);
        return ResponseEntity.ok(historialPersistido);
    }

    @PutMapping
    public ResponseEntity<Historial> actualizarHistorial(@RequestBody Historial historial) {
        Historial historialPersistido = this.servicioHistorial.actualizarHistorial(historial);
        return ResponseEntity.ok(historialPersistido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHistorial(@PathVariable int id) {
        this.servicioHistorial.eliminarHistorial(id);
        return ResponseEntity.noContent().build();
    }
}
