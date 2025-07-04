package py.com.mtess.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.mtess.blog_app.model.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByName(String name);

    Optional<Cliente> findByEmail(String email);

    Boolean existsByName(String name);

    boolean existsByEmail(String email);
}
