package ch.sobrado.radar;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "radar")
public class Radar extends PanacheEntity {

    @Column(nullable = false)
    public String name;

    /*
        @ManyToOne
        @JoinColumn(name = "group_id", nullable = false)
        @JsonBackReference
        public RadarGroup radarGroup;
    */

    @OneToMany(mappedBy = "radar", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderColumn(name = "quadrant_order")
    @Size(min = 4, max = 4, message = "Radar must have 4 quadrants")
    public List<Quadrant> quadrants = new ArrayList<>();

    @OneToMany(mappedBy = "radar", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderColumn(name = "ring_order")
    @Size(min = 4, max = 4, message = "Radar must have 4 rings")
    public List<Ring> rings = new ArrayList<>();

    public void addRing(Ring ring) {
        ring.radar = this;
        rings.add(ring);
    }

    public void addQuadrant(Quadrant quadrant) {
        quadrant.radar = this;
        quadrants.add(quadrant);
    }

    @PrePersist
    @PreUpdate
    public void validate() {
        if (quadrants == null || quadrants.size() != 4) {
            throw new ValidationException("Radar must have 4 quadrants");
        }
        if (rings == null || rings.size() != 4) {
            throw new ValidationException("Radar must have 4 Rings");
        }
    }
}
