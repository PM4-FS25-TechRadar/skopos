package ch.sobrado.radar;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.ArrayList;

@ApplicationScoped
public class TestData {

    @Transactional
    public RadarGroup createGroup(String name) {
        RadarGroup group = new RadarGroup();
        group.name = name;
        group.persistAndFlush();
        return group;
    }

    @Transactional
    public Radar createRadar(String name, RadarGroup group) {
        Radar radar = new Radar();
        radar.name = name;
        radar.radarGroup = group;
        radar.quadrants = new ArrayList<>();
        radar.rings = new ArrayList<>();
        radar.persistAndFlush();
        return radar;
    }

    @Transactional
    public Quadrant addQuadrant(Radar radar, String name) {
        Quadrant quadrant = new Quadrant();
        quadrant.name = name;
        quadrant.radar = radar;
        radar.quadrants.add(quadrant);
        radar.persistAndFlush();
        return quadrant;
    }

    @Transactional
    public Ring addRing(Radar radar, String name) {
        Ring ring = new Ring();
        ring.name = name;
        ring.radar = radar;
        radar.rings.add(ring);
        radar.persistAndFlush();
        return ring;
    }
}