INSERT INTO pacientes (id, dni, nombre, telefono, edad, email) VALUES (1, 100, 'Alberto Arana', '1234', '23', 'albertoa@mail.com');
INSERT INTO pacientes (id, dni, nombre, telefono, edad, email) VALUES (2, 101, 'Bruno Branca', '1234', '32', 'brunob@mail.com');
INSERT INTO pacientes (id, dni, nombre, telefono, edad, email) VALUES (3, 102, 'Camila Castro', '1234', '19', 'camicas@mail.com');
INSERT INTO pacientes (id, dni, nombre, telefono, edad, email) VALUES (4, 103, 'Domingo Dominguez', '1234', '30', 'domingod@mail.com');
INSERT INTO pacientes (id, dni, nombre, telefono, edad, email) VALUES (5, 104, 'Facundo Festa', '1234', '40', 'festaf@mail.com');


INSERT INTO turnos (id, horario_inicio, horario_fin, paciente_id, tipo) VALUES (4, '2023-03-09 10:00:00', '2023-03-09 10:30:00', 4, 0);
INSERT INTO turnos (id, horario_inicio, horario_fin, paciente_id, tipo) VALUES (5, '2023-03-09 11:30:00', '2023-03-09 12:00:00', 5, 0);
INSERT INTO turnos (id, horario_inicio, horario_fin, paciente_id, tipo) VALUES (6, '2023-04-09 07:00:00', '2023-04-09 07:30:00', 2, 1);
INSERT INTO turnos (id, horario_inicio, horario_fin, paciente_id, tipo) VALUES (7, '2023-04-09 07:30:00', '2023-04-09 08:00:00', 1, 0);
INSERT INTO turnos (id, horario_inicio, horario_fin, paciente_id, tipo) VALUES (8, '2023-04-09 09:00:00', '2023-04-09 09:30:00', 4, 1);
INSERT INTO turnos (id, horario_inicio, horario_fin, paciente_id, tipo) VALUES (9, '2023-04-09 10:00:00', '2023-04-09 10:30:00', 5, 1);
INSERT INTO turnos (id, horario_inicio, horario_fin, paciente_id, tipo) VALUES (10, '2023-04-09 12:00:00', '2023-04-09 13:00:00', 3, 2);

