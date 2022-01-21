-- DATA 2022

delete from radar where year = 2022; 


INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('MariaDB', 'data', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('PostgreSQL', 'data', 'eval', 1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Neo4J', 'data', 'eval', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('MongoDB', 'data', 'hold', -1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Doctrine', 'data', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('chart.js', 'data', 'trial', +1, 2022);




INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('DDD', 'methods', 'adopt', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('TDD', 'methods', 'trial', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Scrum', 'methods', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('ADR', 'methods', 'adopt', +1, 2022);



INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('GrpaphQL', 'patterns', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('REST', 'patterns', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('SharedDB', 'patterns', 'trial', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Strangler', 'patterns', 'adopt', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Citadelle', 'patterns', 'adopt', +1, 2022);



INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Java', 'platforms', 'adopt', 1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Spring', 'platforms', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Angular', 'platforms', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('ReactJS', 'platforms', 'trial', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('PHP', 'platforms', 'adopt', 0, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Zend', 'platforms', 'hold', -1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Laminas', 'platforms', 'adopt', +1, 2022);

INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Keycloak', 'platforms', 'eval', +1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('OpenShift 3', 'platforms', 'hold', -1, 2022);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('OpenShift 4', 'platforms', 'trial', +1, 2022);