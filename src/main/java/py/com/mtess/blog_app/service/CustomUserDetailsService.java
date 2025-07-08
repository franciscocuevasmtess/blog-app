package py.com.mtess.blog_app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import py.com.mtess.blog_app.model.Rol;
import py.com.mtess.blog_app.model.Usuario;
import py.com.mtess.blog_app.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    /*
    * CustomUserDetailsService: Es el "puente" entre tus usuarios en la base de datos y Spring Security. Su trabajo es:
    * - Recibir un nombre de usuario.
    * - Buscar ese usuario en la base de datos.
    * - Si existe, convertirlo a un formato que Spring Security entienda.
    * - Si no existe, lanzar un error.
    * */
    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRoles().stream()
                        .map(Rol::getNombre)
                        .toArray(String[]::new))
                .build();
    }

}
