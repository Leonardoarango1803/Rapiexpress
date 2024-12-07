package back_end.com.proyecto.back_end.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Long usuarioid;

    private String nombres;
    private String apellidos;
    private String username;
    private String telefono;
    private String password;
    private String dni;
    private String rol;

    // Getters y setters
}
