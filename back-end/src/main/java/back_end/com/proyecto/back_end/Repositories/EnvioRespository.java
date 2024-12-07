package back_end.com.proyecto.back_end.Repositories;

import back_end.com.proyecto.back_end.Models.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EnvioRespository extends JpaRepository<Envio, Long> {
    @Query(value = "SELECT * FROM envio WHERE codigo_rastreo = :codigoRastreo", nativeQuery = true)
    Optional<Envio> findByCodigoRastreo(@Param("codigoRastreo") String codigoRastreo);

    @Query(value = "SELECT * FROM envio WHERE usuarioid = :usuarioId", nativeQuery = true)
    List<Envio> findAllByUsuarioId(@Param("usuarioId") Long usuarioId);
}