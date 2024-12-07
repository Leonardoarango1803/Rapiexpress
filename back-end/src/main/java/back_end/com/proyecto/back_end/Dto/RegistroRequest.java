package back_end.com.proyecto.back_end.Dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroRequest {

    private String nombres;
    private String apellidos;
    private String username;
    private String telefono;
    private String password;
    private String dni;
    private String rol;

}
