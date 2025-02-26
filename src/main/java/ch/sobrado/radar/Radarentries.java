package ch.sobrado.radar;


import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

/**
 * gemappt auf die View radarentries, die json generiert. 
 */
@Entity
public class Radarentries extends PanacheEntityBase implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	public int year; 
	public String jsondata; 
	

}
