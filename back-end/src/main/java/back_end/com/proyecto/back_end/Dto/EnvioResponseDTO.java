package back_end.com.proyecto.back_end.Dto;

import back_end.com.proyecto.back_end.Models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvioResponseDTO {
    private Long idEnvio;
    private String codigoRastreo;
    private UsuarioDTO usuarioid;
    private String nombreReceptor;
    private String apellidosReceptor;
    private String telefonoReceptor;
    private String direccionReceptor;
    private String provinciaOrigen;
    private String provinciaDestino;
    private String estadoPago;
    private String contenido;
    private Double peso;
    private String dniReceptor;
    private LocalDate fechaCreacion;
    private LocalDate fechaEntrega;
    private List<HistorialEnvioDTO> historialEnvios;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UsuarioDTO {
        private String nombres;
        private String apellidos;
        private String telefono;
        private String dni;

    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HistorialEnvioDTO {
        private Long idHistorial;
        private LocalDateTime fechaHora;
        private String lugar;
        private String estado;
        private String observacion;


    }
}
