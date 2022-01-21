



-- deprecated 
create or replace view radarjson as 
select year, json_build_object(
    'entries', json_agg( 
                    json_build_object(
                        'label', label,
						'quadrant', valquadrant.number, 
						'ring', valring.number,
						'moved', moved, 
                        'active', 'true'
                    )
                )
)
from radar 
join radarvalmap valquadrant on (radar.quadrant = valquadrant.val) 
join radarvalmap valring on (radar.ring = valring.val)
group by year





select *
from radar 
join radarvalmap valquadrant on (radar.quadrant = valquadrant.val) 
join radarvalmap valring on (radar.ring = valring.val)
where label = 'MariaDB'; 





