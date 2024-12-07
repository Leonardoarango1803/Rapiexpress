package back_end.com.proyecto.back_end.Service;


import back_end.com.proyecto.back_end.Models.Envio;
import back_end.com.proyecto.back_end.Models.HistorialEnvios;
import back_end.com.proyecto.back_end.Repositories.EnvioRespository;
import back_end.com.proyecto.back_end.Repositories.HistorialEnvioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialEnvioService {
    @Autowired
    private HistorialEnvioRepository historialEnvioRepository;
    @Autowired
    private EnvioRespository envioRepository;

    public List<HistorialEnvios> obtenerHistorialPorEnvio(Long idEnvio) {
        return historialEnvioRepository.findByEnvio_IdEnvio(idEnvio); // Usa el método ajustado
    }
    @Transactional
    public HistorialEnvios registrarHistorial(HistorialEnvios historialEnvios) {
        Long idEnvio = historialEnvios.getEnvio().getIdEnvio();
        Envio envio = envioRepository.findById(idEnvio)
                .orElseThrow(() -> new RuntimeException("Envio no encontrado"));
        historialEnvios.setEnvio(envio);
        return historialEnvioRepository.save(historialEnvios);
    }
}
