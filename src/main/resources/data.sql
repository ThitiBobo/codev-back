SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- USER
INSERT INTO `users` (`id`, `email`, `password`) VALUES (1, 'admin@gmail.com', '$2y$10$YHIMRg5NeTjxRsaddhniBu.AKEbmPVWUiW7HyShZ2p9DNxvDnPogW');
INSERT INTO `users` (`id`, `email`, `password`, `firstname`, `lastname`) VALUES (2, 'thiti@gmail.com', '$2a$04$1zyF3Uu.ZDMZuICCKcw1WO0zoHnDRMnL.v7yAm881jYEF5NOQR58.', 'thibaut', 'delplanque');

-- METROPOLIS
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('200054781',48.85680393333428, 2.3269548007371856,'Métropole du Grand Paris');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('244400404',47.229080554662545, -1.5527529596219432,'Nantes Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('200046977',45.76588434959976, 4.847735680426774,'Métropole de Lyon');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('200054807',43.42051165648866, 5.436969983373662,"Métropole d\'Aix-Marseille-Provence");
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('245900410',50.63684599615922, 3.0570254290391277,'Métropole Européenne de Lille');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('200040715',45.1849538972223, 5.7124496667840665,'Métropole Grenoble-Alpes-Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('243500139',48.11524139351814, -1.6800324514541154,'Métropole Rennes Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('243100518',43.61211739240191, 1.425974243677954,'Toulouse Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('245400676',48.688679349849814, 6.180562746013864,'Métropole du Grand Nancy');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('200030195',43.71583062835876, 7.235644063398946,"Métropole Nice Côte d\'Azur");
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('243300316',44.853004378868924, -0.5794893484922443,'Bordeaux Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('243400017',43.620201907885125, 3.865070858582002,'Montpellier Méditerranée Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('200023414',49.45060546140958, 1.0925444409808949,'Métropole Rouen Normandie');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('242900314',48.40194123251525, -4.49666736366434,'Brest Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('246700488',48.59153922720777, 7.739418377854517,'Eurométropole de Strasbourg');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('242100410',47.328839345989564, 5.0380551725115374,'Dijon Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('243700754',47.404372630048094, 0.6848141145681729,'Tours Métropole Val de Loire');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('244200770',45.444986090238075, 4.385962241793311,'Saint-Etienne Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('244500468',47.90171395294431, 1.9001964143396244,'Orléans Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('246300701',45.7811811342066, 3.088976538657531,'Clermont Auvergne Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('248300543',43.150372736585, 5.933354269494738,'Métropole Toulon-Provence-Méditerranée');



-- PROFILE
INSERT INTO `profiles` (`id`, `user_id`) VALUES (1, 2);

-- PREFERENCES
-- 3:Lyon  13:Normandie ?
INSERT INTO `profile_metropolises` (`profile_id`, `metropolis_id`) VALUES (1, 3);
INSERT INTO `profile_metropolises` (`profile_id`, `metropolis_id`) VALUES (1, 13);


COMMIT;