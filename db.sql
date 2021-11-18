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
						'moved', moved
                    )
                )
)
from radar; 

