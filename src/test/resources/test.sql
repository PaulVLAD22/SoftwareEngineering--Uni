DROP TABLE IF EXISTS device;
CREATE TABLE device (
                          id bigint NOT NULL AUTO_INCREMENT,
                          nfc_signal tinyblob,
                          one_time_token varchar(255) DEFAULT NULL,
                          pin varchar(255) DEFAULT NULL,
                          email varchar(255) DEFAULT NULL,
                          totp_secret varchar(255) DEFAULT NULL,
                          number_of_wrong_inputs int DEFAULT NULL,
                          is_face_unlock_enabled bit(1) DEFAULT NULL,
                          is_blocked bit(1) DEFAULT NULL,
                          is_locked bit(1) DEFAULT NULL,
                          last_wrong_input_date time DEFAULT NULL,
                          PRIMARY KEY (id)
);

INSERT INTO device (id, nfc_signal, pin, is_face_unlock_enabled, email, is_blocked, last_wrong_input_date, number_of_wrong_inputs, totp_secret, is_locked) VALUES (1, null, '141414', false, 'mail@mail.com', false, null, 0, '', true);