package py.com.mtess.blog_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    @NotBlank
    @Size(min = 5, max = 100)
    private String username;

    private String email;

    private String password;
}
