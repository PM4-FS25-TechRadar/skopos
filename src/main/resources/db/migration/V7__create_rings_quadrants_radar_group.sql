CREATE SEQUENCE quadrants_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE radar_group_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE radar_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE rings_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE quadrants
(
    id       BIGINT DEFAULT nextval('quadrants_seq'),
    name     VARCHAR(255) NOT NULL,
    radar_id BIGINT       NOT NULL,
    CONSTRAINT pk_quadrants PRIMARY KEY (id)
);

CREATE TABLE radar
(
    id       BIGINT DEFAULT nextval('radar_seq'),
    name     VARCHAR(255) NOT NULL,
    group_id BIGINT,
    CONSTRAINT pk_radar PRIMARY KEY (id)
);

CREATE TABLE radar_group
(
    id       BIGINT DEFAULT nextval('radar_group_seq'),
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_radar_group PRIMARY KEY (id)
);

CREATE TABLE rings
(
    id       BIGINT DEFAULT nextval('rings_seq'),
    name     VARCHAR(255) NOT NULL,
    radar_id BIGINT       NOT NULL,
    CONSTRAINT pk_rings PRIMARY KEY (id)
);

ALTER TABLE radar_entry
    ADD quadrant_id BIGINT;

ALTER TABLE radar_entry
    ADD ring_id BIGINT;

ALTER TABLE rings
    ADD CONSTRAINT uc_f082c4fb28250ad5a3d2d1222 UNIQUE (name, radar_id);

ALTER TABLE quadrants
    ADD CONSTRAINT uc_f6c137d5f8b9a85e6dad4a16d UNIQUE (name, radar_id);

ALTER TABLE quadrants
    ADD CONSTRAINT FK_QUADRANTS_ON_RADAR FOREIGN KEY (radar_id) REFERENCES radar (id);

ALTER TABLE radar_entry
    ADD CONSTRAINT FK_RADAR_ENTRY_ON_QUADRANT FOREIGN KEY (quadrant_id) REFERENCES quadrants (id);

ALTER TABLE radar_entry
    ADD CONSTRAINT FK_RADAR_ENTRY_ON_RING FOREIGN KEY (ring_id) REFERENCES rings (id);

ALTER TABLE radar
    ADD CONSTRAINT FK_RADAR_ON_GROUP FOREIGN KEY (group_id) REFERENCES radar_group (id);

ALTER TABLE rings
    ADD CONSTRAINT FK_RINGS_ON_RADAR FOREIGN KEY (radar_id) REFERENCES radar (id);
