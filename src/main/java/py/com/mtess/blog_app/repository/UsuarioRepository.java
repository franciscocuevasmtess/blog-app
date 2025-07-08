package py.com.mtess.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.mtess.blog_app.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
