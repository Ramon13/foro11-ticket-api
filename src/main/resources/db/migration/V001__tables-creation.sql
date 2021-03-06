CREATE TABLE tag (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(16) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE user (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(64) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE ticket (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(128) NOT NULL,
  created_at DATETIME NOT NULL,
  ticket_status CHAR(1) NOT NULL,
  ticket_priority CHAR(1) NOT NULL,
  created_by BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_ticket_user FOREIGN KEY (created_by) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4;

CREATE TABLE ticket_has_tag (
  ticket_id BIGINT NOT NULL,
  tag_id BIGINT NOT NULL,
  CONSTRAINT fk_ticket_has_tag_ticket FOREIGN KEY (ticket_id) REFERENCES ticket(id),
  CONSTRAINT fk_ticket_has_tag_tag FOREIGN KEY (tag_id) REFERENCES tag(id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4;

CREATE TABLE comment (
  id BIGINT NOT NULL AUTO_INCREMENT,
  message VARCHAR(10240) NOT NULL,
  created_at DATETIME NOT NULL,
  created_by BIGINT NOT NULL,
  ticket_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_comment_user FOREIGN KEY (created_by) REFERENCES user(id),
  CONSTRAINT fk_comment_ticket FOREIGN KEY (ticket_id) REFERENCES ticket(id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8mb4;