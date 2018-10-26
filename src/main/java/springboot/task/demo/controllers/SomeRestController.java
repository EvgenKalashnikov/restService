package springboot.task.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import springboot.task.demo.model.User;
import springboot.task.demo.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class SomeRestController {
    private final UserService userService;

    @Autowired
    public SomeRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        if (id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        User user = userService.finUserById(id);
        if (user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "login/{login}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUserByLogin(@PathVariable("login") String login){
        if (login== null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        User user = userService.getUserByLogin(login);
        if (user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        if (user == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!userService.save(user, user.getRoles().stream().map(x -> x.getTitle()).toArray(String[]::new)))//to do
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody User user, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        if (user == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        userService.updateUser(user.getId(), user.getLogin(), user.getPassword(), user.getEmail(),
                user.getRoles().stream().map(x -> x.getTitle()).toArray(String[]::new));
        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
        if (id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        User user = userService.finUserById(id);
        if (user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        userService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.loadAllUsers();
//        if (users.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
