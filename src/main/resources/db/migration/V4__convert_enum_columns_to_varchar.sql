DROP VIEW IF EXISTS radar_view;

ALTER TABLE radar_entry
ALTER COLUMN quadrant TYPE varchar USING quadrant::text,
  ALTER COLUMN ring TYPE varchar USING ring::text;

CREATE OR REPLACE VIEW radar_view AS
SELECT year,
    json_agg(
    json_build_object(
    'label', label,
    'quadrant', valquadrant.number,
    'ring', valring.number,
    'moved', moved,
    'active', 'true'
    )
    )::text AS jsondata
FROM radar_entry
    JOIN radarvalmap valquadrant ON radar_entry.quadrant = valquadrant.val::text
    JOIN radarvalmap valring ON radar_entry.ring = valring.val::text
GROUP BY year;
