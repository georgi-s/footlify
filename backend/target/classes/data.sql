-- Ligen
INSERT INTO liga (name, land) VALUES 
('Bundesliga', 'Deutschland'),
('2. Bundesliga', 'Deutschland'),
('Premier League', 'England');

-- Mannschaften
INSERT INTO mannschaft (name, liga_id) VALUES 
('FC Bayern München', 1),
('Borussia Dortmund', 1),
('Hamburger SV', 2);

-- Formationen
INSERT INTO formation (name, verteidiger, mittelfeldspieler, stuermer, mannschaft_id) VALUES 
('4-4-2', 4, 4, 2, 1),
('4-3-3', 4, 3, 3, 2),
('3-5-2', 3, 5, 2, 3);

-- Spieler
-- FC Bayern München Spieler
INSERT INTO spieler (name, position, alter, trikotnummer, gehalt, mannschaft_id) VALUES 
('Manuel Neuer', 'Torwart', 35, 1, 150000, 1),
('Joshua Kimmich', 'Mittelfeldspieler', 28, 6, 120000, 1),
('Thomas Müller', 'Stuermer', 33, 25, 130000, 1);

-- Borussia Dortmund Spieler
INSERT INTO spieler (name, position, alter, trikotnummer, gehalt, mannschaft_id) VALUES 
('Gregor Kobel', 'Torwart', 25, 1, 80000, 2),
('Marco Reus', 'Mittelfeldspieler', 33, 11, 100000, 2),
('Niclas Füllkrug', 'Stuermer', 30, 9, 90000, 2);

-- Hamburger SV Spieler
INSERT INTO spieler (name, position, alter, trikotnummer, gehalt, mannschaft_id) VALUES 
('Daniel Heuer Fernandes', 'Torwart', 31, 1, 40000, 3),
('Jonas Meffert', 'Mittelfeldspieler', 29, 6, 35000, 3),
('Robert Glatzel', 'Stuermer', 30, 9, 45000, 3);

-- Torwart-Attribute
INSERT INTO torwart (id, reaktion, fangsicherheit) VALUES 
(1, 90, 88),  -- Neuer
(4, 85, 83),  -- Kobel
(7, 75, 77);  -- Heuer Fernandes

-- Mittelfeldspieler-Attribute
INSERT INTO mittelfeldspieler (id, passgenauigkeit, ausdauer) VALUES 
(2, 92, 90),  -- Kimmich
(5, 88, 85),  -- Reus
(8, 78, 82);  -- Meffert

-- Stürmer-Attribute
INSERT INTO stuermer (id, schusskraft, technik) VALUES 
(3, 85, 89),  -- Müller
(6, 87, 83),  -- Füllkrug
(9, 80, 75);  -- Glatzel
