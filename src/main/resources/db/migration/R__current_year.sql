
-- Repeatable Flyway Migration for the current year's Data still being discussed: 
-- (will be replaced by a GUI in future)



DELETE FROM radar_entry where year = 2022;

-- Data and Reporting
-- Default Group
INSERT INTO public.radar_group(id, name) VALUES (1,'Default Group');

-- Default Radar
INSERT INTO public.radar (id, name, group_id) VALUES (1, 'Default Radar', 1);

-- Default Quadrants
INSERT INTO public.quadrants (id, name, radar_id, quadrant_order) VALUES (1, 'data', 1, '0');
INSERT INTO public.quadrants (id, name, radar_id, quadrant_order) VALUES (2, 'methods', 1, '1');
INSERT INTO public.quadrants (id, name, radar_id, quadrant_order) VALUES (3, 'patterns', 1, '2');
INSERT INTO public.quadrants (id, name, radar_id, quadrant_order) VALUES (4, 'platforms', 1, '3');

-- Default Rings
INSERT INTO public.rings (id, name, radar_id, ring_order) VALUES (1, 'adopt', 1, '0');
INSERT INTO public.rings (id, name, radar_id, ring_order) VALUES (2, 'trial', 1, '1');
INSERT INTO public.rings (id, name, radar_id, ring_order) VALUES (3, 'assess', 1, '2');
INSERT INTO public.rings (id, name, radar_id, ring_order) VALUES (4, 'hold', 1, '3');

-- Data
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('MariaDB', 1, 1, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Hibernate', 1, 1, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('PostgreSQL', 1, 3, 1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Grafana', 1, 3, 1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Neo4J', 1, 3, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('JSONB', 1, 3, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('MongoDB', 1, 4, -1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Redis', 1, 4, -1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('RabbitMQ', 1, 4, -1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('chart.js', 1, 2, 1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Tableau', 1, 2, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('MySQL Workbench', 1, 1, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('MongoDB Compass', 1, 1, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Debezium', 1, 3, +1, 2022, 1);

-- Methods
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('DDD', 2, 2, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('TDD', 2, 2, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Cypress', 2, 2, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Scrum', 2, 1, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('TestRail', 2, 3, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('ADR', 2, 1, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Figma', 2, 1, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Confluence', 2, 4, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Jira', 2, 4, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('TechRadar', 2, 2, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Arc42', 2, 1, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('NewRelic', 2, 4, -1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Sentry', 2, 1, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Kibana', 2, 1, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('ScanMeter', 2, 2, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Trunk Based', 2, 3, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('FeatureToggle', 2, 3, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('ContDelivery', 2, 3, 0, 2022, 1);


-- Patterns

INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('GrpaphQL', 3, 1, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Altair GrpaphQL', 3, 1, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('REST', 3, 1, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('OpenAPI', 3, 1, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('SOAP', 3, 4, -1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('SharedDB', 3, 2, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('RBAC', 3, 4, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('ABAC', 3, 3, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('OpenID Connect', 3, 1, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('OAuth', 3, 4, -1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('jwt', 3, 1, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('CertBased Auth', 3, 4, -1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Strangler', 3, 2, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('FeatureFlag', 3, 2, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Citadelle', 3, 1, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Gateway', 3, 4, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Microservice', 3, 4, -1, 2022, 1);


-- Platforms 

INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Java 17', 4, 2, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('JIB', 4, 1, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Java 11', 4, 4, -1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Spring', 4, 1, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Angular', 4, 1, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('VueJS', 4, 2, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('PHP', 4, 4, 0, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Zend', 4, 4, -1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Laminas', 4, 4, 0, 2022, 1);

INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Keycloak', 4, 3, +1, 2022, 1);


INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Quarkus', 4, 3, +1, 2022, 1);

INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('OpenShift 3', 4, 4, -1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Virtual Machines', 4, 4, -1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('OpenShift 4', 4, 2, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('GKE', 4, 3, +1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Jenkins', 4, 4, -1, 2022, 1);
INSERT INTO public.radar_entry(label, quadrant_id, ring_id, moved, year, radar_id) VALUES ('Selenium', 4, 4, -1, 2022, 1);
