package grupospring.tiendaspring.Repositorios;
import grupospring.tiendaspring.Modelo.Cliente;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
    public Optional<Cliente> findByNombre(String nombre);

    Optional<Cliente> getClienteByNickname(@Size(max = 50) @NotNull String nickname);
}
