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

-- Quadrants: 
insert into radarvalmap values ('platforms', 0); 
insert into radarvalmap values ('patterns', 1); 
insert into radarvalmap values ('methods', 2); 
insert into radarvalmap values ('data', 3); 

-- Rings: 
insert into radarvalmap values ('adopt', 0); 
insert into radarvalmap values ('trial', 1); 
insert into radarvalmap values ('eval', 2); 
insert into radarvalmap values ('hold', 3); 





-- View die direkt benutzt werden kann 
create or replace view radarentries as 
select year, json_agg ( 
                    json_build_object(
                        'label', label,
						'quadrant', valquadrant.number, 
						'ring', valring.number,
						'moved', moved, 
                        'active', 'true'
                    )
            )::text jsondata
from radar 
join radarvalmap valquadrant on (radar.quadrant = valquadrant.val) 
join radarvalmap valring on (radar.ring = valring.val)
group by year

