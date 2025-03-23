package ch.sobrado.radar;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "radar_entry")
public class RadarEntry extends PanacheEntity {

    public String label;

    @Enumerated(EnumType.STRING)
    public Quadrant quadrant;

    @Enumerated(EnumType.STRING)
    public Ring ring;

    public int moved;

    public int year;
}