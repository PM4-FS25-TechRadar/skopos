package ch.sobrado.radar;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "radar_entry")
public class RadarEntry extends PanacheEntity {
    public String label;
    public String quadrant;
    public String ring;
    public int moved;
    public int year;
}