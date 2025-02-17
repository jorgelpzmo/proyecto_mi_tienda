package grupospring.tiendaspring.Servicios;

import grupospring.tiendaspring.Modelo.Cliente;
import grupospring.tiendaspring.Repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioCliente {
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<Cliente> listar() {
        return clienteRepositorio.findAll();
    }
    public Optional<Cliente> buscarPorId(Integer id) {
        return clienteRepositorio.findById(id);
    }
    public Cliente guardarCLiente(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }
    public Cliente actualizarCLiente(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }
    public void eliminar(Integer id) {
        clienteRepositorio.deleteById(id);
    }

}
