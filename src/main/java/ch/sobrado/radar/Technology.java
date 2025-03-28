package ch.sobrado.radar;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "technologies")
public class Technology extends PanacheEntity {

    @Column(nullable = false, unique = true)
    public String name;

    public String description;

    @OneToMany(mappedBy = "technology", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    public List<Version> versions;
}