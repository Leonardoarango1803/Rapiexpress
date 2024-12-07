package back_end.com.proyecto.back_end.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "historial_envios")
@NoArgsConstructor
@AllArgsConstructor

public class HistorialEnvios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorial;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_envio", nullable = false)
    @JsonBackReference
    private Envio envio;


    private LocalDateTime fechaHora; // Fecha y hora del registro
    private String lugar;            // Ubicación
    private String estado;           // Estado del pedido (e.g., "En ruta", "Entregado")
    private String observacion;


}
