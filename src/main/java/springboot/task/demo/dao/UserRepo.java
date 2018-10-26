package springboot.task.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.task.demo.model.User;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findUserByLogin(String login);
}