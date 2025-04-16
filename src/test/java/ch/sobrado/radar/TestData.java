package ch.sobrado.radar;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.ArrayList;

@ApplicationScoped
public class TestData {

/*
    @Transactional
    public RadarGroup createGroup(String name) {
        RadarGroup group = new RadarGroup();
        group.name = name;
        group.persistAndFlush();
        return group;
    }
*/

    @Transactional
    public Radar createRadar(String name) {
        Radar radar = new Radar();
        radar.name = name;
        radar.quadrants = new ArrayList<>();
        radar.rings = new ArrayList<>();

        // Create 4 default Rings
        for (int i = 1; i <= 4; i++) {
            Ring ring = new Ring();
            ring.name = "Default Ring " + i;
            ring.radar = radar;
            radar.rings.add(ring);
        }

        // Create 4 default Quadrants
        for (int i = 1; i <= 4; i++) {
            Quadrant quadrant = new Quadrant();
            quadrant.name = "Default Quadrant " + i;
            quadrant.radar = radar;
            radar.quadrants.add(quadrant);
        }

        radar.persistAndFlush();
        return radar;
    }

    @Transactional
    public Quadrant replaceQuadrant(Radar radar, int index, String newName) {
        if (index < 0 || index >= radar.quadrants.size()) {
            throw new IllegalArgumentException("Invalid quadrant index: " + index);
        }
        Quadrant quadrant = radar.quadrants.get(index);
        quadrant.name = newName;
        quadrant.persistAndFlush();
        return quadrant;
    }

    @Transactional
    public Ring replaceRing(Radar radar, int index, String newName) {
        if (index < 0 || index >= radar.rings.size()) {
            throw new IllegalArgumentException("Invalid ring index: " + index);
        }
        Ring ring = radar.rings.get(index);
        ring.name = newName;
        ring.persistAndFlush();
        return ring;
    }
}