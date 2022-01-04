DROP TABLE IF EXISTS device;
CREATE TABLE device (
                          id bigint NOT NULL AUTO_INCREMENT,
                          nfc_signal tinyblob,
                          one_time_token varchar(255) DEFAULT NULL,
                          pin varchar(255) DEFAULT NULL,
                          is_face_unlock_enabled bit(1) DEFAULT NULL,
                          PRIMARY KEY (id)
);

INSERT INTO device VALUES (1,NULL,NULL,'141414',NULL);