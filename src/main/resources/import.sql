-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123

INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date, clinic_id) VALUES ('user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user@example.com', true, '2017-10-01 21:58:58.508-07', null);
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date, clinic_id) VALUES ('admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', 'admin@example.com', true, '2017-10-01 18:57:58.508-07', null);
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date, clinic_id) VALUES ('zivko', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Zivko', 'Zivkovic', 'zivko@example.com', true, '2017-10-01 18:57:58.508-07', null);
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date, clinic_id) VALUES ('mitar', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mitar', 'Mitrovic', 'mitar@example.com', true, '2017-10-01 18:57:58.508-07', null);
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date, clinic_id) VALUES ('mile', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mile', 'Milovic', 'mile@example.com', true, '2017-10-01 18:57:58.508-07', null);
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date, clinic_id) VALUES ('dragan', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Dragan', 'Draganic', 'dragan@example.com', true, '2017-10-01 18:57:58.508-07', null);
INSERT INTO USERS (username, password, first_name, last_name, email, enabled, last_password_reset_date, clinic_id) VALUES ('pera', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Pera', 'Perovic', 'pera@example.com', true, '2017-10-01 18:57:58.508-07', null);

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
INSERT INTO USER_ROLE (user_id, role_id) VALUES (7, 3); 

INSERT INTO CLINIC (name, address) VALUES ('NEW Hospital', 'Alberta Ajnstajna 1, Novi Sad');
INSERT INTO CLINIC (name, address) VALUES ('Healthmedic', 'Narodnog Fronta 73a, Novi Sad');
INSERT INTO CLINIC (name, address) VALUES ('Poliklinika Atina', 'Bulevar Evrope 22, Novi Sad');
INSERT INTO CLINIC (name, address) VALUES ('Poliklinika Selecta', 'Bulevar cara Lazara 79b, Novi Sad');
INSERT INTO CLINIC (name, address) VALUES ('Medigroup', 'Balzakova 44, Novi Sad');
INSERT INTO CLINIC (name, address) VALUES ('Poliklinika Consilium', 'Vrsacka 21, Novi Sad');

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

INSERT INTO APPOINTMENT (date, doctor_id, user_id, clinic_id, room_id, status) VALUES ('2024-05-30T12:34:56', 4, null, 1, 1, 0);
INSERT INTO APPOINTMENT (date, doctor_id, user_id, clinic_id, room_id, status) VALUES ('2024-05-30T12:34:56', 4, null, 2, 1, 0);
INSERT INTO APPOINTMENT (date, doctor_id, user_id, clinic_id, room_id, status) VALUES ('2024-05-30T12:34:56', 4, null, 1, 1, 0);
INSERT INTO APPOINTMENT (date, doctor_id, user_id, clinic_id, room_id, status) VALUES ('2024-05-30T12:34:56', 4, null, 2, 1, 0);
INSERT INTO APPOINTMENT (date, doctor_id, user_id, clinic_id, room_id, status) VALUES ('2024-05-30T12:34:56', 4, 1, 3, 1, 1);
INSERT INTO APPOINTMENT (date, doctor_id, user_id, clinic_id, room_id, status) VALUES ('2024-05-30T12:34:56', 6, null, 4, 1, 0);
INSERT INTO APPOINTMENT (date, doctor_id, user_id, clinic_id, room_id, status) VALUES ('2024-05-30T12:34:56', 6, null, 4, 1, 0);
