INSERT INTO user (id, name, email, password) 
	VALUES 	(1, 'John Doe', 'john@a.com', '$2a$12$/lxCO.9A8PEdBv.51G/jc.WaUfsnrgr0Dj.wwpVOGkgB67vW59gSi'),
			(2, 'Micah Bell', 'bell@labs.org', '$2a$12$ZCvEGnvaCQuwfDgmitu.b..TRSjAO0mIzh94hGzlIIKWFgbxwelxK');

INSERT INTO ticket (id, created_at, priority, status, title, created_by) 
	VALUES 	(1, utc_timestamp, 'HIGH', 'PENDING', 'Integer metus turpis, euismod eu augue vel, euismod fringilla diam', 1),
			(2, utc_timestamp, 'HIGH', 'CLOSED', 'Duis urna ex, pellentesque sit amet volutpat et', 2),
			(3, utc_timestamp, 'HIGH', 'NOT_LISTED', 'scelerisque a ipsum', 1),
			(4, utc_timestamp, 'HIGH', 'PENDING', 'Donec venenatis scelerisque turpis et pretium', 2),
			(5, utc_timestamp, 'HIGH', 'CLOSED', 'Maecenas ultrices sollicitudin justo', 1);
			
INSERT INTO ticket (id, created_at, priority, status, title, created_by) 
	VALUES 	(6, utc_timestamp, 'NORMAL', 'NOT_LISTED', 'in hendrerit tortor mollis egestas', 1),
			(7, utc_timestamp, 'NORMAL', 'PENDING', 'Duis mattis nisi nibh', 2),
			(8, utc_timestamp, 'NORMAL', 'CLOSED', 'eu eleifend lorem lobortis ac', 1),
			(9, utc_timestamp, 'NORMAL', 'NOT_LISTED', 'Phasellus iaculis vel lectus non pulvinar', 2),
			(10, utc_timestamp, 'NORMAL', 'PENDING', 'Phasellus at magna sed urna posuere viverra', 1);
			
INSERT INTO ticket (id, created_at, priority, status, title, created_by) 
	VALUES 	(11, utc_timestamp, 'LOW', 'NOT_LISTED', 'Duis eget ornare urna', 1),
			(12, utc_timestamp, 'LOW', 'PENDING', 'Morbi eget ligula condimentum, blandit mi non, fringilla ipsum1', 2),
			(13, utc_timestamp, 'LOW', 'CLOSED', 'Aenean sodales bibendum arcu, sit amet facilisis', 1),
			(14, utc_timestamp, 'LOW', 'NOT_LISTED', 'Curabitur leo nunc, finibus at diam vitae', 2),
			(15, utc_timestamp, 'LOW', 'PENDING', 'laoreet dapibus dui', 1);					

INSERT INTO comment (id, created_at, message, created_by, ticket_id) 
	VALUES 	(1, utc_timestamp, 'The red fox runs accross the river', 1, 1),
			(2, utc_timestamp, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 2, 1),
			(3, utc_timestamp, 'Lorem ipsum?!', 2, 2);

INSERT INTO tag (id, name) 
	VALUES (1, 'aker'),
	(2, 'firewall'),
	(3, 'impressoras'), 
	(4, 'rede');

INSERT INTO ticket_has_tag (ticket_id, tag_id) 
	VALUES (1, 1),
	(1, 2),
	(2, 2);