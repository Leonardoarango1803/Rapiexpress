package back_end.com.proyecto.back_end.Controller;
import back_end.com.proyecto.back_end.Dto.AuthRequest;
import back_end.com.proyecto.back_end.Dto.RegistroRequest;
import back_end.com.proyecto.back_end.JWT.JwtUtil;
import back_end.com.proyecto.back_end.Models.Usuario;
import back_end.com.proyecto.back_end.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UserService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/protected/usuario") //creamos el arreglo y obtenemos los usuarios
    public ArrayList<Usuario> getUsers(){
        return usuarioService.getUsers();
    }

    @PostMapping("/auth/register")
    public String register(@RequestBody RegistroRequest request) {
        usuarioService.register(request);

        return "Usuario registrado exitosamente";
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody AuthRequest request) {
        String token = usuarioService.login(request.getUsername(), request.getPassword());
        return (token != null) ? token : "Credenciales incorrectas";
    }

    // Obtener información del usuario por su ID (usado con JWT)
    @GetMapping("/protected/usuario/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUserById(id);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(usuario);
    }

    // Actualizar datos del usuario
    @PutMapping("/protected/usuario/{id}")
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable Long id, @RequestBody Usuario updatedUser) {
        boolean updated = usuarioService.updateUser(id, updatedUser);
        Map<String, String> response = new HashMap<>();
        if (updated) {
            response.put("message", "Usuario actualizado correctamente");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/usuario/me")
    public ResponseEntity<Long> getAuthenticatedUserId(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Integer usuarioId = jwtUtil.extractUsuarioId(token);
        return ResponseEntity.ok(usuarioId.longValue());
    }
}

