package ch.sobrado.radar;

import jakarta.transaction.Transactional;


public class TestHelper {

    @Transactional
    public static RadarGroup createTestGroup(String name) {
        RadarGroup group = new RadarGroup();
        group.name = name;
        group.persistAndFlush();
        return group;
    }

    @Transactional
    public static Radar createTestRadar(String name, RadarGroup group) {
        Radar radar = new Radar();
        radar.name = name;
        radar.radarGroup = group;
        radar.persistAndFlush();
        return radar;
    }

    @Transactional
    public static Quadrant createTestQuadrant(String name, Radar radar, Position position) {
        Quadrant quadrant = new Quadrant();
        quadrant.name = name;
        quadrant.position = position;
        quadrant.radar = radar;
        quadrant.persistAndFlush();
        return quadrant;
    }

    @Transactional
    public static Ring createTestRing(String name, Radar radar, Position position) {
        Ring ring = new Ring();
        ring.name = name;
        ring.position = position;
        ring.radar = radar;
        ring.persistAndFlush();
        return ring;
    }
}