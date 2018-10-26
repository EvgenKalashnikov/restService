package springboot.task.demo.service;


import springboot.task.demo.model.Role;

public interface RoleService {
    Role getRoleById(int id);
    Role getRoleByRoleName(String roleName);
}
