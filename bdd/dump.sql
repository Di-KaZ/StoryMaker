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
    cover TEXT,
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
	cover text,
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
    creationDate DATETIME NOT NULL,
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

INSERT INTO `story` (`id`, `name`, `description`, `creationDate`, `cover`, `userId`, `firstBlocId`) VALUES (1, 'test cover img in bloc', 'test', '2021-09-02', 'https://www.codingwithjesse.com/images/me_umbrella.jpg', 1, 1);

INSERT INTO `blocStory` (`id`, `name`, `content`, `storyId`, `previousBlocId`, `cover`) VALUES (1, 'premier choix', 'Ceci est un texte tres looooooooooong', 1, -1, 'https://upload.wikimedia.org/wikipedia/commons/9/9a/Gull_portrait_ca_usa.jpg');