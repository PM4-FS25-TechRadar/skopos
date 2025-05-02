DROP VIEW IF EXISTS radar_view;

CREATE VIEW radar_view AS
SELECT
    entry.radar_id,
    entry.year,
    -- build one big JSON object per (radar,year)
    json_build_object(
            'title',    radar.name,
            'quadrants',
            ( SELECT json_agg(
                             json_build_object(
                                     'id',    quadrants.id,
                                     'name',  quadrants.name,
                                     'order', quadrants.quadrant_order
                             )
                             ORDER BY quadrants.quadrant_order
                     )
              FROM quadrants
              WHERE quadrants.radar_id = entry.radar_id
            ),
            'rings',
            ( SELECT json_agg(
                             json_build_object(
                                     'id',    rings.id,
                                     'name',  rings.name,
                                     'order', rings.ring_order
                             )
                             ORDER BY rings.ring_order
                     )
              FROM rings
              WHERE rings.radar_id = entry.radar_id
            ),
            'entries',
            json_agg(
                    json_build_object(
                            'label',    entry.label,
                            'quadrant', quadrants.quadrant_order,
                            'ring',     rings.ring_order,
                            'moved',    entry.moved,
                            'active',   true
                    )
                    ORDER BY quadrants.quadrant_order, rings.ring_order, entry.label
            )
    )::text AS jsondata
FROM radar_entry AS entry
         JOIN quadrants ON entry.quadrant_id = quadrants.id
         JOIN rings ON entry.ring_id     = rings.id
         JOIN radar ON entry.radar_id    = radar.id
GROUP BY
    entry.radar_id,
    entry.year,
    radar.name
;
