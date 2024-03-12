-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123

INSERT INTO CLINIC (name, address, start_time, end_time, rating) VALUES ('NEW Hospital', 'Alberta Ajnstajna 1, Novi Sad', '07:00:00.000000000', '19:00:00.000000000', 4.6);
INSERT INTO CLINIC (name, address, start_time, end_time, rating) VALUES ('Healthmedic', 'Narodnog Fronta 73a, Novi Sad', '08:00:00.000000000', '16:00:00.000000000', 5.0);
INSERT INTO CLINIC (name, address, start_time, end_time, rating) VALUES ('Poliklinika Atina', 'Bulevar Evrope 22, Novi Sad', '07:00:00.000000000', '19:00:00.000000000', 4.0);
INSERT INTO CLINIC (name, address, start_time, end_time, rating) VALUES ('Poliklinika Selecta', 'Bulevar cara Lazara 79b, Novi Sad', '09:00:00.000000000', '17:00:00.000000000', 3.6);
INSERT INTO CLINIC (name, address, start_time, end_time, rating) VALUES ('Medigroup', 'Balzakova 44, Novi Sad', '07:00:00.000000000', '20:00:00.000000000', 5.0);
INSERT INTO CLINIC (name, address, start_time, end_time, rating) VALUES ('Poliklinika Consilium', 'Vrsacka 21, Novi Sad', '07:00:00.000000000', '19:00:00.000000000', 2.6);

INSERT INTO USERS (username, password, first_name, last_name, email, address, city, state, phone, lbo, activation_code, denial_reason, status, last_password_reset_date, clinic_id, appointment_type) VALUES ('user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user@example.com', 'Milana Tepica 4', 'Novi Sad', 'Serbia', '06012345678', '123456789', 'ZDZjNzRkNzUtNzVjMy00MzljLTg5OTEtMGM3NGUzMWFkYjQ0MTcwOTI5NTUzMjg3MA', '', 3, '2017-10-01 21:58:58.508-07', null, 1);
INSERT INTO USERS (username, password, first_name, last_name, email, address, city, state, phone, lbo, activation_code, denial_reason, status, last_password_reset_date, clinic_id, appointment_type) VALUES ('admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'admin@example.com', 'Sonje Marinkovic 1', 'Beograd', 'Serbia', '06012345679', '123456781', 'ZDZjNzRkNzUtNzVjMy00MzljLTg5OTEtMGM3NGUzMWFkYjQ0MTcwOTI5NTUzMjg3MB', '', 3, '2017-10-01 18:57:58.508-07', null, 1);
INSERT INTO USERS (username, password, first_name, last_name, email, address, city, state, phone, lbo, activation_code, denial_reason, status, last_password_reset_date, clinic_id, appointment_type) VALUES ('zivko', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Zivko', 'Zivkovic', 'zivko@example.com', 'Futoska 3', 'Nis', 'Serbia', '06012345671', '123456782', 'ZDZjNzRkNzUtNzVjMy00MzljLTg5OTEtMGM3NGUzMWFkYjQ0MTcwOTI5NTUzMjg3MC', '', 3, '2017-10-01 18:57:58.508-07', null, 1);
INSERT INTO USERS (username, password, first_name, last_name, email, address, city, state, phone, lbo, activation_code, denial_reason, status, last_password_reset_date, clinic_id, appointment_type) VALUES ('mitar', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mitar', 'Mitrovic', 'mitar@example.com', 'Branka Bajica 65', 'Subotica', 'Serbia', '06012345672', '123456783', 'ZDZjNzRkNzUtNzVjMy00MzljLTg5OTEtMGM3NGUzMWFkYjQ0MTcwOTI5NTUzMjg3MD', '', 3, '2017-10-01 18:57:58.508-07', 1, 1);
INSERT INTO USERS (username, password, first_name, last_name, email, address, city, state, phone, lbo, activation_code, denial_reason, status, last_password_reset_date, clinic_id, appointment_type) VALUES ('mile', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mile', 'Milovic', 'mile@example.com', 'Cara Lazara 9', 'Novi Sad', 'Serbia', '06012345678', '123456783', 'ZDZjNzRkNzUtNzVjMy00MzljLTg5OTEtMGM3NGUzMWFkYjQ0MTcwOTI5NTUzMjg3ME', '', 3, '2017-10-01 18:57:58.508-07', null, 1);
INSERT INTO USERS (username, password, first_name, last_name, email, address, city, state, phone, lbo, activation_code, denial_reason, status, last_password_reset_date, clinic_id, appointment_type) VALUES ('dragan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Dragan', 'Draganic', 'dragan@example.com', 'Maksima Gorkog 5', 'Novi Sad', 'Serbia', '06012345678', '123456784', 'ZDZjNzRkNzUtNzVjMy00MzljLTg5OTEtMGM3NGUzMWFkYjQ0MTcwOTI5NTUzMjg3MF', '', 3, '2017-10-01 18:57:58.508-07', 2, 1);
INSERT INTO USERS (username, password, first_name, last_name, email, address, city, state, phone, lbo, activation_code, denial_reason, status, last_password_reset_date, clinic_id, appointment_type) VALUES ('pera', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Pera', 'Perovic', 'pera@example.com', 'Momcila Tapavice 13', 'Novi Sad', 'Serbia', '06012345678', '123456785', 'ZDZjNzRkNzUtNzVjMy00MzljLTg5OTEtMGM3NGUzMWFkYjQ0MTcwOTI5NTUzMjg3ML', '', 3, '2017-10-01 18:57:58.508-07', null, 1);

INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_DOCTOR');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1); -- user-u dodeljujemo rolu USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 1); -- admin-u dodeljujemo rolu USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2); -- admin-u dodeljujemo rolu USER
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 1); 
INSERT INTO USER_ROLE (user_id, role_id) VALUES (4, 3); 
INSERT INTO USER_ROLE (user_id, role_id) VALUES (5, 1); 
INSERT INTO USER_ROLE (user_id, role_id) VALUES (6, 3); 
INSERT INTO USER_ROLE (user_id, role_id) VALUES (7, 1);

INSERT INTO RATING (value, clinic_id, doctor_id, user_id) VALUES (5, 1, null, 1);
INSERT INTO RATING (value, clinic_id, doctor_id, user_id) VALUES (3, 2, null, 1);
INSERT INTO RATING (value, clinic_id, doctor_id, user_id) VALUES (4, 3, null, 1);
INSERT INTO RATING (value, clinic_id, doctor_id, user_id) VALUES (5, 4, null, 1);
INSERT INTO RATING (value, clinic_id, doctor_id, user_id) VALUES (5, null, 4, 1);

INSERT INTO ROOM (name) VALUES ('room 1');
INSERT INTO ROOM (name) VALUES ('room 2');
INSERT INTO ROOM (name) VALUES ('room 3');
INSERT INTO ROOM (name) VALUES ('room 4');
INSERT INTO ROOM (name) VALUES ('room 5');
INSERT INTO ROOM (name) VALUES ('room 6');
INSERT INTO ROOM (name) VALUES ('room 7');
INSERT INTO ROOM (name) VALUES ('room 8');
INSERT INTO ROOM (name) VALUES ('room 9');

INSERT INTO APPOINTMENT (date, time, doctor_id, user_id, clinic_id, room_id, status, type) VALUES ('2024-05-30', '12:34:56', 4, null, 1, 1, 0, 1);
INSERT INTO APPOINTMENT (date, time, doctor_id, user_id, clinic_id, room_id, status, type) VALUES ('2024-05-30', '12:34:56', 4, null, 1, 1, 0, 1);
INSERT INTO APPOINTMENT (date, time, doctor_id, user_id, clinic_id, room_id, status, type) VALUES ('2024-05-30', '12:34:56', 4, null, 1, 1, 0, 1);
INSERT INTO APPOINTMENT (date, time, doctor_id, user_id, clinic_id, room_id, status, type) VALUES ('2024-05-30', '12:34:56', 4, null, 1, 1, 0, 1);
INSERT INTO APPOINTMENT (date, time, doctor_id, user_id, clinic_id, room_id, status, type) VALUES ('2024-05-30', '12:34:56', 4, 1, 3, 1, 1, 1);
INSERT INTO APPOINTMENT (date, time, doctor_id, user_id, clinic_id, room_id, status, type) VALUES ('2024-05-30', '12:34:56', 6, null, 1, 1, 0, 1);
INSERT INTO APPOINTMENT (date, time, doctor_id, user_id, clinic_id, room_id, status, type) VALUES ('2024-05-30', '12:34:56', 6, null, 1, 1, 0, 1);

INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (1, 0, 4200);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (1, 1, 4200);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (1, 2, 4200);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (2, 0, 2000);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (2, 1, 2000);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (2, 2, 2000);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (3, 0, 1000);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (3, 1, 1000);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (3, 2, 1000);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (4, 0, 4200);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (4, 1, 4200);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (4, 2, 4200);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (5, 0, 3000);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (5, 1, 3000);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (5, 2, 3000);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (6, 0, 2500);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (6, 1, 2500);
INSERT INTO APPOINTMENT_PRICE (clinic_id, appointment_type, price) VALUES (6, 2, 2500);
