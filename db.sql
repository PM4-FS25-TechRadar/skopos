create table radar (
    label text primary key; 
    quadrant int, 
    ring int, 
    moved int, 
    year int    
)




INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('MariaDB', 3, 2, -1, 2021);

INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('PostgreSQL', 3, 0, 1, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('Neo4J', 3, 1, 0, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('MS SQL', 3, 3, -1, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('DDD', 2, 1, 1, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('TDD', 2, 1, 1, 2021);

INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('GrpaphQL', 1, 0, 0, 2021);
INSERT INTO public.radar(label, quadrant, ring, moved, year) VALUES ('REST', 1, 0, 0, 2021);




select json_build_object(
    'entries', json_agg( 
                    json_build_object(
                        'label', label,
						'quadrant', quadrant, 
						'ring', ring,
						'moved', moved, 
                        'active', 'true'
                    )
                )
)
from radar; 

-- mit Enums




create type radarval as enum 
(
    'platforms', 'patterns', 'methods', 'data',
    'adopt', 'trial', 'eval', 'hold'
)


create table radar_e (
    label text primary key; 
    quadrant radarval, 
    ring radarval, 
    moved int, 
    year int    
)


INSERT INTO public.radar_e(label, quadrant, ring, moved, year) VALUES ('MariaDB', 'data', 'adopt', -1, 2021);
INSERT INTO public.radar_e(label, quadrant, ring, moved, year) VALUES ('PostgreSQL', 'data', 'eval', 1, 2021);
INSERT INTO public.radar_e(label, quadrant, ring, moved, year) VALUES ('Neo4J', 'data', 'eval', 0, 2021);
INSERT INTO public.radar_e(label, quadrant, ring, moved, year) VALUES ('MS SQL', 'data', 'hold', -1, 2021);
INSERT INTO public.radar_e(label, quadrant, ring, moved, year) VALUES ('DDD', 'methods', 'adopt', 1, 2021);
INSERT INTO public.radar_e(label, quadrant, ring, moved, year) VALUES ('TDD', 'methods', 'adopt', 1, 2021);
INSERT INTO public.radar_e(label, quadrant, ring, moved, year) VALUES ('GrpaphQL', 'patterns', 'adopt', 0, 2021);
INSERT INTO public.radar_e(label, quadrant, ring, moved, year) VALUES ('REST', 'patterns', 'adopt', 0, 2021);


create table radarvalmap (
    val radarval, 
    number int, 
    unique (val, number)
)


insert into radarvalmap values ('platforms', 0); 
insert into radarvalmap values ('patterns', 1); 
insert into radarvalmap values ('methods', 2); 
insert into radarvalmap values ('data', 3); 


insert into radarvalmap values ('adopt', 0); 
insert into radarvalmap values ('trial', 1); 
insert into radarvalmap values ('eval', 2); 
insert into radarvalmap values ('hold', 3); 



select *
from radar_e 
join radarvalmap valq on (radar_e.quadrant = valq.val) 
join radarvalmap valr on (radar_e.ring = valr.val)
where label = 'MariaDB'



select json_build_object(
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
from radar_e 
join radarvalmap valq on (radar_e.quadrant = valq.val) 
join radarvalmap valr on (radar_e.ring = valr.val)