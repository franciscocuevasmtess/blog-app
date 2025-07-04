package py.com.mtess.blog_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    @NotBlank
    @Size(min = 5, max = 100)
    private String titulo;

    @NotBlank
    @Size(min = 10, max = 5000)
    private String contenido;
}
