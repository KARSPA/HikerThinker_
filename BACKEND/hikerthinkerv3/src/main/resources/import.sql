--Insertion des roles et authorité associée.
INSERT INTO roles(role_id,authority) VALUES (1, 'ADMIN');
INSERT INTO roles(role_id,authority) VALUES (2, 'USER');
SELECT setval('roles_seq',(SELECT GREATEST(MAX(role_id), nextval('roles_seq')-1) FROM roles));


--Insertion des catégories
INSERT INTO categories(category_id, name) VALUES (1, 'Aucune');
INSERT INTO categories(category_id, name) VALUES (2, 'Portage');
INSERT INTO categories(category_id, name) VALUES (3, 'Santé');
INSERT INTO categories(category_id, name) VALUES (4, 'Eau');
INSERT INTO categories(category_id, name) VALUES (5, 'Hygiène');
INSERT INTO categories(category_id, name) VALUES (6, 'Habillage');
INSERT INTO categories(category_id, name) VALUES (7, 'Accessoires');
INSERT INTO categories(category_id, name) VALUES (8, 'Électronique');
INSERT INTO categories(category_id, name) VALUES (9, 'Divers');
INSERT INTO categories(category_id, name) VALUES (10, 'Couchage');
SELECT setval('categories_category_id_seq',(SELECT GREATEST(MAX(category_id), nextval('categories_category_id_seq')-1) FROM "categories"));


--Insérer des marques (liste à raffiner plus tard)
INSERT INTO brands(brand_id, name) VALUES (1, 'AUCUNE');
INSERT INTO brands(brand_id, name) VALUES (2, 'FORCLAZ');
INSERT INTO brands(brand_id, name) VALUES (3, 'ZPACKS');
INSERT INTO brands(brand_id, name) VALUES (4, 'LANSHAN');
INSERT INTO brands(brand_id, name) VALUES (5, 'OKO');
INSERT INTO brands(brand_id, name) VALUES (6, 'SIMOND');
INSERT INTO brands(brand_id, name) VALUES (7, 'ICEBREAKER');
INSERT INTO brands(brand_id, name) VALUES (8, 'ARCTERYX');
INSERT INTO brands(brand_id, name) VALUES (9, 'GARMIN');
INSERT INTO brands(brand_id, name) VALUES (10, 'QUECHUA');
INSERT INTO brands(brand_id, name) VALUES (11, 'OSPREY');
INSERT INTO brands(brand_id, name) VALUES (12, 'MSR');
INSERT INTO brands(brand_id, name) VALUES (13, 'THERM-A-REST');
INSERT INTO brands(brand_id, name) VALUES (14, 'NEMO');
INSERT INTO brands(brand_id, name) VALUES (15, 'COLUMBIA');
SELECT setval('brands_brand_id_seq',(SELECT GREATEST(MAX(brand_id), nextval('brands_brand_id_seq')-1) FROM "brands"));


--Insertion des users.
INSERT INTO "users"(user_id, email, password, username) VALUES (1,'admin@oui.com' ,'$2y$10$.xyXnwT4F7pbhA17YodaEOtjt1Dw3FN.zam2wpW09Q6CxhxgRepT6','admin'),(2, 'michel@test.com','$2y$10$9yiIjLM.0sXwvQ3.i90bwudFtplRX5qufsfsGXbf5Z2nvBLOs7/.2','michelBG'),(3, 'gilbert@gmail.fr', '$2y$10$9yiIjLM.0sXwvQ3.i90bwudFtplRX5qufsfsGXbf5Z2nvBLOs7/.2', 'gilbertO');
SELECT setval('users_user_id_seq',(SELECT GREATEST(MAX(user_id), nextval('users_user_id_seq')-1) FROM "users"));

--Roles associés aux users.
INSERT INTO user_role_junction(user_id, role_id) VALUES (1, 1),(1, 2),(2, 2),(3, 2);

--Insertion de stats pour chaque user. 
INSERT INTO user_statistics(user_id) VALUES (1),(2),(3);
INSERT INTO inventories(user_id) VALUES (1),(2),(3);



--Insérer une liste d'équipements
INSERT INTO equipments(equipment_id, category_id, name, description, weight, brand_id) VALUES (1, 1, 'Matériel générique', 'Votre description...', 0, 1);
INSERT INTO equipments(equipment_id, category_id, name, description, weight, brand_id) VALUES (2, 10, 'Tente Dôme MT900 1-place', 'Résistante et tout ...', 1300, 2);
INSERT INTO equipments(equipment_id, category_id, name, description, weight, brand_id) VALUES (3, 10, 'Sac de couchage MT500 0°C Synthétique', 'Super confort et chaud', 1520, 2);
INSERT INTO equipments(equipment_id, category_id, name, description, weight, brand_id) VALUES (4, 10, 'Matelas gonflable MT500 1-personne Air Isolant', 'Isolation R-value 3.3', 820, 2);
INSERT INTO equipments(equipment_id, category_id, name, description, weight, brand_id) VALUES (5, 10, 'Tente Plex Solo', 'Se monte avec un bâton', 413, 3);
INSERT INTO equipments(equipment_id, category_id, name, description, weight, brand_id) VALUES (6, 10, '2 Pro (2 places)', 'Se monte avec deux bâton', 915, 4);
INSERT INTO equipments(equipment_id, category_id, name, description, weight, brand_id) VALUES (7, 4, 'Gourde ÖKO 500mL', 'Filtres tout - 400L par filtre', 135, 5);
INSERT INTO equipments(equipment_id, category_id, name, description, weight, brand_id) VALUES (8, 4, 'Gourde ÖKO 650mL', 'Filtres tout - 400L par filtre', 147, 5);
INSERT INTO equipments(equipment_id, category_id, name, description, weight, brand_id) VALUES (9, 4, 'Gourde ÖKO 1000mL', 'Filtres tout - 400L par filtre', 172, 5);
INSERT INTO equipments(equipment_id, category_id, name, description, weight, brand_id) VALUES (10, 6, 'Doudoune duvert Alpinism', 'Taille M - Confort jusqu''à -12°C.', 495, 6);
INSERT INTO equipments(equipment_id, category_id, name, description, weight, brand_id) VALUES (11, 2, 'Sac à Dos ACE 50', 'Contenance 50L', 1680, 11);
INSERT INTO equipments(equipment_id, category_id, name, description, weight, brand_id) VALUES (12, 2, 'Sac à Dos Talon 33', 'Contenance 33L', 1070, 11);
INSERT INTO equipments(equipment_id, category_id, name, description, weight, brand_id) VALUES (13, 3, 'Couverture de survie', 'Réutilisable', 170, 1);
INSERT INTO equipments(equipment_id, category_id, name, description, weight, brand_id) VALUES (14, 10, 'Tente Tarp MT900 1-place', 'Blabla super produit', 920, 2);
SELECT setval('equipments_equipment_id_seq',(SELECT GREATEST(MAX(equipment_id), nextval('equipments_equipment_id_seq')-1) FROM "equipments"));



--Affectation des équipements à certains users
INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (1, 1, 14,10,2, 'Tente Tarp MT900 1-place', 'Blabla super produit', 920);
INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (2, 1, 3,10,2, 'Sac de couchage MT500 0°C Synthétique', 'Super confort et chaud', 1520);
INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (3, 1, 4,10,2, 'Matelas gonflable MT500 1-personne Air Isolant', 'Isolation R-value 3.3', 820);
INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (4, 1, 8,4,5, 'Gourde ÖKO 650mL', 'Filtres tout - 400L par filtre', 147);
INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (5, 1, 12,2,11, 'Sac à Dos Talon 33', 'Contenance 33L', 1070);
INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (6, 1, 13,3,1, 'Couverture de survie', 'Réutilisable', 170);

INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (7, 2, 5,10,3, 'Tente Plex Solo', 'Se monte avec un bâton', 413);
INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (8, 2, 10,6,6, 'Doudoune duvert Alpinism', 'Taille M - Confort jusqu''à -12°C.', 495);
INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (9, 2, 4,10,2, 'Matelas gonflable MT500 1-personne Air Isolant', 'Isolation R-value 3.3', 820);
INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (10, 2, 9,4,5, 'Gourde ÖKO 1000mL', 'Filtres tout - 400L par filtre', 172);
INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (11, 2, 11,2,11, 'Sac à Dos ACE 50', 'Contenance 50L', 1680);

INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (12, 3, 2,10,2, 'Tente Dôme MT900 1-place', 'Résistante et tout ...', 1300);
INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (13, 3, 3,10,2, 'Sac de couchage MT500 0°C Synthétique', 'Super confort et chaud', 1520);
INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (14, 3, 4,10,2, 'Matelas gonflable MT500 1-personne Air Isolant', 'Isolation R-value 3.3', 820);
INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (15, 3, 7,4,5, 'Gourde ÖKO 500mL', 'Filtres tout - 400L par filtre', 135);
INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (16, 3, 11,2,11, 'Sac à Dos ACE 50', 'Contenance 50L', 1680);
INSERT INTO user_equipments(user_equipment_id, user_id, equipment_id, category_id, brand_id, name, description, weight) VALUES (17, 3, 13,3,1, 'Couverture de survie', 'Réutilisable', 170);
SELECT setval('user_equipments_user_equipment_id_seq',(SELECT GREATEST(MAX(user_equipment_id), nextval('user_equipments_user_equipment_id_seq')-1) FROM "user_equipments"));

--Insertion des enregistrements des stats de chaque équipement créer.
INSERT INTO user_equipment_statistics(user_equipment_id) VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15),(16),(17);


--Insertion de randonnées.
INSERT INTO hikes(hike_id, date, distance, duration, title, negative, positive, user_id) VALUES (1, '2024-05-18', 19.2, 2, 'Lac d''Ayous', 1600, 1600, 1);
INSERT INTO hikes(hike_id, date, distance, duration, title, negative, positive, user_id) VALUES (2, '2023-07-24', 74.9, 4, 'Bout dur GR10', 6300, 6250, 1);

INSERT INTO hikes(hike_id, date, distance, duration, title, negative, positive, user_id) VALUES (3, '2024-06-25', 36, 3, 'Tour des lacs de Néouvielle', 3530, 3200, 2);
INSERT INTO hikes(hike_id, date, distance, duration, title, negative, positive, user_id) VALUES (4, '2024-08-30', 23, 2, 'Turon de Néouvielle', 2200, 2200, 2);
INSERT INTO hikes(hike_id, date, distance, duration, title, negative, positive, user_id) VALUES (5, '2024-09-14', 21, 2, 'Cirque de Gavernie et Pimené', 1900, 1900, 2);

INSERT INTO hikes(hike_id, date, distance, duration, title, negative, positive, user_id) VALUES (6, '2024-07-15', 79, 4, 'Alpes Écrins', 4800, 5200, 3);
SELECT setval('hikes_hike_id_seq',(SELECT GREATEST(MAX(hike_id), nextval('hikes_hike_id_seq')-1) FROM "hikes"));

--Modification des statistiques de chaque utilisateur
UPDATE user_statistics SET hike_count = 2, average_duration = 3,  average_distance = 47.05, average_negative = 3950, average_positive = 3925, average_weight = 0, total_duration = 6,  total_distance = 94.1, total_negative = 7900, total_positive = 7850 WHERE user_id = 1;
UPDATE user_statistics SET hike_count = 3, average_duration = 2.66,  average_distance = 26.66, average_negative = 2543.33, average_positive = 2433.33, average_weight = 0, total_duration = 7,  total_distance = 80, total_negative = 7630, total_positive = 7300 WHERE user_id = 2;
UPDATE user_statistics SET hike_count = 1, average_duration = 2,  average_distance = 21, average_negative = 1900, average_positive = 1900, average_weight = 0, total_duration = 2,  total_distance = 21, total_negative = 1900, total_positive = 1900 WHERE user_id = 3;

-- RESTE A FAIRE : METTRE A JOUR LES STATISTIQUES DE CHAQUE EQUIPEMENT EN FONCTION DE LEUR RANDONNÉES ASSOCIÉES !