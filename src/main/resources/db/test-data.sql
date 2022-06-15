INSERT INTO user (id, name) VALUES (1, 'John Doe');
INSERT INTO user (id, name) VALUES (2, 'Micah Bell');

INSERT INTO ticket (id, created_at, ticket_priority, ticket_status, title, created_by) VALUES (1, '2022-06-07 03:01:18', 'H', 'P', 'Ticket teste', 1);
INSERT INTO ticket (id, created_at, ticket_priority, ticket_status, title, created_by) VALUES (2, '2022-06-07 03:01:18', 'N', 'C', 'Ticket Finalizado0', 2);

INSERT INTO comment (id, created_at, message, created_by, ticket_id) VALUES (1, '2022-06-01 03:01:18', 'The red fox runs accross the river', 1, 1);
INSERT INTO comment (id, created_at, message, created_by, ticket_id) VALUES (2, '2022-06-02 18:01:18', 'Lorem ipsum?!', 2, 2);

INSERT INTO tag (id, name) VALUES (1, 'aker'), (2, 'firewall'), (3, 'impressoras'), (4, 'rede');

INSERT INTO ticket_has_tag (ticket_id, tag_id) VALUES (1, 1), (1, 2), (2, 2);