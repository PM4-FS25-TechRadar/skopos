ALTER TABLE quadrants
    ADD position INT;

UPDATE quadrants SET position = '0' WHERE name IN ('data');
UPDATE quadrants SET position = '1' WHERE name IN ('methods');
UPDATE quadrants SET position = '2' WHERE name IN ('patterns');
UPDATE quadrants SET position = '3' WHERE name IN ('platforms');

ALTER TABLE quadrants
    ALTER COLUMN position SET NOT NULL;

ALTER TABLE rings
    ADD position INT;

UPDATE rings SET position = '0' WHERE name IN ('adopt');
UPDATE rings SET position = '1' WHERE name IN ('trial');
UPDATE rings SET position = '2' WHERE name IN ('assess');
UPDATE rings SET position = '3' WHERE name IN ('hold');

ALTER TABLE rings
    ALTER COLUMN position SET NOT NULL;

ALTER TABLE radar_entry
    ADD radar_id BIGINT;

ALTER TABLE quadrants
    ADD CONSTRAINT uc_68c0daf04a1435c73b012fc81 UNIQUE (name, position, radar_id);

ALTER TABLE rings
    ADD CONSTRAINT uc_bae11565e97dc7bea7c628023 UNIQUE (name, position, radar_id);

ALTER TABLE radar_entry
    ADD CONSTRAINT FK_RADAR_ENTRY_ON_RADAR FOREIGN KEY (radar_id) REFERENCES radar (id);

DROP TABLE radarvalmap CASCADE;

ALTER TABLE radar_entry
DROP
COLUMN quadrant;

ALTER TABLE radar_entry
DROP
COLUMN ring;

ALTER TABLE technologies
ALTER
COLUMN description TYPE VARCHAR(255) USING (description::VARCHAR(255));

ALTER TABLE versions
ALTER
COLUMN description TYPE VARCHAR(255) USING (description::VARCHAR(255));

ALTER TABLE radar_entry
ALTER
COLUMN label TYPE VARCHAR(255) USING (label::VARCHAR(255));

ALTER TABLE radar_entry
    ALTER COLUMN label DROP NOT NULL;

ALTER TABLE radar_entry
    ALTER COLUMN moved SET NOT NULL;

ALTER TABLE radar_entry
    ALTER COLUMN year SET NOT NULL;

CREATE OR REPLACE VIEW radar_view AS
SELECT year,
    json_agg(
    json_build_object(
    'label', label,
    'quadrant', quadrants.position,
    'ring', rings.position,
    'moved', moved,
    'active', 'true'
    )
    )::text AS jsondata
FROM radar_entry
    JOIN quadrants ON radar_entry.quadrant_id = quadrants.id
    JOIN rings ON radar_entry.ring_id = rings.id
GROUP BY year;