-- Default Radar
INSERT INTO public.radar (id, name)
SELECT 1, 'Default Radar'
    WHERE NOT EXISTS (SELECT 1 FROM public.radar WHERE id = 1);

-- Default Quadrants
INSERT INTO public.quadrants (id, name, radar_id, quadrant_order)
SELECT 1, 'data', 1, '0'
    WHERE NOT EXISTS (SELECT 1 FROM public.quadrants WHERE id = 1);

INSERT INTO public.quadrants (id, name, radar_id, quadrant_order)
SELECT 2, 'methods', 1, '1'
    WHERE NOT EXISTS (SELECT 1 FROM public.quadrants WHERE id = 2);

INSERT INTO public.quadrants (id, name, radar_id, quadrant_order)
SELECT 3, 'patterns', 1, '2'
    WHERE NOT EXISTS (SELECT 1 FROM public.quadrants WHERE id = 3);

INSERT INTO public.quadrants (id, name, radar_id, quadrant_order)
SELECT 4, 'platforms', 1, '3'
    WHERE NOT EXISTS (SELECT 1 FROM public.quadrants WHERE id = 4);

-- Default Rings
INSERT INTO public.rings (id, name, description, radar_id, ring_order)
SELECT 1, 'adopt', 'We feel strongly that we should be adopting these items...', 1, '0'
    WHERE NOT EXISTS (SELECT 1 FROM public.rings WHERE id = 1);

INSERT INTO public.rings (id, name, description, radar_id, ring_order)
SELECT 2, 'trial', 'Worth pursuing. It is important to understand how to build up this capability...', 1, '1'
    WHERE NOT EXISTS (SELECT 1 FROM public.rings WHERE id = 2);

INSERT INTO public.rings (id, name, description, radar_id, ring_order)
SELECT 3, 'assess', 'Worth exploring with the goal of understanding how it will affect us...', 1, '2'
    WHERE NOT EXISTS (SELECT 1 FROM public.rings WHERE id = 3);

INSERT INTO public.rings (id, name, description, radar_id, ring_order)
SELECT 4, 'hold', 'Proceed with caution. Should not be extended or used for new projects...', 1, '3'
    WHERE NOT EXISTS (SELECT 1 FROM public.rings WHERE id = 4);

DO $$
DECLARE
radar_id BIGINT := (SELECT id FROM radar LIMIT 1);
    quadrants RECORD;
    rings RECORD;
    version RECORD;
    status TEXT[];
    i INT := 0;
BEGIN
    -- Status options
    status := ARRAY['NEW', 'NO_CHANGE', 'MOVED_UP', 'MOVED_DOWN'];

-- Loop through to create 40 entries
FOR i IN 1..40 LOOP
-- Randomly select a quadrant and a ring
SELECT * INTO quadrants FROM quadrants ORDER BY random() LIMIT 1;
SELECT * INTO rings FROM rings ORDER BY random() LIMIT 1;

-- Randomly select a version
SELECT * INTO version FROM versions ORDER BY random() LIMIT 1;

-- Insert the entry
INSERT INTO entries (radar_id, version_id, quadrant_id, ring_id, status)
VALUES (radar_id, version.id, quadrants.id, rings.id, status[1 + (random() * 3)::int]);
END LOOP;
END $$;