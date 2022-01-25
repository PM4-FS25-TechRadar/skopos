
-- Repeatable Flyway Migration for the current year's Data still being discussed: 
-- (will be replaced by a GUI in future)



delete from radar where year = 2022; 

-- Data and Reporting

INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('MariaDB', 'data', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('PostgreSQL', 'data', 'eval', 1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Grafana', 'data', 'eval', 1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Neo4J', 'data', 'eval', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('JSONB', 'data', 'eval', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('MongoDB', 'data', 'hold', -1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Redis', 'data', 'hold', -1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('RabbitMQ', 'data', 'hold', -1, 2022);

INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Doctrine', 'data', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('chart.js', 'data', 'trial', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Tableau', 'data', 'trial', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('MySQL Workbench', 'data', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('MongoDB Compass', 'data', 'adopt', 0, 2022);



-- Methods 


INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('DDD', 'methods', 'trial', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('TDD', 'methods', 'trial', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Cypress', 'methods', 'trial', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Scrum', 'methods', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('M&P', 'methods', 'eval', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('TestRail', 'methods', 'eval', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('ADR', 'methods', 'adopt', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Figma', 'methods', 'adopt', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Confluence', 'methods', 'hold', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Jira', 'methods', 'hold', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('TechRadar', 'methods', 'trial', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Arc42', 'methods', 'adopt', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('NewRelic', 'methods', 'hold', -1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Sentry', 'methods', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Kibana', 'methods', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Trunk Based', 'methods', 'eval', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('FeatureToggle', 'methods', 'eval', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('ContDelivery', 'methods', 'eval', 0, 2022);


-- Patterns

INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('GrpaphQL', 'patterns', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Altair GrpaphQL', 'patterns', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('REST', 'patterns', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('SharedDB', 'patterns', 'trial', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('RBAC', 'patterns', 'hold', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('ABAC', 'patterns', 'eval', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Strangler', 'patterns', 'trial', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Citadelle', 'patterns', 'adopt', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Gateway', 'patterns', 'hold', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Microservice', 'patterns', 'hold', -1, 2022);


-- Platforms 

INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Java 17', 'platforms', 'trial', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Java 11', 'platforms', 'hold', -1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Spring', 'platforms', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Angular', 'platforms', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('ReactJS', 'platforms', 'trial', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('PHP', 'platforms', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Zend', 'platforms', 'hold', -1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Laminas', 'platforms', 'adopt', +1, 2022);

INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Keycloak', 'platforms', 'eval', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('OpenID Connect', 'platforms', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('jwt', 'platforms', 'adopt', 0, 2022);

INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Quarkus', 'platforms', 'eval', +1, 2022);

INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('OpenShift 3', 'platforms', 'hold', -1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Virtual Machines', 'platforms', 'hold', -1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('OpenShift 4', 'platforms', 'trial', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('GKE', 'platforms', 'eval', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Jenkins', 'platforms', 'hold', -1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Selenium', 'platforms', 'hold', -1, 2022);
