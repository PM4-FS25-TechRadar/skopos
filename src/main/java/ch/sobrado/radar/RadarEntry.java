package ch.sobrado.radar;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "entries")
public class RadarEntry extends PanacheEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "radar_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    public Radar radar;

    @ManyToOne(optional = false)
    @JoinColumn(name = "version_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Version version;

    @ManyToOne(optional = false)
    @JoinColumn(name = "quadrant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Quadrant quadrant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ring_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Ring ring;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    public EntryStatus status;

}
