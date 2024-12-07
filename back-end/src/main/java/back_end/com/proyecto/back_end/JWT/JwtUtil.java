package back_end.com.proyecto.back_end.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    private final SecretKey secretKey;

    // Constructor que genera automáticamente una clave secreta segura
    public JwtUtil() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    // Genera un token usando la clave secreta generada
    public String generateToken(String username, Long usuarioid) {
        return Jwts.builder()
                .setSubject(username)
                .claim("usuarioid", usuarioid)  // Agregar el usuarioid como un claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3)) // 3 horas
                .signWith(secretKey)  // Usa la clave secreta generada
                .compact();
    }

    // Verifica la validez del token
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //extrae el usuario
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }
    //extra el usuarioid del usuario
    public Integer extractUsuarioId(String token) {
        return getClaims(token).get("usuarioid", Integer.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
