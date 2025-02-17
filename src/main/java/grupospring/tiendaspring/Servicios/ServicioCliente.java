package grupospring.tiendaspring.Servicios;

import grupospring.tiendaspring.Excepciones.StockInsuficienteException;
import grupospring.tiendaspring.Modelo.Cliente;
import grupospring.tiendaspring.Modelo.LoginDTO;
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
    public Cliente login(LoginDTO loginDTO) {
        Optional<Cliente> clienteAux = clienteRepositorio.getClienteByNickname(loginDTO.getNickname());
        if (clienteAux.isPresent()) {
            Cliente cliente = clienteAux.get();
            if(cliente.getPassword().equals(loginDTO.getPassword())) {
                return cliente;
            }else{
                throw new StockInsuficienteException("Contraseña incorrecta");
            }
        }else{
            throw new StockInsuficienteException("Cliente no existe");
        }

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
