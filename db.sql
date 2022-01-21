
-- um alles zu löschen: 
drop owned by sob cascade 








select *
from radar 
join radarvalmap valquadrant on (radar.quadrant = valquadrant.val) 
join radarvalmap valring on (radar.ring = valring.val)
where label = 'MariaDB'; 





