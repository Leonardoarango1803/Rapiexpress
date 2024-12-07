package back_end.com.proyecto.back_end.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "envio")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "historialEnvios")
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnvio;

    @Column(unique = true)
    private String codigoRastreo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuarioEnvia;

    private String nombreReceptor;
    private String telefonoReceptor;
    private String direccionReceptor;

    private String provinciaDestino; // Cambiado a String
    private String estadoPago;       // Cambiado a String

    private String contenido;
    private Double peso;

    private String apellidosReceptor;
    private String dniReceptor;
    private String provinciaOrigen;
    private LocalDate fechaEntrega;

    private LocalDate fechaCreacion = LocalDate.now();

    @OneToMany(mappedBy = "envio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<HistorialEnvios> historialEnvios = new ArrayList<>();

}

