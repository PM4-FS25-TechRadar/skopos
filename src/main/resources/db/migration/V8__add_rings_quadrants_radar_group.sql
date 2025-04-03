-- Insert sample radar groups
INSERT INTO radar_group (id, name) VALUES (nextval('radar_group_seq'), 'Technology Radar');
INSERT INTO radar_group (id, name) VALUES (nextval('radar_group_seq'), 'Enterprise Architecture');
INSERT INTO radar_group (id, name) VALUES (nextval('radar_group_seq'), 'Development Practices');

-- Insert sample radars
INSERT INTO radar (id, name, group_id) VALUES (nextval('radar_seq'), 'Technology Radar', (SELECT id FROM radar_group WHERE name = 'Technology Radar'));
INSERT INTO radar (id, name, group_id) VALUES (nextval('radar_seq'), 'EA Radar', (SELECT id FROM radar_group WHERE name = 'Enterprise Architecture'));
INSERT INTO radar (id, name, group_id) VALUES (nextval('radar_seq'), 'Development Radar', (SELECT id FROM radar_group WHERE name = 'Development Practices'));

-- Insert standard quadrants for the technology radar
INSERT INTO quadrants (id, name, radar_id) VALUES (nextval('quadrants_seq'), 'Techniques', (SELECT id FROM radar WHERE name = 'Technology Radar'));
INSERT INTO quadrants (id, name, radar_id) VALUES (nextval('quadrants_seq'), 'Platforms', (SELECT id FROM radar WHERE name = 'Technology Radar'));
INSERT INTO quadrants (id, name, radar_id) VALUES (nextval('quadrants_seq'), 'Tools', (SELECT id FROM radar WHERE name = 'Technology Radar'));
INSERT INTO quadrants (id, name, radar_id) VALUES (nextval('quadrants_seq'), 'Languages & Frameworks', (SELECT id FROM radar WHERE name = 'Technology Radar'));

-- Duplicate for the EA radar
INSERT INTO quadrants (id, name, radar_id) VALUES (nextval('quadrants_seq'), 'Techniques', (SELECT id FROM radar WHERE name = 'EA Radar'));
INSERT INTO quadrants (id, name, radar_id) VALUES (nextval('quadrants_seq'), 'Platforms', (SELECT id FROM radar WHERE name = 'EA Radar'));
INSERT INTO quadrants (id, name, radar_id) VALUES (nextval('quadrants_seq'), 'Tools', (SELECT id FROM radar WHERE name = 'EA Radar'));
INSERT INTO quadrants (id, name, radar_id) VALUES (nextval('quadrants_seq'), 'Languages & Frameworks', (SELECT id FROM radar WHERE name = 'EA Radar'));

-- Custom quadrants for Development radar
INSERT INTO quadrants (id, name, radar_id) VALUES (nextval('quadrants_seq'), 'Frontend', (SELECT id FROM radar WHERE name = 'Development Radar'));
INSERT INTO quadrants (id, name, radar_id) VALUES (nextval('quadrants_seq'), 'Backend', (SELECT id FROM radar WHERE name = 'Development Radar'));
INSERT INTO quadrants (id, name, radar_id) VALUES (nextval('quadrants_seq'), 'Infrastructure', (SELECT id FROM radar WHERE name = 'Development Radar'));
INSERT INTO quadrants (id, name, radar_id) VALUES (nextval('quadrants_seq'), 'Testing', (SELECT id FROM radar WHERE name = 'Development Radar'));

-- Standard rings for all radars
INSERT INTO rings (id, name, radar_id) VALUES (nextval('rings_seq'), 'Adopt', (SELECT id FROM radar WHERE name = 'Technology Radar'));
INSERT INTO rings (id, name, radar_id) VALUES (nextval('rings_seq'), 'Trial', (SELECT id FROM radar WHERE name = 'Technology Radar'));
INSERT INTO rings (id, name, radar_id) VALUES (nextval('rings_seq'), 'Assess', (SELECT id FROM radar WHERE name = 'Technology Radar'));
INSERT INTO rings (id, name, radar_id) VALUES (nextval('rings_seq'), 'Hold', (SELECT id FROM radar WHERE name = 'Technology Radar'));

-- Rings for EA radar
INSERT INTO rings (id, name, radar_id) VALUES (nextval('rings_seq'), 'Adopt', (SELECT id FROM radar WHERE name = 'EA Radar'));
INSERT INTO rings (id, name, radar_id) VALUES (nextval('rings_seq'), 'Trial', (SELECT id FROM radar WHERE name = 'EA Radar'));
INSERT INTO rings (id, name, radar_id) VALUES (nextval('rings_seq'), 'Assess', (SELECT id FROM radar WHERE name = 'EA Radar'));
INSERT INTO rings (id, name, radar_id) VALUES (nextval('rings_seq'), 'Hold', (SELECT id FROM radar WHERE name = 'EA Radar'));

-- Rings for Development radar
INSERT INTO rings (id, name, radar_id) VALUES (nextval('rings_seq'), 'Strategic', (SELECT id FROM radar WHERE name = 'Development Radar'));
INSERT INTO rings (id, name, radar_id) VALUES (nextval('rings_seq'), 'Tactical', (SELECT id FROM radar WHERE name = 'Development Radar'));
INSERT INTO rings (id, name, radar_id) VALUES (nextval('rings_seq'), 'Operational', (SELECT id FROM radar WHERE name = 'Development Radar'));
INSERT INTO rings (id, name, radar_id) VALUES (nextval('rings_seq'), 'Legacy', (SELECT id FROM radar WHERE name = 'Development Radar'));


