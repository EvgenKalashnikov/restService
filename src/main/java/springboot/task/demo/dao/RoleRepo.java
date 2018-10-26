package springboot.task.demo.dao;

import org.springframework.data.repository.CrudRepository;
import springboot.task.demo.model.Role;

public interface RoleRepo extends CrudRepository<Role, Integer> {
    Role findRoleByTitle(String titile);
}
