package ch.sobrado.radar;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "radar")
public class Radar extends PanacheEntity {

    @Column(nullable = false)
    public String name;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = true)
    @JsonBackReference
    public RadarGroup radarGroup;

    @OneToMany(mappedBy = "radar", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public java.util.List<Quadrant> quadrants;

    @OneToMany(mappedBy = "radar", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public java.util.List<Ring> rings;
}
