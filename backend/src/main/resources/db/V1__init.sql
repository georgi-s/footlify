-- Mannschaften mit Liga und Formation
INSERT INTO mannschaft (id, name, liga, formation, trainer) VALUES 
(1, 'FC Bayern München', 'Bundesliga1', 'f442', 'Thomas Tuchel'),
(2, 'Borussia Dortmund', 'Bundesliga1', 'f433', 'Edin Terzić'),
(3, 'Hamburger SV', 'Bundesliga2', 'f343', 'Tim Walter');

-- Torwarte
INSERT INTO spieler (id, vorname, nachname, position, geburtsdatum, trikotnummer, gehalt, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten, mannschaft_id, spieler_type, spieleOhneGegentor, gegentore, haltequote) VALUES 
(1, 'Manuel', 'Neuer', 'TW', '1986-03-27', 1, 150000.00, 20, false, '2011-06-01', 0, 1, 1, 'TORWART', 10, 15, 0.85),
(2, 'Gregor', 'Kobel', 'TW', '1997-12-06', 1, 120000.00, 18, false, '2021-07-01', 0, 2, 2, 'TORWART', 8, 20, 0.80),
(3, 'Daniel', 'Heuer Fernandes', 'TW', '1992-11-13', 1, 80000.00, 22, false, '2019-07-01', 0, 1, 3, 'TORWART', 6, 25, 0.75);

-- Verteidiger
INSERT INTO spieler (id, vorname, nachname, position, geburtsdatum, trikotnummer, gehalt, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten, mannschaft_id, spieler_type, geblockteAngriffe, gewonneneZweikaempfe, passquote) VALUES 
(4, 'Matthijs', 'de Ligt', 'IV', '1999-08-12', 4, 120000.00, 15, false, '2022-07-01', 0, 3, 1, 'VERTEIDIGER', 45, 65, 0.88),
(5, 'Mats', 'Hummels', 'IV', '1988-12-16', 15, 100000.00, 20, false, '2019-07-01', 0, 2, 2, 'VERTEIDIGER', 50, 70, 0.90),
(6, 'Sebastian', 'Schonlau', 'IV', '1994-08-05', 4, 70000.00, 19, false, '2021-07-01', 1, 4, 3, 'VERTEIDIGER', 40, 60, 0.85);

-- Mittelfeldspieler
INSERT INTO spieler (id, vorname, nachname, position, geburtsdatum, trikotnummer, gehalt, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten, mannschaft_id, spieler_type, anzahlVorlagen, tore, passquote) VALUES 
(7, 'Joshua', 'Kimmich', 'ZM', '1995-02-08', 6, 180000.00, 22, false, '2015-07-01', 0, 4, 1, 'MITTELFELDSPIELER', 12, 5, 0.92),
(8, 'Marco', 'Reus', 'OM', '1989-05-31', 11, 150000.00, 16, false, '2012-07-01', 0, 1, 2, 'MITTELFELDSPIELER', 8, 7, 0.88),
(9, 'Jonas', 'Meffert', 'DM', '1994-09-04', 8, 60000.00, 21, false, '2021-07-01', 0, 5, 3, 'MITTELFELDSPIELER', 6, 2, 0.85);

-- Stürmer
INSERT INTO spieler (id, vorname, nachname, position, geburtsdatum, trikotnummer, gehalt, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten, mannschaft_id, spieler_type, geschosseneTore, schussgenauigkeit, chancenverwertung) VALUES 
(10, 'Harry', 'Kane', 'ST', '1993-07-28', 9, 250000.00, 20, false, '2023-07-01', 0, 1, 1, 'STUERMER', 25, 0.85, 0.80),
(11, 'Niclas', 'Füllkrug', 'ST', '1993-02-09', 9, 120000.00, 19, false, '2023-07-01', 0, 3, 2, 'STUERMER', 15, 0.75, 0.70),
(12, 'Robert', 'Glatzel', 'ST', '1994-01-08', 9, 80000.00, 21, false, '2021-07-01', 0, 2, 3, 'STUERMER', 18, 0.78, 0.75);

-- Turnier mit Teilnehmern
INSERT INTO turnier (id, ort, datum, insgPreisgeld, siegerMannschaft) VALUES
(1, 'Berlin', '2024-05-10', 100000, NULL),
(2, 'München', '2024-06-15', 150000, NULL);

-- Mannschaften am Turnier teilnehmen lassen
INSERT INTO turnier_teilnehmer (turnier_id, mannschaft_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3);
