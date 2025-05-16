ALTER VIEW radar_view RENAME TO radar_view_old;
DROP VIEW IF EXISTS radar_view_old CASCADE;

CREATE OR REPLACE VIEW radar_view AS
SELECT
    r.id AS radar_id,
    NULL::integer AS year,
    json_build_object(
            'title', r.name,
            'quadrants', (
                SELECT json_agg(json_build_object(
                        'id', q.id,
                        'name', q.name,
                        'description', q.description,
                        'order', q.quadrant_order
                                ) ORDER BY q.quadrant_order)
                FROM quadrants q
                WHERE q.radar_id = r.id
            ),
            'rings', (
                SELECT json_agg(json_build_object(
                        'id', ri.id,
                        'name', ri.name,
                        'description', ri.description,
                        'order', ri.ring_order
                                ) ORDER BY ri.ring_order)
                FROM rings ri
                WHERE ri.radar_id = r.id
            ),
            'entries', (
                SELECT json_agg(json_build_object(
                                        'label', v.name,
                                        'quadrant', q.quadrant_order,
                                        'ring', ri.ring_order,
                                        'moved', 0,
                                        'active', true
                                ) ORDER BY q.quadrant_order, ri.ring_order, v.name)
                FROM entries e
                         JOIN versions v ON e.version_id = v.id
                         JOIN quadrants q ON e.quadrant_id = q.id
                         JOIN rings ri ON e.ring_id = ri.id
                WHERE e.radar_id = r.id
            )
    )::text AS jsondata
FROM radar r;