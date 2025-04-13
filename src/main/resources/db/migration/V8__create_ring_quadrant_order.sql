ALTER TABLE rings
    ADD ring_order INTEGER;

ALTER TABLE quadrants
    ADD quadrant_order INTEGER;

ALTER TABLE radar_entry
    ADD radar_id BIGINT;

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
    'quadrant', quadrants.quadrant_order,
    'ring', rings.ring_order,
    'moved', moved,
    'active', 'true'
    )
    )::text AS jsondata
FROM radar_entry
    JOIN quadrants ON radar_entry.quadrant_id = quadrants.id
    JOIN rings ON radar_entry.ring_id = rings.id
GROUP BY year;