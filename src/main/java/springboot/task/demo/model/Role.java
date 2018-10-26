package springboot.task.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "roles")
public class Role extends Model {

    private static final long serialVersionUID = 7890639962282377869L;
    @Column(unique = true)
    private String title;
    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private List<User> users = new ArrayList<>();

    public Role() {

    }

    public Role(String title) {
        super();
        this.title = title;
    }

    public Role(int id) {
        super(id);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
