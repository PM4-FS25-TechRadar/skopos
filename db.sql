create type radarval as enum 
(
    'platforms', 'patterns', 'methods', 'data',
    'adopt', 'trial', 'eval', 'hold'
); 


create table radar (
    label text primary key, 
    quadrant radarval, 
    ring radarval, 
    moved int, 
    year int    
); 


create table radarvalmap (
    val radarval, 
    number int, 
    unique (val, number)
); 


insert into radarvalmap values ('platforms', 0); 
insert into radarvalmap values ('patterns', 1); 
insert into radarvalmap values ('methods', 2); 
insert into radarvalmap values ('data', 3); 


insert into radarvalmap values ('adopt', 0); 
insert into radarvalmap values ('trial', 1); 
insert into radarvalmap values ('eval', 2); 
insert into radarvalmap values ('hold', 3); 





-- deprecated 
create or replace view radarjson as 
select year, json_build_object(
    'entries', json_agg( 
                    json_build_object(
                        'label', label,
						'quadrant', valq.number, 
						'ring', valr.number,
						'moved', moved, 
                        'active', 'true'
                    )
                )
)
from radar 
join radarvalmap valq on (radar.quadrant = valq.val) 
join radarvalmap valr on (radar.ring = valr.val)
group by year


create or replace view radarentries as 
select year, json_agg ( 
                    json_build_object(
                        'label', label,
						'quadrant', valq.number, 
						'ring', valr.number,
						'moved', moved, 
                        'active', 'true'
                    )
            )
from radar 
join radarvalmap valq on (radar.quadrant = valq.val) 
join radarvalmap valr on (radar.ring = valr.val)
group by year





select *
from radar 
join radarvalmap valq on (radar.quadrant = valq.val) 
join radarvalmap valr on (radar.ring = valr.val)
where label = 'MariaDB'; 



-- DATA 


INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('MariaDB', 'data', 'adopt', 0, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('PostgreSQL', 'data', 'eval', 1, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Neo4J', 'data', 'eval', 0, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('MongoDB', 'data', 'hold', -1, 2021);


INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('DDD', 'methods', 'adopt', 1, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('TDD', 'methods', 'trial', +1, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Scrum', 'methods', 'adopt', 0, 2021);



INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('GrpaphQL', 'patterns', 'adopt', 0, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('REST', 'patterns', 'adopt', 0, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('SharedDB', 'patterns', 'trial', +1, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Strangler', 'patterns', 'adopt', +1, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Citadelle', 'patterns', 'adopt', +1, 2021);



INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Java', 'platforms', 'adopt', 1, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Spring', 'platforms', 'adopt', 0, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Angular', 'platforms', 'adopt', 0, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('ReactJS', 'platforms', 'trial', +1, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('PHP', 'platforms', 'adopt', -1, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('chart.js', 'platforms', 'trial', +1, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Keycloak', 'platforms', 'eval', +1, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('OpenShift', 'platforms', 'adopt', 0, 2021);


