package ch.sobrado.radar;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Table;

@Entity
@Table(name = "radar_view")
public class RadarView extends PanacheEntityBase implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    public int year;
    public String jsondata;


}
