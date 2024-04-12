DROP TABLE IF EXISTS personTable;

CREATE TABLE IF NOT EXISTS personTable ( id INT AUTO_INCREMENT PRIMARY KEY,
                                         name varchar(50) NOT NULL,
                                         phone varchar(50) NOT NULL,
                                         email varchar(50) NOT NULL,
                                         note varchar(100) NOT NULL);


