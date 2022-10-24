INSERT INTO user (id, name) 
	VALUES (1, 'John Doe'),
	(2, 'Micah Bell');

INSERT INTO ticket (id, created_at, priority, status, title, created_by) 
	VALUES (1, utc_timestamp, 'HIGH', 'PENDING', 'Ticket teste', 1),
	(2, utc_timestamp, 'NORMAL', 'CLOSED', 'Ticket Finalizado0', 2);

INSERT INTO comment (id, created_at, message, created_by, ticket_id) 
	VALUES (1, utc_timestamp, 'The red fox runs accross the river', 1, 1),
	(2, utc_timestamp, 'Lorem ipsum?!', 2, 2);

INSERT INTO tag (id, name) 
	VALUES (1, 'aker'),
	(2, 'firewall'),
	(3, 'impressoras'), 
	(4, 'rede');

INSERT INTO ticket_has_tag (ticket_id, tag_id) 
	VALUES (1, 1),
	(1, 2),
	(2, 2);