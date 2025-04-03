package ch.sobrado.radar;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "radar_group")
public class RadarGroup extends PanacheEntity {

    @Column(nullable = false)
    public String name;

    @OneToMany(mappedBy = "radarGroup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    public List<Radar> radars;
}