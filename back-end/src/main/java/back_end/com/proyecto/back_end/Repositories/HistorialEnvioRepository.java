package back_end.com.proyecto.back_end.Repositories;



import back_end.com.proyecto.back_end.Models.Envio;
import back_end.com.proyecto.back_end.Models.HistorialEnvios;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistorialEnvioRepository extends JpaRepository<HistorialEnvios, Long> {
    List<HistorialEnvios> findByEnvio_IdEnvio(Long idEnvio);
    // Consulta utilizando EntityGraph para cargar historialEnvios


    // Consulta personalizada con JPQL
    @Query("SELECT e FROM Envio e JOIN FETCH e.historialEnvios WHERE e.codigoRastreo = :codigoRastreo")
    Optional<Envio> findByCodigoRastreoWithHistorial(@Param("codigoRastreo") String codigoRastreo);
}
