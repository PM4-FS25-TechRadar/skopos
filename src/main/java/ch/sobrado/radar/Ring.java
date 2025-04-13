package ch.sobrado.radar;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(
        name = "rings",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "radar_id"})
        }
)
public class Ring extends PanacheEntity {

    @Column(nullable = false)
    public String name;

    @ManyToOne
    @JoinColumn(name = "radar_id", nullable = false)
    @JsonBackReference
    public Radar radar;
}