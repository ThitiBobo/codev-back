SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

INSERT INTO `users` (`username`, `email`, `password`) VALUES ('admin', 'admin@mail.com', '$2y$10$YHIMRg5NeTjxRsaddhniBu.AKEbmPVWUiW7HyShZ2p9DNxvDnPogW');
INSERT INTO `users` (`username`, `email`, `password`) VALUES ('thiti', 'thiti@mail.com', '$2a$04$1zyF3Uu.ZDMZuICCKcw1WO0zoHnDRMnL.v7yAm881jYEF5NOQR58.');

COMMIT;