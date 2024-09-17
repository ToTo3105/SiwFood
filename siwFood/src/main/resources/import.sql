insert into amministratore(id, nome, cognome, data_nascita) values(nextval('persona_seq'), 'Jacopo', 'Lore', '2000-05-31');
insert into credenziali (id, username, password, ruolo_utente, persona_id) values(nextval('credenziali_seq'), 'jacopo.lore', '$2a$10$25H5p2IZ12fTG12hWMC.DO8ctZS3Z0HedIXHhIVIcN51Cufun7EHi', 'AMMINISTRATORE', 1);
insert into cuoco(id, nome, cognome, data_nascita) values(nextval('persona_seq'), 'a', 'b', '0001-1-1');
insert into credenziali (id, username, password, ruolo_utente, persona_id) values(nextval('credenziali_seq'), 'a.b', '$2a$10$25H5p2IZ12fTG12hWMC.DO8ctZS3Z0HedIXHhIVIcN51Cufun7EHi', 'CUOCO', 51);
