package back_end.com.proyecto.back_end.Controller;

import back_end.com.proyecto.back_end.Models.HistorialEnvios;
import back_end.com.proyecto.back_end.Service.HistorialEnvioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth/historial-envios")
public class HistorialEnvioController {
    @Autowired
    private HistorialEnvioService historialEnvioService;

    @GetMapping("/{idEnvio}")
    public ResponseEntity<List<HistorialEnvios>> obtenerHistorialPorEnviol(@PathVariable Long idEnvio) {
        List<HistorialEnvios> historial = historialEnvioService.obtenerHistorialPorEnvio(idEnvio);
        return ResponseEntity.ok(historial);
    }



    @PostMapping
    public ResponseEntity<HistorialEnvios> registrarHistorial(@RequestBody HistorialEnvios historial) {
        HistorialEnvios nuevoHistorial = historialEnvioService.registrarHistorial(historial);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoHistorial);
    }
}
