



INSERT INTO schicht (schichtart)
SELECT 'Frühschicht'
WHERE NOT EXISTS (
    SELECT 1 FROM schicht WHERE schichtart = 'Frühschicht'
);

INSERT INTO schicht (schichtart)
SELECT 'Spätschicht'
WHERE NOT EXISTS (
    SELECT 1 FROM schicht WHERE schichtart = 'Spätschicht'
);

INSERT INTO schicht (schichtart)
SELECT 'Nachtschicht'
WHERE NOT EXISTS (
    SELECT 1 FROM schicht WHERE schichtart = 'Nachtschicht'
);

INSERT INTO mitarbeiter (vorname, nachname, alter, adresse, abteilung, urlaub_uebrig)
SELECT 'Max', 'Mustermann', 35, 'Musterstraße 1', 'Produktion', 30
WHERE NOT EXISTS (
    SELECT 1 FROM mitarbeiter
    WHERE vorname = 'Max' AND nachname = 'Mustermann'
);

INSERT INTO mitarbeiter (vorname, nachname, alter, adresse, abteilung, urlaub_uebrig)
SELECT 'Erika', 'Musterfrau', 32, 'Beispielweg 5', 'Verwaltung', 28
WHERE NOT EXISTS (
    SELECT 1 FROM mitarbeiter
    WHERE vorname = 'Erika' AND nachname = 'Musterfrau'
);

INSERT INTO kalenderwoche (kw_nummer, jahr)
SELECT 1, 2026
WHERE NOT EXISTS (
    SELECT 1 FROM kalenderwoche WHERE kw_nummer = 1 AND jahr = 2026
);

INSERT INTO urlaub (mid, kwid, status)
SELECT m.mid, k.kwid, 'beantragt'
FROM mitarbeiter m
JOIN kalenderwoche k ON k.kw_nummer = 1 AND k.jahr = 2026
WHERE m.vorname = 'Max' AND m.nachname = 'Mustermann'
  AND NOT EXISTS (
      SELECT 1
      FROM urlaub u
      WHERE u.mid = m.mid AND u.kwid = k.kwid
  );