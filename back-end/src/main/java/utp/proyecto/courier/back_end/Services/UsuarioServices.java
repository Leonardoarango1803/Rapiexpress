package utp.proyecto.courier.back_end.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.proyecto.courier.back_end.Models.UserModel;
import utp.proyecto.courier.back_end.Repositories.IUsuarioRepositories;

import java.util.ArrayList;

@Service
public class UsuarioServices {
    @Autowired
    IUsuarioRepositories usuarioRepositories;

    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) usuarioRepositories.findAll();
    }

    public UserModel saveUser(UserModel user){
        return usuarioRepositories.save(user);
    }


}
