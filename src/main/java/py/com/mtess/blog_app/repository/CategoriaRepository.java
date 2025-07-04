package py.com.mtess.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.mtess.blog_app.model.Categoria;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNombre(String nombre);
}
