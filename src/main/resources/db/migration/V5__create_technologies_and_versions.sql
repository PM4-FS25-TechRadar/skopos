CREATE SEQUENCE Technologies_SEQ START WITH 1 INCREMENT BY 50;
CREATE TABLE technologies
(
    id          BIGINT PRIMARY KEY DEFAULT nextval('Technologies_SEQ'),
    name        VARCHAR(255) NOT NULL UNIQUE,
    description TEXT
);

CREATE SEQUENCE Versions_SEQ START WITH 1 INCREMENT BY 50;
CREATE TABLE versions
(
    id            BIGINT PRIMARY KEY DEFAULT nextval('Versions_SEQ'),
    name          VARCHAR(255) NOT NULL,
    description   TEXT,
    technology_id BIGINT       NOT NULL,
    CONSTRAINT fk_technology FOREIGN KEY (technology_id) REFERENCES technologies (id) ON DELETE CASCADE,
    CONSTRAINT unique_version_name_per_tech UNIQUE (name, technology_id)
);
