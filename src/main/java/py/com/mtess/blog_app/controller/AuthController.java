package py.com.mtess.blog_app.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.com.mtess.blog_app.dto.RegistroDTO;
import py.com.mtess.blog_app.model.Rol;
import py.com.mtess.blog_app.model.Usuario;
import py.com.mtess.blog_app.repository.RolRepository;
import py.com.mtess.blog_app.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registro")
    public String registrarUsuario(@RequestBody RegistroDTO registroDTO) {
        if (usuarioRepository.existsByUsername(registroDTO.getUsername())) {
            return "El nombre de usuario ya existe";
        }

        if (usuarioRepository.existsByEmail(registroDTO.getEmail())) {
            return "El email ya está registrado";
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(registroDTO.getUsername());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
        usuario.setEmail(registroDTO.getEmail());
        usuario.setActivo(true);

        // Asignar rol por defecto (USER)
        Rol rolUsuario = rolRepository.findByNombre("USER")
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.getRoles().add(rolUsuario);

        usuarioRepository.save(usuario);
        return "Usuario registrado exitosamente";
    }

}
