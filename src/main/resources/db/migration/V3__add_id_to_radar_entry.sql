ALTER TABLE radar_entry DROP CONSTRAINT radar_pkey;
ALTER TABLE radar_entry ADD COLUMN id BIGSERIAL;
ALTER TABLE radar_entry ADD PRIMARY KEY (id);
ALTER SEQUENCE radar_entry_id_seq RENAME TO radar_entry_SEQ;
ALTER SEQUENCE radar_entry_SEQ INCREMENT BY 50;