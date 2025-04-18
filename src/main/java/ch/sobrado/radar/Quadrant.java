package ch.sobrado.radar;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.ValidationException;

@Entity
@Table(
        name = "quadrants",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "radar_id"})
        }
)
@JsonIgnoreProperties("radar")
public class Quadrant extends PanacheEntity {

    @Column(nullable = false)
    public String name;

    @ManyToOne
    @JoinColumn(name = "radar_id", nullable = false)
    public Radar radar;

    @PrePersist
    @PreUpdate
    private void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Quadrant must have a name");
        }
        if (radar == null) {
            throw new ValidationException("Quadrant must be associated with a Radar");
        }
    }
}