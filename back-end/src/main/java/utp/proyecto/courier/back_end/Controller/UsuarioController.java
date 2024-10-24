package utp.proyecto.courier.back_end.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utp.proyecto.courier.back_end.Models.UserModel;
import utp.proyecto.courier.back_end.Services.UsuarioServices;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioservice;

    @GetMapping //creamos el arreglo y obtenemos los usuarios
    public ArrayList<UserModel>getUsers(){
        return usuarioservice.getUsers();
    }

    @PostMapping //Mandamos los usuarios
    public UserModel addUser(@RequestBody UserModel user){
        return usuarioservice.saveUser(user);
    }



}
