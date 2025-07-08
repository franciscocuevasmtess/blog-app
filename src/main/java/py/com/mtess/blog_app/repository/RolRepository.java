package py.com.mtess.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.mtess.blog_app.model.Rol;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}
