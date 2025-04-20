package ch.sobrado.radar;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "radar_entry")
public class RadarEntry extends PanacheEntity {

    public String label;

    @ManyToOne
    @JoinColumn(name = "radar_id")
    public Radar radar;

    @ManyToOne
    @JoinColumn(name = "quadrant_id")
    public Quadrant quadrant;

    @ManyToOne
    @JoinColumn(name = "ring_id")
    public Ring ring;

    public int moved;

    public int year;

}