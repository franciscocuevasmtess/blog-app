package py.com.mtess.blog_app.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.mtess.blog_app.dto.PostDTO;
import py.com.mtess.blog_app.exception.ResourceNotFoundException;
import py.com.mtess.blog_app.model.Post;
import py.com.mtess.blog_app.model.Usuario;
import py.com.mtess.blog_app.repository.PostRepository;
import py.com.mtess.blog_app.repository.UsuarioRepository;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    /*@Transactional
    public Post crearPost(PostDTO postDTO, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));

        Post post = new Post();
        post.setTitulo(postDTO.getTitulo());
        post.setContenido(postDTO.getContenido());
        post.setAutor(usuario);

        return repository.save(post);
    }

    @Transactional
    public List<Post> obtenerPostsPorUsuario(Long usuarioId) {
        return repository.findByAutorId(usuarioId);
    }

    @Transactional
    public Post obtenerPostPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }*/

}
