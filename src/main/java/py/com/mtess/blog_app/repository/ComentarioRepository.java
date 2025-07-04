package py.com.mtess.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.mtess.blog_app.model.Comentario;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByPostId(Long id);

    List<Comentario> findByAutorId(Long id);
}
