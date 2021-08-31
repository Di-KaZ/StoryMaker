-- Création de la bdd et des tables

CREATE DATABASE IF NOT EXISTS story_maker;

USE story_maker;

CREATE TABLE IF NOT EXISTS user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS story (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    creationDate DATE NOT NULL,
    userId INT NOT NULL,
    firstBlocId INT NOT NULL,
    FOREIGN KEY (userId)
    REFERENCES user (id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS blocStory (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(250) NOT NULL,
    content TEXT NOT NULL,
    storyId INT,
	previousBlocId INT NOT NULL,
    FOREIGN KEY (storyId)
    REFERENCES story (id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS characters (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    stats VARCHAR(250) NOT NULL,
    storyId INT,
    FOREIGN KEY (storyId)
    REFERENCES story (id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comment (
    id INT PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL,
    creationDate DATE NOT NULL,
    userId INT,
    storyId INT,
    subCommentId INT,
    FOREIGN KEY (userId)
    REFERENCES user (id),
    FOREIGN KEY (storyId)
    REFERENCES story (id),
    FOREIGN KEY (subCommentId)
    REFERENCES comment (id)
        ON DELETE CASCADE
);
--


-- Insertion des donées
INSERT INTO `user` (`id`,`name`,`password`,`email`) VALUES (1,'root','$2a$10$O53VvxRtlyKRhHLfrffJHeYE2JGvpNaoPOGJUYwzNGEcZBNk/yXuy','root@root.com');