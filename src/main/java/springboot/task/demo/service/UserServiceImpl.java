package springboot.task.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springboot.task.demo.dao.RoleRepo;
import springboot.task.demo.dao.UserRepo;
import springboot.task.demo.model.Role;
import springboot.task.demo.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final RoleRepo roleRepo;

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(RoleRepo roleRepo, UserRepo userRepo) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
    }


    @Transactional
    public boolean save(User user, String[] role) {
        List<Role> roles = new ArrayList<>();
        for (String s : role) {
            Role roleByRoleName = roleRepo.findRoleByTitle(s);
            boolean b = roleByRoleName != null ? roles.add(roleByRoleName) : roles.add(new Role(s));
        }
        String encode = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encode);
        user.setRoles(roles);
        return userRepo.findUserByLogin(user.getLogin()) == null && userRepo.save(user) != null;

    }


    @Transactional
    public User getUserByLogin(String login) {
        return userRepo.findUserByLogin(login);
    }

    @Transactional
    public List<User> loadAllUsers() {
        return (List<User>) userRepo.findAll();
    }

    @Transactional
    public void deleteUser(User user) {
        userRepo.delete(user);

    }

    @Transactional
    public boolean updateUser(int id, String login, String password, String email, String[] roles) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(password);
        User one = userRepo.getOne(id);
        User userByLogin = userRepo.findUserByLogin(login);
        if (userByLogin!=null&&!one.equals(userByLogin))
            return false;
        List<Role> roleList = new ArrayList<>();
        for (String s:roles){
            Role roleByTitle = roleRepo.findRoleByTitle(s);
            if (roleByTitle==null){
                roleList.add(new Role(s));
            }else {
                roleList.add(roleByTitle);
            }

        }
        one.setLogin(login);
        one.setEmail(email);
        one.setPassword(encode);
        one.setRoles(roleList);
        return userRepo.save(one) != null;

    }

    @Transactional
    public User finUserById(int id) {
        return userRepo.findById(id).get();
    }
}
