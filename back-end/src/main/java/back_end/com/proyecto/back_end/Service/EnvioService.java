package back_end.com.proyecto.back_end.Service;


import back_end.com.proyecto.back_end.Dto.EnvioResponseDTO;
import back_end.com.proyecto.back_end.Models.Envio;
import back_end.com.proyecto.back_end.Repositories.EnvioRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EnvioService {

    private static final List<String> PROVINCIAS_VALIDAS = List.of("ICA", "CHINCHA", "PISCO", "NASCA", "PALPA");

    @Autowired
    private EnvioRespository envioRepository;

    public List<Envio> listarEnvios() {
        return envioRepository.findAll();
    }

    public List<Envio> obtenerEnviosPorUsuarioId(Long usuarioId) {
        return envioRepository.findAllByUsuarioId(usuarioId);
    }

    @Transactional
    public Envio guardarEnvio(Envio envio) {
        // Asignar la fecha de creación solo si es nuevo
        if (envio.getIdEnvio() == null) {
            envio.setFechaCreacion(LocalDate.now());
        }

        // Guardar inicialmente para obtener el ID generado
        Envio envioGuardado = envioRepository.save(envio);

        // Generar el código de rastreo con el ID generado y actualizar el envío
        if (envioGuardado.getCodigoRastreo() == null) {
            envioGuardado.setCodigoRastreo(generarCodigoRastreo(envioGuardado.getIdEnvio()));
            envioRepository.save(envioGuardado); // Actualizar con el código de rastreo
        }

        return envioGuardado;
    }

    private String generarCodigoRastreo(Long id) {
        return "E" + String.format("%08d", id);
    }

    public EnvioResponseDTO obtenerEnvioPorCodigoRastreo(String codigoRastreo) {
        Envio envio = envioRepository.findByCodigoRastreo(codigoRastreo)
                .orElseThrow(() -> new RuntimeException("No se encontró un envío con el código de rastreo: " + codigoRastreo));

        // Mapear usuarioEnvia
        EnvioResponseDTO.UsuarioDTO usuarioDTO = new EnvioResponseDTO.UsuarioDTO();
        usuarioDTO.setNombres(envio.getUsuarioEnvia().getNombres());
        usuarioDTO.setApellidos(envio.getUsuarioEnvia().getApellidos());
        usuarioDTO.setTelefono(envio.getUsuarioEnvia().getTelefono());
        usuarioDTO.setDni(envio.getUsuarioEnvia().getDni());

        // Mapear historialEnvios
        List<EnvioResponseDTO.HistorialEnvioDTO> historialEnvioDTOs = envio.getHistorialEnvios().stream()
                .map(h -> {
                    EnvioResponseDTO.HistorialEnvioDTO dto = new EnvioResponseDTO.HistorialEnvioDTO();
                    dto.setIdHistorial(h.getIdHistorial());
                    dto.setFechaHora(h.getFechaHora());
                    dto.setLugar(h.getLugar());
                    dto.setEstado(h.getEstado());
                    dto.setObservacion(h.getObservacion());
                    return dto;
                })
                .toList();

        // Crear y devolver el DTO completo
        EnvioResponseDTO envioResponseDTO = new EnvioResponseDTO();
        envioResponseDTO.setIdEnvio(envio.getIdEnvio());
        envioResponseDTO.setCodigoRastreo(codigoRastreo);
        envioResponseDTO.setUsuarioid(usuarioDTO);
        envioResponseDTO.setNombreReceptor(envio.getNombreReceptor());
        envioResponseDTO.setApellidosReceptor(envio.getApellidosReceptor());
        envioResponseDTO.setTelefonoReceptor(envio.getTelefonoReceptor());
        envioResponseDTO.setDireccionReceptor(envio.getDireccionReceptor());
        envioResponseDTO.setProvinciaOrigen(envio.getProvinciaOrigen());
        envioResponseDTO.setProvinciaDestino(envio.getProvinciaDestino());
        envioResponseDTO.setEstadoPago(envio.getEstadoPago());
        envioResponseDTO.setContenido(envio.getContenido());
        envioResponseDTO.setPeso(envio.getPeso());
        envioResponseDTO.setDniReceptor(envio.getDniReceptor());
        envioResponseDTO.setFechaCreacion(envio.getFechaCreacion());

        envioResponseDTO.setHistorialEnvios(historialEnvioDTOs);

        return envioResponseDTO;
    }

    @Transactional
    public Envio obtenerEnvioPorCodigoRastreoEntidad(String codigoRastreo) {
        return envioRepository.findByCodigoRastreo(codigoRastreo)
                .orElse(null);
    }

    @Transactional
    public Envio actualizarEnvio(Envio envio) {
        return envioRepository.save(envio);
    }

    public void eliminarEnvio(Long id) {
        if (!envioRepository.existsById(id)) {
            throw new IllegalArgumentException("El envío con ID " + id + " no existe.");
        }
        envioRepository.deleteById(id);
    }


}

