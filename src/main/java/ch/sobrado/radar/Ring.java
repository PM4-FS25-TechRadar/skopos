package ch.sobrado.radar;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(
        name = "rings",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "position", "radar_id"})
        }
)
public class Ring extends PanacheEntity {

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public Position position;

    @ManyToOne
    @JoinColumn(name = "radar_id", nullable = false)
    @JsonBackReference
    public Radar radar;
}