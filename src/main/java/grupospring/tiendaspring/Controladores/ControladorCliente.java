package grupospring.tiendaspring.Controladores;

import grupospring.tiendaspring.Modelo.Cliente;
import grupospring.tiendaspring.Modelo.LoginDTO;
import grupospring.tiendaspring.Servicios.ServicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ControladorCliente {
    @Autowired
    private ServicioCliente servicioCliente;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(servicioCliente.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable int id) {
        Cliente cliente = this.servicioCliente.buscarPorId(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> insertarCliente(@RequestBody Cliente cliente) {
        Cliente clientePersistido = this.servicioCliente.guardarCLiente(cliente);
        return ResponseEntity.ok(clientePersistido);
    }

    @PostMapping("/login")
    public ResponseEntity<Cliente> login(@RequestBody LoginDTO loginDTO) {
        Cliente cliente = this.servicioCliente.login(loginDTO);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping
    public ResponseEntity<Cliente> modificarCliente(@RequestBody Cliente cliente) {
        Cliente clientePersistido = this.servicioCliente.guardarCLiente(cliente);
        return ResponseEntity.ok(clientePersistido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable int id) {
        this.servicioCliente.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
