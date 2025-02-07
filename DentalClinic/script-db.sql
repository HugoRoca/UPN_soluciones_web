CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
      username VARCHAR(50) NOT NULL UNIQUE,
      password VARCHAR(255) NOT NULL,
      rol VARCHAR(50) NOT NULL,
      name VARCHAR(100) NOT NULL,
      nickname VARCHAR(50),
      lastname VARCHAR(100) NOT NULL
);

INSERT INTO user (username, password, rol, name, nickname, lastname) VALUES
    ('jdoe', '123', 'admin', 'John', 'JD', 'Doe'),
    ('asmith', '123', 'user', 'Alice', 'Ali', 'Smith'),
    ('bwayne', '123', 'moderator', 'Bruce', 'Bats', 'Wayne'),
    ('ckent', '123', 'user', 'Clark', 'Supes', 'Kent'),
    ('pparker', '123', 'editor', 'Peter', 'Spidey', 'Parker');

select * from user

--------
CREATE TABLE person(
	id INT AUTO_INCREMENT PRIMARY KEY,
	documenttype VARCHAR(5) NOT NULL,
	documentnumber VARCHAR(20) NOT NULL,
	name VARCHAR(200) NOT NULL,
	lastname VARCHAR(200) NOT NULL,
	email VARCHAR(200) NOT NULL,
	phone VARCHAR(200) NOT NULL,
	address VARCHAR(200) NOT NULL
)

INSERT INTO person (documenttype, documentnumber, name, lastname, email, phone, address)
VALUES
('DNI', '12345678', 'Carlos Eduardo', 'Pérez Gómez', 'carlos.perez@example.com', '987654321', 'Av. Los Olivos 123'),
('DNI', '87654321', 'María Fernanda', 'López Sánchez', 'maria.lopez@example.com', '987654322', 'Jr. Las Flores 456'),
('DNI', '23456789', 'Juan Pablo', 'Ramírez Torres', 'juan.ramirez@example.com', '987654323', 'Calle 7 de Junio 789'),
('DNI', '34567890', 'Ana Sofía', 'Mendoza Castro', 'ana.mendoza@example.com', '987654324', 'Pasaje San Martín 321'),
('DNI', '45678901', 'José Manuel', 'Herrera Rojas', 'jose.herrera@example.com', '987654325', 'Av. Primavera 654'),
('DNI', '56789012', 'Luis Alberto', 'Gutiérrez Salazar', 'luis.gutierrez@example.com', '987654326', 'Calle Bolívar 987'),
('DNI', '67890123', 'Gabriela Isabel', 'Ortega Paredes', 'gabriela.ortega@example.com', '987654327', 'Jr. Grau 741'),
('DNI', '78901234', 'Pedro Antonio', 'Vega Campos', 'pedro.vega@example.com', '987654328', 'Av. Universitaria 852'),
('DNI', '89012345', 'Elena Victoria', 'Morales Espinoza', 'elena.morales@example.com', '987654329', 'Jr. Larco 159'),
('DNI', '90123456', 'Ricardo Esteban', 'Suárez Núñez', 'ricardo.suarez@example.com', '987654330', 'Calle Independencia 753'),
('DNI', '11223344', 'Carmen Teresa', 'Díaz Guzmán', 'carmen.diaz@example.com', '987654331', 'Av. Tacna 951'),
('DNI', '22334455', 'Andrés Felipe', 'Rojas Cáceres', 'andres.rojas@example.com', '987654332', 'Jr. Lima 246'),
('DNI', '33445566', 'Valeria Antonia', 'Fernández León', 'valeria.fernandez@example.com', '987654333', 'Calle Callao 135'),
('DNI', '44556677', 'Miguel Ángel', 'Ponce Vera', 'miguel.ponce@example.com', '987654334', 'Pasaje San Pedro 864'),
('DNI', '55667788', 'Daniel Alejandro', 'Castro Montoya', 'daniel.castro@example.com', '987654335', 'Av. Arequipa 753'),
('DNI', '66778899', 'Paula Andrea', 'Sánchez Rivas', 'paula.sanchez@example.com', '987654336', 'Jr. San Borja 357'),
('DNI', '77889900', 'Esteban Ignacio', 'Flores Aguirre', 'esteban.flores@example.com', '987654337', 'Calle Salaverry 951'),
('DNI', '88990011', 'Natalia Beatriz', 'Zambrano Huerta', 'natalia.zambrano@example.com', '987654338', 'Av. Colonial 456'),
('DNI', '99001122', 'Roberto Emiliano', 'Reyes Vásquez', 'roberto.reyes@example.com', '987654339', 'Jr. Miraflores 753'),
('DNI', '10111213', 'Fernanda Lucía', 'Chávez Ibarra', 'fernanda.chavez@example.com', '987654340', 'Calle Primavera 842'),
('DNI', '11121314', 'David Sebastián', 'Medina Pizarro', 'david.medina@example.com', '987654341', 'Av. Larco 369'),
('DNI', '12131415', 'Laura Eugenia', 'Gómez Arévalo', 'laura.gomez@example.com', '987654342', 'Jr. Grau 753'),
('DNI', '13141516', 'Alejandro Enrique', 'Martínez Salinas', 'alejandro.martinez@example.com', '987654343', 'Calle San Miguel 123'),
('DNI', '14151617', 'Marina Isabel', 'Arroyo Escobar', 'marina.arroyo@example.com', '987654344', 'Av. Pardo 951'),
('DNI', '15161718', 'Lucas Adrián', 'Herrera Benítez', 'lucas.herrera@example.com', '987654345', 'Jr. Tacna 654'),
('DNI', '16171819', 'Silvia Carolina', 'Espinoza Miranda', 'silvia.espinoza@example.com', '987654346', 'Calle San José 753'),
('DNI', '17181920', 'Francisco Javier', 'Vargas Rojas', 'francisco.vargas@example.com', '987654347', 'Av. Callao 159'),
('DNI', '18192021', 'Camila Alejandra', 'Núñez Carranza', 'camila.nunez@example.com', '987654348', 'Jr. Zárate 741'),
('DNI', '19202122', 'Raúl Eduardo', 'Paredes Quispe', 'raul.paredes@example.com', '987654349', 'Calle Primavera 357'),
('DNI', '20212223', 'Verónica Mariana', 'Ríos Guzmán', 'veronica.rios@example.com', '987654350', 'Pasaje San Martín 654'),
('DNI', '21222324', 'Santiago Alonso', 'Cornejo Valdez', 'santiago.cornejo@example.com', '987654351', 'Av. Independencia 135'),
('DNI', '22232425', 'Isabel Renata', 'Torres Guzmán', 'isabel.torres@example.com', '987654352', 'Calle Bolívar 951'),
('DNI', '23242526', 'José Antonio', 'Maldonado León', 'jose.maldonado@example.com', '987654353', 'Jr. Los Olivos 753'),
('DNI', '24252627', 'Claudia Beatriz', 'Serrano Márquez', 'claudia.serrano@example.com', '987654354', 'Av. San Borja 159'),
('DNI', '25262728', 'Pablo Daniel', 'Vega Jiménez', 'pablo.vega@example.com', '987654355', 'Calle San Pedro 246'),
('DNI', '26272829', 'Lucía Valentina', 'Ramírez Castañeda', 'lucia.ramirez@example.com', '987654356', 'Jr. Arequipa 369'),
('DNI', '27282930', 'Fernando Ignacio', 'Mendoza Rivas', 'fernando.mendoza@example.com', '987654357', 'Av. Miraflores 753'),
('DNI', '28293031', 'Andrea Sofía', 'Ortega Pizarro', 'andrea.ortega@example.com', '987654358', 'Calle Larco 951');

select * from person

CREATE TABLE medicalhistory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    person_id INT NOT NULL,
    diagnosis VARCHAR(255) NOT NULL,
    consultation_date DATE NOT NULL,
    observations TEXT,
    FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
);

INSERT INTO medicalhistory (person_id, diagnosis, consultation_date, observations)
VALUES
(1, 'Caries dental', '2024-01-10', 'Se realizó limpieza y empaste en molar superior derecho.'),
(2, 'Gingivitis', '2024-01-15', 'Se recomendó uso de enjuague bucal con clorhexidina.'),
(3, 'Maloclusión dental', '2024-02-01', 'Paciente evaluado para ortodoncia con brackets.'),
(4, 'Pérdida dental', '2023-12-20', 'Se planificó colocación de implante en premolar.'),
(5, 'Bruxismo', '2024-01-05', 'Paciente con desgaste dental severo, se recomendó férula de descarga.'),
(6, 'Diente fracturado', '2024-01-22', 'Fractura en incisivo superior restaurada con resina.'),
(7, 'Periodontitis', '2023-11-30', 'Se realizó curetaje y alisado radicular.'),
(8, 'Absceso dental', '2024-01-08', 'Drenaje de absceso, se recetó antibiótico.'),
(9, 'Halitosis', '2023-12-10', 'Paciente con halitosis persistente, se indicó limpieza profunda.'),
(10, 'Manchas dentales', '2024-01-18', 'Paciente solicita blanqueamiento dental.'),
(11, 'Diente impactado', '2024-02-02', 'Extracción de cordal inferior derecho con cirugía.'),
(12, 'Sensibilidad dental', '2023-12-25', 'Se aplicó barniz de flúor en piezas dentales sensibles.'),
(13, 'Encía inflamada', '2023-11-15', 'Se diagnosticó gingivitis leve, se recomendó mejor higiene oral.'),
(14, 'Caries profunda', '2023-12-02', 'Tratamiento de conducto realizado en premolar superior.'),
(15, 'Dientes torcidos', '2023-10-20', 'Paciente evaluado para ortodoncia con frenillos metálicos.'),
(16, 'Absceso periodontal', '2024-02-03', 'Dolor e inflamación en encía, se realizó drenaje.'),
(17, 'Caries interproximal', '2023-10-15', 'Caries entre premolares inferiores, restaurada con resina.'),
(18, 'Inflamación en encías', '2023-11-20', 'Se realizó curetaje y se indicó uso de hilo dental.'),
(19, 'Fractura de raíz dental', '2024-01-10', 'Se planificó extracción y futura colocación de implante.'),
(20, 'Dolor de muela', '2023-12-22', 'Caries avanzada, se realizó extracción del molar.'),
(21, 'Diente flojo', '2024-02-01', 'Movilidad dental tratada con férula de estabilización.'),
(22, 'Diente astillado', '2023-12-05', 'Fractura parcial en diente tratado con resina.'),
(23, 'Pericoronitis', '2024-01-25', 'Inflamación severa, se realizó extracción de muela del juicio.'),
(24, 'Mordida cruzada', '2023-10-07', 'Paciente inicia tratamiento ortodóncico.'),
(25, 'Recesión gingival', '2023-09-25', 'Corrección estética con injerto de encía.'),
(26, 'Dientes manchados', '2023-11-18', 'Paciente con tinción por café y tabaco, se realizó blanqueamiento.'),
(27, 'Diente perdido', '2024-01-30', 'Se planificó colocación de implante unitario.'),
(28, 'Dolor mandibular', '2023-12-05', 'Paciente con probable disfunción temporomandibular.'),
(29, 'Flemón dental', '2024-01-28', 'Se realizó drenaje de absceso y se prescribió antibiótico.'),
(30, 'Encía sangrante', '2023-10-05', 'Paciente con gingivitis moderada, se realizó limpieza profunda.'),
(31, 'Diente astillado', '2023-09-10', 'Corrección con composite en diente fracturado.'),
(32, 'Diente supernumerario', '2024-02-01', 'Extracción de diente adicional en maxilar superior.'),
(33, 'Fractura de incisivo', '2024-02-04', 'Fractura parcial restaurada con resina compuesta.'),
(34, 'Mordida abierta', '2024-01-12', 'Evaluación para iniciar ortodoncia.'),
(35, 'Diente impactado', '2023-12-02', 'Paciente con canino retenido, se planifica extracción quirúrgica.'),
(36, 'Fístula dental', '2023-12-10', 'Presencia de fístula en premolar superior, se realizó tratamiento de conducto.'),
(37, 'Dientes desgastados', '2023-09-28', 'Paciente con bruxismo severo, se colocaron carillas de porcelana.'),
(38, 'Paladar hendido', '2024-01-03', 'Paciente en consulta para cirugía correctiva.'),
(1, 'Pérdida dental múltiple', '2023-11-10', 'Se planificó prótesis removible para restauración.'),
(2, 'Dientes separados', '2023-12-18', 'Cierre de espacios con resina estética.'),
(3, 'Absceso periapical', '2024-01-15', 'Dolor intenso, se realizó endodoncia en premolar.'),
(4, 'Diente torcido', '2023-09-15', 'Paciente optó por tratamiento con alineadores Invisalign.'),
(5, 'Infección dental', '2024-01-20', 'Extracción de molar con infección severa.'),
(6, 'Caries recurrente', '2023-11-22', 'Empaste previo filtrado, se realizó nueva restauración.'),
(7, 'Manchas blancas en esmalte', '2023-11-25', 'Corrección estética con microabrasión dental.'),
(8, 'Dolor en muela del juicio', '2024-02-02', 'Extracción quirúrgica por inflamación recurrente.'),
(9, 'Maloclusión', '2023-12-15', 'Paciente inicia tratamiento con brackets metálicos.'),
(10, 'Dientes sensibles', '2024-01-07', 'Aplicación de flúor para reducción de sensibilidad.'),
(11, 'Caries en molar', '2023-12-08', 'Restauración con resina fotopolimerizable.'),
(12, 'Diente fracturado', '2024-02-05', 'Paciente sufrió golpe en el incisivo, se restauró con resina.');
GO

INSERT INTO medicalhistory (person_id, diagnosis, consultation_date, observations)
VALUES
(1, 'Caries en premolar', '2024-01-11', 'Se realizó empaste con resina en premolar superior izquierdo.'),
(2, 'Gingivitis leve', '2024-01-20', 'Recomendación de cepillado adecuado y uso de hilo dental.'),
(3, 'Maloclusión clase II', '2024-02-05', 'Paciente evaluado para ortodoncia interceptiva.'),
(4, 'Extracción de molar', '2023-12-22', 'Extracción por caries avanzada, se planifica implante.'),
(5, 'Bruxismo severo', '2024-01-09', 'Se confeccionó férula de descarga nocturna.'),
(6, 'Diente astillado', '2024-01-30', 'Se realizó restauración con resina compuesta.'),
(7, 'Periodontitis moderada', '2023-11-28', 'Curetaje y control periodontal programado.'),
(8, 'Absceso periapical', '2024-01-07', 'Tratamiento de conducto realizado en molar superior.'),
(9, 'Halitosis crónica', '2023-12-15', 'Limpieza profunda y asesoramiento en higiene oral.'),
(10, 'Manchas de tetraciclina', '2024-01-12', 'Paciente considera blanqueamiento dental.'),
(11, 'Diente retenido', '2024-02-03', 'Evaluación para cirugía de extracción de canino superior.'),
(12, 'Hipoplasia del esmalte', '2023-12-18', 'Corrección estética con carillas dentales.'),
(13, 'Encía sangrante', '2023-11-10', 'Se realizó limpieza profunda y aplicación de flúor.'),
(14, 'Caries radicular', '2023-12-04', 'Endodoncia en premolar inferior.'),
(15, 'Dientes apiñados', '2023-10-30', 'Se sugiere inicio de ortodoncia con alineadores.'),
(16, 'Infección periapical', '2024-02-02', 'Se drenó absceso y se prescribió antibiótico.'),
(17, 'Caries proximal', '2023-10-10', 'Restauración con resina compuesta en incisivo.'),
(18, 'Inflamación gingival', '2023-11-25', 'Se recomendó mejor técnica de cepillado y enjuague bucal.'),
(19, 'Fractura coronal', '2024-01-06', 'Reparación con resina en diente anterior.'),
(20, 'Dolor intenso en muela', '2023-12-20', 'Diagnóstico de pulpitis irreversible, se realizó endodoncia.'),
(21, 'Movilidad dental', '2024-01-31', 'Colocación de férula de estabilización.'),
(22, 'Hipersensibilidad dental', '2023-12-10', 'Aplicación de sellador y barniz de flúor.'),
(23, 'Pericoronitis leve', '2024-01-29', 'Se indicó control de higiene en muela del juicio.'),
(24, 'Desgaste por bruxismo', '2023-10-15', 'Paciente evaluado para carillas de porcelana.'),
(25, 'Recesión gingival', '2023-09-20', 'Corrección estética mediante injerto de encía.'),
(26, 'Dientes pigmentados', '2023-11-08', 'Blanqueamiento dental realizado con peróxido de hidrógeno.'),
(27, 'Diente ausente', '2024-01-25', 'Se planifica colocación de implante unitario.'),
(28, 'Dolor en la articulación temporomandibular', '2023-12-04', 'Se indicó fisioterapia y relajación mandibular.'),
(29, 'Infección en encía', '2024-01-15', 'Limpieza profunda y antibióticos indicados.'),
(30, 'Sangrado al cepillarse', '2023-10-08', 'Se diagnosticó gingivitis y se realizó profilaxis.'),
(31, 'Diente fisurado', '2023-09-05', 'Corrección con resina compuesta.'),
(32, 'Diente adicional', '2024-02-01', 'Extracción de supernumerario en maxilar superior.'),
(33, 'Fractura en premolar', '2024-02-06', 'Se realizó restauración estética.'),
(34, 'Mordida profunda', '2024-01-11', 'Evaluación para tratamiento ortodóncico.'),
(35, 'Muela impactada', '2023-12-01', 'Planificación para extracción quirúrgica.'),
(36, 'Fístula periapical', '2023-12-18', 'Endodoncia en incisivo central superior.'),
(37, 'Bruxismo moderado', '2023-09-30', 'Uso de férula de descarga nocturna recomendado.'),
(38, 'Frenillo corto', '2024-01-02', 'Se realizó frenectomía para mejorar movilidad lingual.'),
(1, 'Dientes perdidos', '2023-11-05', 'Planificación de prótesis removible.'),
(2, 'Diastema dental', '2023-12-10', 'Cierre de espacio con resina estética.'),
(3, 'Absceso gingival', '2024-01-16', 'Drenaje realizado, paciente en observación.'),
(4, 'Caries en incisivo', '2023-11-20', 'Restauración con resina fotopolimerizable.'),
(5, 'Manchas blancas en esmalte', '2023-11-30', 'Corrección estética con microabrasión.'),
(6, 'Dolor en muela del juicio', '2024-02-03', 'Extracción planificada en próximas semanas.'),
(7, 'Apiñamiento dental', '2023-12-12', 'Paciente evaluado para tratamiento con ortodoncia.'),
(8, 'Sensibilidad al frío', '2024-01-05', 'Aplicación de barniz de flúor para reducción de sensibilidad.'),
(9, 'Caries en premolar', '2023-12-07', 'Empaste realizado con resina compuesta.'),
(10, 'Fractura en incisivo', '2024-02-04', 'Paciente sufrió traumatismo, se realizó restauración.');



select * from medicalhistory;




