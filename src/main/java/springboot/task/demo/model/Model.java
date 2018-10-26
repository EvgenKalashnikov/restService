package springboot.task.demo.model;

import javax.persistence.*;
import java.io.Serializable;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "@Id")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Model.class)

@MappedSuperclass
public abstract class Model implements Serializable {
    private static final long serialVersionUID = -6359549900797572596L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Model() {
    }

    public Model(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                '}';
    }
}
