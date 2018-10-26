package springboot.task.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springboot.task.demo.dao.RoleRepo;
import springboot.task.demo.model.Role;

import javax.transaction.Transactional;

@Repository
public class RoleServiceImpl implements RoleService {
    private final RoleRepo rolesRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo rolesRepo) {
        this.rolesRepo = rolesRepo;
    }

    @Transactional
    public Role getRoleById(int id) {
        return rolesRepo.findById(id).get();
    }


    @Transactional
    public Role getRoleByRoleName(String roleName) {
        return rolesRepo.findRoleByTitle(roleName);
    }
}
