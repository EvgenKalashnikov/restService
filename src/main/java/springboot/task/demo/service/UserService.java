package springboot.task.demo.service;



import springboot.task.demo.model.User;

import java.util.List;

public interface UserService {
    boolean save(User user, String[] role);
    User getUserByLogin(String login);
    List<User> loadAllUsers();
    void deleteUser(User userName);
    boolean updateUser(int id, String login, String password, String email, String[] roles);
    User finUserById(int id);

}
