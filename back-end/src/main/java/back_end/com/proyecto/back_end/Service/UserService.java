package back_end.com.proyecto.back_end.Service;

import back_end.com.proyecto.back_end.Dto.RegistroRequest; // Asegúrate de que el DTO esté en el paquete adecuado
import back_end.com.proyecto.back_end.JWT.JwtUtil;
import back_end.com.proyecto.back_end.Models.Usuario;
import back_end.com.proyecto.back_end.Repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    public ArrayList<Usuario> getUsers(){
        return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Usuario register(RegistroRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombres(request.getNombres());
        usuario.setApellidos(request.getApellidos());
        usuario.setUsername(request.getUsername());
        usuario.setTelefono(request.getTelefono());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setDni(request.getDni());
        usuario.setRol(request.getRol()); // Asigna el rol del usuario (ej. "user" o "admin")

        return usuarioRepository.save(usuario);
    }

    public String login(String username, String password) {
        // Busca el usuario por su username
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Valida la contraseña
        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new IllegalArgumentException("Credenciales incorrectas");
        }

        // Genera el token JWT
        return jwtUtil.generateToken(username, usuario.getUsuarioid());
    }

    // Obtener un usuario por ID
    public Usuario getUserById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Actualizar los datos de un usuario
    @Transactional
    public boolean updateUser(Long id, Usuario updatedUser) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNombres(updatedUser.getNombres());
            usuario.setApellidos(updatedUser.getApellidos());
            usuario.setTelefono(updatedUser.getTelefono());
            usuario.setDni(updatedUser.getDni());
            usuario.setUsername(updatedUser.getUsername());
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

}