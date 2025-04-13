package ch.sobrado.radar;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(
        name = "versions",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "technology_id"})
        }
)
public class Version extends PanacheEntity {

    @Column(nullable = false)
    public String name;

    public String description;

    @ManyToOne
    @JoinColumn(name = "technology_id", nullable = false)
    @JsonBackReference
    public Technology technology;
}
