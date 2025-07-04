package py.com.mtess.blog_app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.com.mtess.blog_app.dto.PostDTO;
import py.com.mtess.blog_app.model.Post;
import py.com.mtess.blog_app.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService service;

    /*@PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<Post> crearPost(@Valid @RequestBody PostDTO postDTO, @PathVariable Long usuarioId) {
        Post nuevoPost = service.crearPost(postDTO, usuarioId);
        return new ResponseEntity<>(nuevoPost, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Post>> obtenerPostsPorUsuario(
            @PathVariable Long usuarioId) {

        List<Post> posts = service.obtenerPostsPorUsuario(usuarioId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> obtenerPostPorId(@PathVariable Long id) {
        Post post = service.obtenerPostPorId(id);
        return ResponseEntity.ok(post);
    }*/

}
