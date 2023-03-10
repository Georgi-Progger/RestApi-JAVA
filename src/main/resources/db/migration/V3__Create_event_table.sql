
CREATE TABLE IF NOT EXISTS events (
  id  int(11) NOT NULL AUTO_INCREMENT,
  USER_ID INT NOT NULL,
  FILE_ID INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (USER_ID) REFERENCES users(id) on delete cascade,
  FOREIGN KEY (FILE_ID) REFERENCES files(id) on delete cascade
);
