package py.com.mtess.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import py.com.mtess.blog_app.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByAutorId(Long usuarioId);

    @Query("SELECT p FROM Post p JOIN p.categorias c WHERE c.id = :categoriaId")
    List<Post> findByCategoriaId(@Param("categoriaId") Long categoriaId);
}
