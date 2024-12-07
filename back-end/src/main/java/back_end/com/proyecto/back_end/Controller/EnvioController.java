package back_end.com.proyecto.back_end.Controller;

import back_end.com.proyecto.back_end.Dto.EnvioResponseDTO;
import back_end.com.proyecto.back_end.JWT.JwtUtil;
import back_end.com.proyecto.back_end.Models.Envio;
import back_end.com.proyecto.back_end.Models.HistorialEnvios;
import back_end.com.proyecto.back_end.Models.Usuario;
import back_end.com.proyecto.back_end.Repositories.EnvioRespository;
import back_end.com.proyecto.back_end.Service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EnvioRespository envioRespository;

    @GetMapping("/protected/enviouser/{usuarioId}")
    public ResponseEntity<List<Envio>> obtenerEnviosPorUsuario(@PathVariable Long usuarioId) {
        List<Envio> envios = envioService.obtenerEnviosPorUsuarioId(usuarioId);
        return ResponseEntity.ok(envios);
    }

    @GetMapping("/auth/envios")
    public ResponseEntity<List<Envio>> listarEnvios() {
        return ResponseEntity.ok(envioService.listarEnvios());
    }


    @GetMapping("/auth/envios/{codigoRastreo}")
    public ResponseEntity<EnvioResponseDTO> obtenerEnvioPorCodigoRastreo(@PathVariable String codigoRastreo) {
        EnvioResponseDTO envioResponse = envioService.obtenerEnvioPorCodigoRastreo(codigoRastreo);
        return ResponseEntity.ok(envioResponse);
    }



    @PostMapping("/protected/envios")
    public ResponseEntity<Map<String, Object>> guardarEnvio(
            @RequestBody Envio envio,
            @RequestHeader("Authorization") String authorizationHeader) {

        // Extraer el token y usuario ID del encabezado
        String token = authorizationHeader.substring(7); // Remover "Bearer "
        Integer usuarioId = jwtUtil.extractUsuarioId(token);

        // Asignar el usuario al envío
        Usuario usuario = new Usuario();
        usuario.setUsuarioid(usuarioId.longValue());
        envio.setUsuarioEnvia(usuario);

        // Crear el historial inicial
        HistorialEnvios historialInicial = new HistorialEnvios();
        historialInicial.setFechaHora(LocalDateTime.now());
        historialInicial.setLugar(envio.getProvinciaOrigen());
        historialInicial.setEstado("recibido");
        historialInicial.setObservacion("");
        historialInicial.setEnvio(envio);

        envio.getHistorialEnvios().add(historialInicial);

        // Guardar el envío con el historial inicial y código de rastreo
        Envio envioGuardado = envioService.guardarEnvio(envio);

        // Retornar la respuesta con los datos del envío
        Map<String, Object> response = new HashMap<>();
        response.put("envio", envioGuardado);

        return ResponseEntity.ok(response);
    }


    @PutMapping("/auth/envios/{codigoRastreo}")
    public ResponseEntity<Envio> actualizarEnvio(
            @PathVariable String codigoRastreo,
            @RequestBody Envio actualizacionEnvio) {

        // Buscar el envío existente
        Envio envioExistente = envioService.obtenerEnvioPorCodigoRastreoEntidad(codigoRastreo);

        if (envioExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar campos si están presentes
        if (actualizacionEnvio.getEstadoPago() != null) {
            envioExistente.setEstadoPago(actualizacionEnvio.getEstadoPago());
        }

        if (actualizacionEnvio.getFechaEntrega() != null) {
            envioExistente.setFechaEntrega(actualizacionEnvio.getFechaEntrega());
        }

        // Agregar al historial
        if (!actualizacionEnvio.getHistorialEnvios().isEmpty()) {
            for (HistorialEnvios nuevoHistorial : actualizacionEnvio.getHistorialEnvios()) {
                nuevoHistorial.setEnvio(envioExistente); // Establece la relación bidireccional
                envioExistente.getHistorialEnvios().add(nuevoHistorial);
            }
        }

        // Guardar cambios
        Envio envioActualizado = envioService.actualizarEnvio(envioExistente);
        return ResponseEntity.ok(envioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEnvio(@PathVariable Long id) {
        try {
            envioService.eliminarEnvio(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
