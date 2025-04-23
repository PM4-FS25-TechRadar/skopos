package ch.sobrado.radar;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "radar_view")
public class RadarView extends PanacheEntityBase implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    public int year;
    public String jsondata;


}