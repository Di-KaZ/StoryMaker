CREATE DATABASE IF NOT EXISTS story_maker;

USE story_maker;

CREATE TABLE IF NOT EXISTS user (
    userid INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(150) NOT NULL UNIQUE,
    user_password VARCHAR(100) NOT NULL,
    user_mail VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS story (
    story_id INT PRIMARY KEY AUTO_INCREMENT,
    story_name VARCHAR(150) NOT NULL,
    story_description TEXT NOT NULL,
    story_date DATE NOT NULL,
    userid INT NOT NULL,
    firstIdBloc INT NOT NULL,
    FOREIGN KEY (userid)
    REFERENCES user (userid)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS bloc_story (
    bloc_story_id INT PRIMARY KEY AUTO_INCREMENT,
    bloc_story_name VARCHAR(250) NOT NULL,
    bloc_story_text TEXT NOT NULL,
    story_id INT,
    FOREIGN KEY (story_id)
    REFERENCES story (story_id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS story_character (
    character_id INT PRIMARY KEY AUTO_INCREMENT,
    character_name VARCHAR(200) NOT NULL,
    character_stat VARCHAR(250) NOT NULL,
    story_id INT,
    FOREIGN KEY (story_id)
    REFERENCES story (story_id)
        ON DELETE CASCADE
);

INSERT INTO user (username,user_password,user_mail)
VALUES('toto', '12345', 'toto@gmail.com'),
      ('John', '45678', 'john@gmail.com'),
      ('Moussa', '12345', 'Moussa@gmail.com');

INSERT INTO story (story_name, story_description, story_date, userid, firstIdBloc)
VALUES('story', 'This is my first story !', '2020-01-12',1, 1);

INSERT INTO bloc_story (bloc_story_name,bloc_story_text, story_id)
VALUES('choix', 'Ble ble ble', 1);

INSERT INTO story_character (character_name,character_stat, story_id)
VALUES('Hubert', 'stat', 1);