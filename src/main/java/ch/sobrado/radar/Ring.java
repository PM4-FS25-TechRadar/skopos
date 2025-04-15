package ch.sobrado.radar;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.ValidationException;

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

    @PrePersist
    @PreUpdate
    private void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Ring must have a name");
        }
        if (radar == null) {
            throw new ValidationException("Ring must be associated with a Radar");
        }
    }
}