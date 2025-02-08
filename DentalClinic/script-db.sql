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
drop table Patient
drop table Doctor
drop table MedicalRecord

CREATE TABLE patient (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
	documenttype VARCHAR(5) NOT NULL,
	documentnumber VARCHAR(20) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    birth_date DATE,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE doctor (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    specialty VARCHAR(50),
    phone VARCHAR(15),
    email VARCHAR(100) UNIQUE
);

CREATE TABLE madical_record (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    consultation_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    diagnosis TEXT,
    treatment TEXT,
    FOREIGN KEY (patient_id) REFERENCES patient(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctor(doctor_id) ON DELETE CASCADE
);

select * from MedicalRecord


INSERT INTO patient (documenttype, documentnumber, first_name, last_name, phone, email, birth_date)
VALUES 
('DNI', '12345678', 'Carlos', 'Fernandez Lopez', '987654321', 'carlos.fernandez@example.com', '1985-07-21'),
('DNI', '23456789', 'María', 'Gonzalez Perez', '987654322', 'maria.gonzalez@example.com', '1990-05-15'),
('DNI', '34567890', 'Luis', 'Rodriguez Diaz', '987654323', 'luis.rodriguez@example.com', '1988-10-10'),
('DNI', '45678901', 'Ana', 'Martinez Torres', '987654324', 'ana.martinez@example.com', '1992-03-08'),
('DNI', '56789012', 'Pedro', 'Hernandez Vargas', '987654325', 'pedro.hernandez@example.com', '1983-09-25'),
('DNI', '67890123', 'Sofia', 'Ramirez Chavez', '987654326', 'sofia.ramirez@example.com', '1995-06-12'),
('DNI', '78901234', 'Jorge', 'Lopez Castro', '987654327', 'jorge.lopez@example.com', '1980-12-30'),
('DNI', '89012345', 'Paula', 'Diaz Morales', '987654328', 'paula.diaz@example.com', '1993-07-19'),
('DNI', '90123456', 'Diego', 'Medina Rojas', '987654329', 'diego.medina@example.com', '1987-01-22'),
('DNI', '10234567', 'Elena', 'Paredes Nuñez', '987654330', 'elena.paredes@example.com', '1998-04-05'),
('DNI', '11234568', 'Raúl', 'Ortega Jimenez', '987654331', 'raul.ortega@example.com', '1979-08-14'),
('DNI', '12234569', 'Clara', 'Sanchez Guzman', '987654332', 'clara.sanchez@example.com', '1996-11-30'),
('DNI', '13234570', 'Fernando', 'Rios Salazar', '987654333', 'fernando.rios@example.com', '1984-05-09'),
('DNI', '14234571', 'Lorena', 'Vega Cordero', '987654334', 'lorena.vega@example.com', '1991-02-25'),
('DNI', '15234572', 'Oscar', 'Navarro Soto', '987654335', 'oscar.navarro@example.com', '1986-09-17'),
('DNI', '16234573', 'Valeria', 'Aguilar Mendez', '987654336', 'valeria.aguilar@example.com', '1994-12-03'),
('DNI', '17234574', 'Gabriel', 'Mendoza Flores', '987654337', 'gabriel.mendoza@example.com', '1981-06-20'),
('DNI', '18234575', 'Andrea', 'Fuentes Carrillo', '987654338', 'andrea.fuentes@example.com', '1997-03-11'),
('DNI', '19234576', 'Hugo', 'Reyes Peña', '987654339', 'hugo.reyes@example.com', '1989-07-28'),
('DNI', '20234577', 'Diana', 'Campos Romero', '987654340', 'diana.campos@example.com', '1992-10-15'),
('DNI', '21234578', 'Manuel', 'Gutierrez Silva', '987654341', 'manuel.gutierrez@example.com', '1983-11-05'),
('DNI', '22234579', 'Patricia', 'Castro Ruiz', '987654342', 'patricia.castro@example.com', '1995-01-30'),
('DNI', '23234580', 'Alberto', 'Herrera Jimenez', '987654343', 'alberto.herrera@example.com', '1987-04-18'),
('DNI', '24234581', 'Camila', 'Ortega Ramirez', '987654344', 'camila.ortega@example.com', '1990-09-09'),
('DNI', '25234582', 'Julian', 'Salas Rivas', '987654345', 'julian.salas@example.com', '1982-08-22'),
('DNI', '26234583', 'Natalia', 'Guerrero Paredes', '987654346', 'natalia.guerrero@example.com', '1993-06-14'),
('DNI', '27234584', 'Sebastian', 'Morales Leon', '987654347', 'sebastian.morales@example.com', '1985-12-06'),
('DNI', '28234585', 'Lucia', 'Vargas Castro', '987654348', 'lucia.vargas@example.com', '1996-02-28'),
('DNI', '29234586', 'Enrique', 'Delgado Nuñez', '987654349', 'enrique.delgado@example.com', '1989-05-25'),
('DNI', '30234587', 'Isabela', 'Rojas Salazar', '987654350', 'isabela.rojas@example.com', '1997-10-13'),
('DNI', '31234588', 'Martin', 'Molina Cordero', '987654351', 'martin.molina@example.com', '1981-07-01'),
('DNI', '32234589', 'Renata', 'Silva Guzman', '987654352', 'renata.silva@example.com', '1992-11-29'),
('DNI', '33234590', 'Esteban', 'Ramirez Carrillo', '987654353', 'esteban.ramirez@example.com', '1984-03-16'),
('DNI', '34234591', 'Carolina', 'Fernandez Lopez', '987654354', 'carolina.fernandez@example.com', '1995-12-12'),
('DNI', '35234592', 'Daniel', 'Mendez Ruiz', '987654355', 'daniel.mendez@example.com', '1986-09-03'),
('DNI', '36234593', 'Ximena', 'Perez Jimenez', '987654356', 'ximena.perez@example.com', '1990-04-20'),
('DNI', '37234594', 'Pablo', 'Santos Flores', '987654357', 'pablo.santos@example.com', '1983-07-07'),
('DNI', '38234595', 'Angela', 'Cruz Vargas', '987654358', 'angela.cruz@example.com', '1994-05-14'),
('DNI', '39234596', 'Francisco', 'Ortega Peña', '987654359', 'francisco.ortega@example.com', '1988-02-27'),
('DNI', '40234597', 'Monica', 'Reyes Romero', '987654360', 'monica.reyes@example.com', '1991-08-19'),
('DNI', '41234598', 'Ricardo', 'Herrera Silva', '987654361', 'ricardo.herrera@example.com', '1985-06-08'),
('DNI', '42234599', 'Alejandra', 'Diaz Salazar', '987654362', 'alejandra.diaz@example.com', '1993-09-04'),
('DNI', '43234600', 'Federico', 'Molina Guzman', '987654363', 'federico.molina@example.com', '1987-01-11'),
('DNI', '44234601', 'Gabriela', 'Vega Cordero', '987654364', 'gabriela.vega@example.com', '1996-03-22'),
('DNI', '45234602', 'Emiliano', 'Navarro Rivas', '987654365', 'emiliano.navarro@example.com', '1982-10-29'),
('DNI', '46234603', 'Victoria', 'Aguilar Mendez', '987654366', 'victoria.aguilar@example.com', '1994-07-17');


INSERT INTO doctor (first_name, last_name, specialty, phone, email) 
VALUES 
('Alejandro', 'Gomez Ruiz', 'Odontología General', '987650001', 'alejandro.gomez@example.com'),
('Beatriz', 'Fernandez Lopez', 'Ortodoncia', '987650002', 'beatriz.fernandez@example.com'),
('Carlos', 'Ramirez Diaz', 'Endodoncia', '987650003', 'carlos.ramirez@example.com'),
('Diana', 'Sanchez Torres', 'Periodoncia', '987650004', 'diana.sanchez@example.com'),
('Eduardo', 'Vargas Morales', 'Cirugía Oral', '987650005', 'eduardo.vargas@example.com'),
('Fernanda', 'Hernandez Castro', 'Implantología', '987650006', 'fernanda.hernandez@example.com'),
('Gonzalo', 'Ortega Nuñez', 'Odontopediatría', '987650007', 'gonzalo.ortega@example.com'),
('Hilda', 'Navarro Salazar', 'Rehabilitación Oral', '987650008', 'hilda.navarro@example.com'),
('Ismael', 'Paredes Guzman', 'Radiología Dental', '987650009', 'ismael.paredes@example.com'),
('Juliana', 'Delgado Rivas', 'Estética Dental', '987650010', 'juliana.delgado@example.com');

INSERT INTO medical_record (patient_id, doctor_id, consultation_date, diagnosis, treatment) 
VALUES 
(1, 2, '2024-02-01 10:30:00', 'Caries dental en molar inferior izquierdo', 'Obturación con resina compuesta'),
(2, 4, '2024-02-02 14:00:00', 'Gingivitis moderada', 'Limpieza dental y enjuague con clorhexidina'),
(3, 1, '2024-02-03 09:15:00', 'Maloclusión clase II', 'Tratamiento ortodóncico con brackets metálicos'),
(4, 6, '2024-02-04 11:00:00', 'Fractura de premolar superior derecho', 'Colocación de corona de porcelana'),
(5, 3, '2024-02-05 16:30:00', 'Necrosis pulpar en incisivo central superior', 'Tratamiento de conducto'),
(6, 5, '2024-02-06 10:00:00', 'Extracción de tercer molar impactado', 'Cirugía de exodoncia con sutura'),
(7, 9, '2024-02-07 12:00:00', 'Pérdida de piezas dentales', 'Colocación de prótesis removible'),
(8, 8, '2024-02-08 15:00:00', 'Manchas dentales por tetraciclina', 'Blanqueamiento dental con peróxido de hidrógeno'),
(9, 7, '2024-02-09 17:45:00', 'Lesión periapical crónica', 'Apicectomía y obturación retrógrada'),
(10, 10, '2024-02-10 08:30:00', 'Bruxismo severo', 'Placa de relajación nocturna'),
(11, 1, '2024-02-11 09:00:00', 'Sensibilidad dental generalizada', 'Aplicación de flúor y cambio de pasta dental'),
(12, 3, '2024-02-12 14:45:00', 'Diastema entre incisivos centrales', 'Cierre de espacios con carillas de resina'),
(13, 4, '2024-02-13 10:20:00', 'Absceso dental en molar inferior derecho', 'Drenaje y antibióticos'),
(14, 2, '2024-02-14 12:10:00', 'Hipoplasia del esmalte', 'Aplicación de resina compuesta para restauración'),
(15, 5, '2024-02-15 11:40:00', 'Movilidad dental grado II', 'Ferulización con fibra de vidrio'),
(16, 6, '2024-02-16 15:50:00', 'Halitosis persistente', 'Educación en higiene oral y uso de raspador lingual'),
(17, 9, '2024-02-17 09:30:00', 'Inflamación gingival por ortodoncia', 'Control de higiene y ajustes en brackets'),
(18, 8, '2024-02-18 13:20:00', 'Dolor en articulación temporomandibular', 'Terapia de relajación mandibular'),
(19, 10, '2024-02-19 16:00:00', 'Pulpitis irreversible', 'Endodoncia en premolar superior'),
(20, 7, '2024-02-20 08:10:00', 'Retracción gingival leve', 'Control de cepillado y uso de crema desensibilizante'),
(21, 1, '2024-02-21 09:45:00', 'Muelas del juicio retenidas', 'Evaluación para extracción quirúrgica'),
(22, 2, '2024-02-22 14:10:00', 'Cambio de amalgama en premolar', 'Restauración con resina estética'),
(23, 3, '2024-02-23 10:30:00', 'Fractura radicular en molar', 'Extracción y planificación de implante'),
(24, 4, '2024-02-24 11:20:00', 'Erosión dental por reflujo gástrico', 'Protección con resina fluida y control médico'),
(25, 5, '2024-02-25 15:15:00', 'Mucositis oral leve', 'Enjuague con clorhexidina al 0.12%'),
(26, 6, '2024-02-26 12:30:00', 'Hipomineralización del esmalte', 'Aplicación de sellantes y flúor'),
(27, 7, '2024-02-27 08:50:00', 'Quiste dentígero en maxilar superior', 'Cirugía de extirpación y biopsia'),
(28, 8, '2024-02-28 14:40:00', 'Candidiasis oral', 'Tratamiento con antifúngicos tópicos'),
(29, 9, '2024-02-29 10:10:00', 'Caries recurrente en premolar tratado', 'Reemplazo de restauración con resina'),
(30, 10, '2024-03-01 16:20:00', 'Pérdida de hueso alveolar', 'Injerto óseo previo a implante dental'),
(31, 1, '2024-03-02 11:00:00', 'Aftas recurrentes en mucosa bucal', 'Tratamiento con corticoides tópicos'),
(32, 2, '2024-03-03 09:25:00', 'Desgaste oclusal severo', 'Reconstrucción con coronas de cerámica'),
(33, 3, '2024-03-04 13:50:00', 'Inflamación posoperatoria', 'Control y manejo de analgesia'),
(34, 4, '2024-03-05 10:40:00', 'Hipersensibilidad dentinaria', 'Barniz de flúor y sellado de túbulos dentinarios'),
(35, 5, '2024-03-06 15:10:00', 'Dientes supernumerarios', 'Evaluación para extracción quirúrgica'),
(36, 6, '2024-03-07 12:50:00', 'Traumatismo dentoalveolar', 'Reubicación de diente y ferulización'),
(37, 7, '2024-03-08 08:30:00', 'Pérdida de inserción periodontal', 'Tratamiento con injerto gingival'),
(38, 8, '2024-03-09 14:20:00', 'Desmineralización del esmalte', 'Aplicación de flúor y revisión cada 3 meses'),
(39, 9, '2024-03-10 09:55:00', 'Lesión de tejido blando en mejilla', 'Biopsia para diagnóstico diferencial'),
(40, 10, '2024-03-11 16:45:00', 'Necrosis ósea por bisfosfonatos', 'Evaluación con especialista en cirugía oral'),
(41, 1, '2024-03-12 10:15:00', 'Mordida cruzada anterior', 'Intervención ortodóncica'),
(42, 2, '2024-03-13 12:40:00', 'Cálculo dental severo', 'Limpieza profunda con ultrasonido'),
(43, 3, '2024-03-14 09:30:00', 'Cierre incompleto de frenillo lingual', 'Frenectomía con láser'),
(44, 4, '2024-03-15 15:50:00', 'Hipoplasia en dientes temporales', 'Aplicación de sellantes preventivos'),
(45, 5, '2024-03-16 11:20:00', 'Leucoplasia en lengua', 'Seguimiento y biopsia en caso de persistencia'),
(46, 6, '2024-03-17 14:35:00', 'Caries en molares primarios', 'Tratamiento con coronas pediátricas'),
(1, 7, '2024-03-18 10:50:00', 'Pericoronaritis en tercer molar', 'Drenaje y manejo de inflamación'),
(3, 8, '2024-03-19 12:10:00', 'Desgaste dental por bruxismo', 'Colocación de férula oclusal'),
(10, 9, '2024-03-20 09:40:00', 'Hipoplasia de esmalte', 'Recubrimiento con resina fluida'),
(20, 10, '2024-03-21 16:30:00', 'Erosión dental severa', 'Restauración con resinas compuestas');

INSERT INTO medical_record (patient_id, doctor_id, consultation_date, diagnosis, treatment) 
VALUES 
(1, 5, '2024-03-22 10:30:00', 'Dolor en muela del juicio', 'Extracción quirúrgica del tercer molar'),
(2, 6, '2024-03-23 14:00:00', 'Caries profunda en molar inferior', 'Endodoncia y reconstrucción con resina'),
(3, 8, '2024-03-24 09:15:00', 'Sarro y placa dental', 'Limpieza profunda con ultrasonido'),
(4, 3, '2024-03-25 11:00:00', 'Pérdida ósea periodontal', 'Tratamiento periodontal con injerto óseo'),
(5, 2, '2024-03-26 16:30:00', 'Fractura en incisivo lateral', 'Colocación de carilla de porcelana'),
(6, 7, '2024-03-27 10:00:00', 'Mal aliento persistente', 'Limpieza y educación en higiene oral'),
(7, 10, '2024-03-28 12:00:00', 'Cierre incompleto de diastema', 'Colocación de resina estética'),
(8, 1, '2024-03-29 15:00:00', 'Diente retenido', 'Evaluación radiográfica para cirugía de extracción'),
(9, 4, '2024-03-30 17:45:00', 'Inflamación de encías', 'Tratamiento periodontal con raspado y alisado'),
(10, 9, '2024-03-31 08:30:00', 'Bruxismo leve', 'Placa de descarga para uso nocturno'),
(11, 5, '2024-04-01 09:00:00', 'Abrasión dental', 'Corrección con resina fluida y control de cepillado'),
(12, 6, '2024-04-02 14:45:00', 'Absceso periapical', 'Drenaje, endodoncia y antibióticos'),
(13, 3, '2024-04-03 10:20:00', 'Caries interproximal', 'Limpieza y restauración con resina compuesta'),
(14, 8, '2024-04-04 12:10:00', 'Dientes manchados por tetraciclina', 'Blanqueamiento dental con peróxido de hidrógeno'),
(15, 7, '2024-04-05 11:40:00', 'Desgaste dental por reflujo', 'Protección con resina y dieta controlada'),
(16, 10, '2024-04-06 15:50:00', 'Candidiasis oral', 'Tratamiento antifúngico con nistatina'),
(17, 2, '2024-04-07 09:30:00', 'Encía retraída', 'Cirugía de injerto gingival'),
(18, 1, '2024-04-08 13:20:00', 'Dolor en premolar superior derecho', 'Endodoncia y reconstrucción con resina'),
(19, 4, '2024-04-09 16:00:00', 'Caries rampante en niños', 'Aplicación de flúor y control de dieta'),
(20, 9, '2024-04-10 08:10:00', 'Mordida abierta', 'Tratamiento ortodóncico con brackets'),
(21, 5, '2024-04-11 09:45:00', 'Dientes amarillentos', 'Blanqueamiento con luz LED'),
(22, 6, '2024-04-12 14:10:00', 'Pericoronaritis en tercer molar', 'Drenaje y manejo del dolor'),
(23, 8, '2024-04-13 10:30:00', 'Raíz expuesta en molar superior', 'Recubrimiento con resina fluida'),
(24, 3, '2024-04-14 11:20:00', 'Aftas bucales recurrentes', 'Aplicación de corticoides tópicos'),
(25, 7, '2024-04-15 15:15:00', 'Dientes desgastados por bruxismo', 'Férula de descarga'),
(26, 10, '2024-04-16 12:30:00', 'Pérdida de hueso alveolar', 'Regeneración ósea con injerto'),
(27, 2, '2024-04-17 08:50:00', 'Halitosis crónica', 'Educación en higiene bucal y tratamiento periodontal'),
(28, 1, '2024-04-18 14:40:00', 'Frenillo lingual corto', 'Frenectomía con láser'),
(29, 4, '2024-04-19 10:10:00', 'Hipoplasia del esmalte', 'Protección con selladores dentales'),
(30, 9, '2024-04-20 16:20:00', 'Malposición dental', 'Evaluación para ortodoncia'),
(31, 5, '2024-04-21 11:00:00', 'Inflamación post-extracción', 'Antiinflamatorios y compresas frías'),
(32, 6, '2024-04-22 09:25:00', 'Cierre inadecuado de espacios en ortodoncia', 'Revisión y ajuste de brackets'),
(33, 3, '2024-04-23 13:50:00', 'Pérdida prematura de molares temporales', 'Mantenimiento del espacio con ortodoncia'),
(34, 8, '2024-04-24 10:40:00', 'Caries recurrente en premolar tratado', 'Reemplazo de restauración'),
(35, 7, '2024-04-25 15:10:00', 'Dientes impactados', 'Cirugía de extracción quirúrgica'),
(36, 10, '2024-04-26 12:50:00', 'Lesión periapical crónica', 'Evaluación radiográfica y tratamiento de conducto'),
(37, 2, '2024-04-27 08:30:00', 'Dientes oscuros por necrosis', 'Blanqueamiento interno'),
(38, 1, '2024-04-28 14:20:00', 'Dolor en articulación temporomandibular', 'Terapia con férula miorelajante'),
(39, 4, '2024-04-29 09:55:00', 'Placa bacteriana excesiva', 'Limpieza con ultrasonido y educación en cepillado'),
(40, 9, '2024-04-30 16:45:00', 'Sarro en encías', 'Detartraje y pulido dental'),
(41, 5, '2024-05-01 10:15:00', 'Hipermovilidad dental', 'Ferulización de dientes afectados'),
(42, 6, '2024-05-02 12:40:00', 'Exposición radicular', 'Aplicación de resinas protectoras'),
(43, 3, '2024-05-03 09:30:00', 'Cierre de diastema', 'Carillas estéticas en incisivos'),
(44, 8, '2024-05-04 15:50:00', 'Pérdida de esmalte en premolares', 'Aplicación de flúor y selladores'),
(45, 7, '2024-05-05 11:20:00', 'Quiste dentígero', 'Evaluación y cirugía de extracción'),
(46, 10, '2024-05-06 14:35:00', 'Caries en niños', 'Fluorización y educación en dieta saludable'),
(34, 2, '2024-05-07 10:50:00', 'Dolor persistente en muela tratada', 'Revisión de endodoncia previa'),
(33, 1, '2024-05-08 12:10:00', 'Retención de dientes primarios', 'Evaluación para extracción'),
(32, 4, '2024-05-09 09:40:00', 'Dientes impactados', 'Extracción quirúrgica de terceros molares'),
(1, 9, '2024-05-10 16:30:00', 'Infección periapical', 'Endodoncia y antibióticos');

select * from MedicalRecord