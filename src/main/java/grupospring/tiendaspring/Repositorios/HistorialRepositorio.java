package grupospring.tiendaspring.Repositorios;
import grupospring.tiendaspring.Modelo.Cliente;
import grupospring.tiendaspring.Modelo.Historial;
import grupospring.tiendaspring.Modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistorialRepositorio extends JpaRepository<Historial, Integer> {
    public Optional<Historial> findByCliente(Cliente cliente);
    public Optional<Historial> findByProducto(Producto producto);
}
