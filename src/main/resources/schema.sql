DROP TABLE IF EXISTS Contacts;

CREATE TABLE Contacts (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  middle_name VARCHAR(250) DEFAULT NULL,
  last_name VARCHAR(250) NOT NULL,
  street_name VARCHAR(250) NOT NULL,
  city_name VARCHAR(250) NOT NULL,
  state_name VARCHAR(250) NOT NULL,
  zip_code VARCHAR(5) NOT NULL,
  home_phone VARCHAR(10) DEFAULT NULL,
  work_phone VARCHAR(10) DEFAULT NULL,
  mobile_phone VARCHAR(10) DEFAULT NULL,
  email_address VARCHAR(250) NOT NULL
);
