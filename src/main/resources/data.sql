SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- USER
INSERT INTO `users` (`id`, `username`, `email`, `password`) VALUES (1, 'admin', 'admin@mail.com', '$2y$10$YHIMRg5NeTjxRsaddhniBu.AKEbmPVWUiW7HyShZ2p9DNxvDnPogW');
INSERT INTO `users` (`id`, `username`, `email`, `password`) VALUES (2, 'thiti', 'thiti@mail.com', '$2a$04$1zyF3Uu.ZDMZuICCKcw1WO0zoHnDRMnL.v7yAm881jYEF5NOQR58.');

-- PROFILE
INSERT INTO `profiles` (`id`, `user_id`) VALUES (1, 2);

-- METROPOLIS
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('200054781',0,0,'Métropole du Grand Paris');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('244400404',0,0,'Nantes Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('200046977',0,0,'Métropole de Lyon');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('200054807',0,0,"Métropole d\'Aix-Marseille-Provence");
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('245900410',0,0,'Métropole Européenne de Lille');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('200030195',0,0,'Métropole Grenoble-Alpes-Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('200040715',0,0,'Métropole Rennes Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('243100518',0,0,'Toulouse Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('243500139',0,0,'Métropole du Grand Nancy');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('245400676',0,0,"Métropole Nice Côte d\'Azur");
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('243300316',0,0,'Bordeaux Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('243400017',0,0,'Montpellier Méditerranée Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('200023414',0,0,'Métropole Rouen Normandie');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('242900314',0,0,'Brest Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('246700488',0,0,'Eurométropole de Strasbourg');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('242100410',0,0,'Dijon Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('243700754',0,0,'Tours Métropole Val de Loire');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('244200770',0,0,'Saint-Etienne Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('244500468',0,0,'Orléans Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('246300701',0,0,'Clermont Auvergne Métropole');
INSERT INTO `metropolises` (`code`, `latitude`, `longitude`, `name`) VALUES ('248300543',0,0,'Métropole Toulon-Provence-Méditerranée');

-- PREFERENCES
-- 3:Lyon  13:Normandie ?
INSERT INTO `profile_metropolises` (`profile_id`, `metropolis_id`) VALUES (1, 3);
INSERT INTO `profile_metropolises` (`profile_id`, `metropolis_id`) VALUES (1, 13);


COMMIT;