package grupospring.tiendaspring.Repositorios;
import grupospring.tiendaspring.Modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
    public Optional<Producto> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
