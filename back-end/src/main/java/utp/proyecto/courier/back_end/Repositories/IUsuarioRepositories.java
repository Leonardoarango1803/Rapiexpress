package utp.proyecto.courier.back_end.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.proyecto.courier.back_end.Models.UserModel;

@Repository
public interface IUsuarioRepositories extends JpaRepository<UserModel,Long> {
}
