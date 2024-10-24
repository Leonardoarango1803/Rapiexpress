package utp.proyecto.courier.back_end.Models;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

@Enabled
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity

public class UserModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long usuarioid;

        @Column
        private String nombres;

        @Column
        private String apellidos;

        @Column
        private String email;

        @Column
        private String telefono;

        @Column
        private String password;

        @Column
        private String dni;

        @Column
        private String role;

}
