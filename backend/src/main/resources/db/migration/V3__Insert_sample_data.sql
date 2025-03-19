-- Beispieldaten für die Datenbank

-- Formationen einfügen
INSERT INTO formation (bezeichnung, verteidiger_anzahl, mittelfeldspieler_anzahl, stuermer_anzahl) 
VALUES 
('4-4-2', 4, 4, 2),
('4-3-3', 4, 3, 3),
('3-5-2', 3, 5, 2),
('5-3-2', 5, 3, 2),
('4-2-3-1', 4, 5, 1);

-- Ligen einfügen
INSERT INTO liga (name, saison) 
VALUES 
('Bundesliga', '2024/2025'),
('2. Bundesliga', '2024/2025'),
('3. Liga', '2024/2025'),
('DFB-Pokal', '2024/2025');

-- Mannschaften einfügen
INSERT INTO mannschaft (name, trainer, formation_id, liga_id) 
VALUES 
('FC Bayern München', 'Vincent Kompany', 1, 1),
('Borussia Dortmund', 'Nuri Sahin', 2, 1),
('RB Leipzig', 'Marco Rose', 3, 1),
('Bayer 04 Leverkusen', 'Xabi Alonso', 2, 1),
('Hamburger SV', 'Steffen Baumgart', 1, 2),
('Hertha BSC', 'Christian Fiel', 4, 2),
('SV Darmstadt 98', 'Torsten Lieberknecht', 5, 2);

-- Turniere einfügen
INSERT INTO turnier (name, ort, datum, preisgeld) 
VALUES 
('DFB-Pokal 2024/2025', 'Deutschland', '2024-08-16', 10000000.00),
('Telekom Cup 2024', 'München', '2024-07-20', 500000.00),
('Champions Cup', 'Berlin', '2024-09-10', 2000000.00);

-- Turnier-Mannschaft Zuordnungen
INSERT INTO turnier_mannschaft (turnier_id, mannschaft_id) 
VALUES 
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7),
(2, 1), (2, 2), (2, 4),
(3, 1), (3, 3), (3, 4);

-- Spieler einfügen
INSERT INTO spieler (vorname, nachname, geburtsdatum, position, mannschaft_id, ist_feldspieler, trikotnummer) 
VALUES 
-- FC Bayern Spieler
('Manuel', 'Neuer', '1986-03-27', 'TORWART', 1, false, 1),
('Joshua', 'Kimmich', '1995-02-08', 'VERTEIDIGER', 1, true, 6),
('Jamal', 'Musiala', '2003-02-26', 'MITTELFELDSPIELER', 1, true, 10),
('Harry', 'Kane', '1993-07-28', 'STUERMER', 1, true, 9),

-- Dortmund Spieler
('Gregor', 'Kobel', '1997-12-06', 'TORWART', 2, false, 1),
('Mats', 'Hummels', '1988-12-16', 'VERTEIDIGER', 2, true, 15),
('Julian', 'Brandt', '1996-05-02', 'MITTELFELDSPIELER', 2, true, 10),
('Sébastien', 'Haller', '1994-06-22', 'STUERMER', 2, true, 9);

-- Spiele einfügen
INSERT INTO spiel (heim_mannschaft_id, gast_mannschaft_id, datum, uhrzeit, ort, status, heim_tore, gast_tore, liga_id) 
VALUES 
(1, 2, '2024-08-18', '15:30:00', 'Allianz Arena', 'GEPLANT', NULL, NULL, 1),
(3, 4, '2024-08-19', '17:30:00', 'Red Bull Arena', 'GEPLANT', NULL, NULL, 1),
(5, 6, '2024-08-20', '20:30:00', 'Volksparkstadion', 'GEPLANT', NULL, NULL, 2),
(1, 3, '2024-07-10', '15:30:00', 'Allianz Arena', 'BEENDET', 3, 1, 1),
(2, 4, '2024-07-11', '18:30:00', 'Signal Iduna Park', 'BEENDET', 2, 2, 1);
