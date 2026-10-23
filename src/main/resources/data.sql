INSERT INTO kalenderwoche (kwid, kw_nummer, jahr) VALUES
    (1, 48, 2026),
    (2, 49, 2026),
    (3, 50, 2026);

INSERT INTO mitarbeiter (mid, vorname, nachname, alter, adresse, abteilung, urlaub_uebrig, benutzername, passwort) VALUES
    (1, 'Anna', 'Müller', 28, 'Hauptstraße 1, 12345 Musterstadt', 'Produktion', 30, 'anna', '$2b$12$6cE3HMLlL3JJiOFbnhYHs.cWDMAhNSnu0JamFcCbMgBublsaLeqZ6'),
    (2, 'Ben', 'Schmidt', 35, 'Gartenweg 5, 12345 Musterstadt', 'Lager', 24, 'ben', '$2b$12$CNwrpeEW4c.wOO4N9qCAdujns77qUbcopSCnY3ktktGuGhmgCIiJy'),
    (3, 'Clara', 'Weber', 42, 'Bahnhofstraße 9, 12345 Musterstadt', 'Versand', 28, 'clara', '$2b$12$QKLmvVHmH8l5N2sG6KhT..B1zho4gbR1BfNqDue1ICk62AsKO/WBe');

INSERT INTO schicht (sid, schichtart) VALUES
    (1, 'Frühschicht'),
    (2, 'Spätschicht'),
    (3, 'Nachtschicht');

INSERT INTO einsatz (mid, sid, datum_von, datum_bis) VALUES
    (1, 1, '2026-11-30', '2026-12-01'),
    (2, 2, '2026-11-30', '2026-12-01'),
    (3, 3, '2026-12-01', '2026-12-31');

INSERT INTO urlaub (uid, mid, kwid, status) VALUES
    (1, 1, 3, 'beantragt'),
    (2, 2, 2, 'genehmigt');
