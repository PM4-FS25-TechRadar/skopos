package ch.sobrado.radar;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "entries")
public class RadarEntry extends PanacheEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "radar_id", nullable = false)
    public Radar radar;

    @ManyToOne(optional = false)
    @JoinColumn(name = "version_id", nullable = false)
    public Version version;

    @ManyToOne(optional = false)
    @JoinColumn(name = "quadrant_id", nullable = false)
    public Quadrant quadrant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ring_id", nullable = false)
    public Ring ring;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    public EntryStatus status;

}
