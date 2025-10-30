package py.com.mtess.blog_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.com.mtess.blog_app.dto.RegistroDTO;
import py.com.mtess.blog_app.dto.RegistroRolDTO;
import py.com.mtess.blog_app.model.Rol;
import py.com.mtess.blog_app.model.Usuario;
import py.com.mtess.blog_app.repository.RolRepository;
import py.com.mtess.blog_app.repository.UsuarioRepository;

import java.util.HashSet;
import java.util.Optional;

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
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO) {
        // 1. Validar si el usuario o email ya existen
        if (usuarioRepository.existsByUsername(registroDTO.getUsername())) {
            //return "El nombre de usuario ya existe";
            return ResponseEntity.badRequest().body("El nombre de usuario ya existe");
        }

        if (usuarioRepository.existsByEmail(registroDTO.getEmail())) {
            //return "El email ya está registrado";
            return ResponseEntity.badRequest().body("El email ya está registrado");
        }

        // 2. Crear nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(registroDTO.getUsername());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
        usuario.setEmail(registroDTO.getEmail());
        usuario.setActivo(true);

        // 3. Asignar rol por defecto (USER)
        Rol rolUsuario = rolRepository.findByNombre("USER")
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        // Asegurarse que la colección de roles está inicializada
        if (usuario.getRoles() == null) {
            usuario.setRoles(new HashSet<>());
        }

        usuario.getRoles().add(rolUsuario);

        // 4. Guardar el usuario
        usuarioRepository.save(usuario);

        //return "Usuario registrado exitosamente";
        // 5. Retornar respuesta
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }


    @PostMapping("/rol")
    public ResponseEntity<?> registrarRol(@RequestBody RegistroRolDTO registroRolDTO) {

        // 1. Validar si el rol ya existe
        Optional<Rol> rol = rolRepository.findByNombre(registroRolDTO.getNombre());
        if (rol.isPresent()) {
            return ResponseEntity.badRequest().body("El nombre de rol ya existe");
        }

        // 2. Crear nuevo rol
        Rol rol1 = new Rol();
        rol1.setNombre(registroRolDTO.getNombre());

        // 3. Guardar el rol
        rolRepository.save(rol1);

        // 4. Retornar respuesta
        return ResponseEntity.ok("Rol registrado exitosamente");
    }

}
