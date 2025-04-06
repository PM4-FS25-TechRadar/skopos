package ch.sobrado.radar;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "radar")
public class Radar extends PanacheEntity {

    @Column(nullable = false)
    public String name;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    @JsonBackReference
    public RadarGroup radarGroup;

    @OneToMany(mappedBy = "radar", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    public List<Quadrant> quadrants;

    @OneToMany(mappedBy = "radar", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    public List<Ring> rings;
}
