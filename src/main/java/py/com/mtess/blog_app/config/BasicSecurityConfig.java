package py.com.mtess.blog_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and() // Habilita CORS
            .csrf().disable() // Desactivar CSRF para APIs (puedes activarlo después)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/clientes/**").authenticated() // Protege estos endpoints
                .anyRequest().permitAll() // El resto son públicos
            )
            .httpBasic(); // Usar autenticación básica (usuario/contraseña)

        return http.build();
    }

    /*
    @Bean
    public UserDetailsService userDetailsService() {
        // Usuario en memoria (solo para desarrollo)
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
    */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
