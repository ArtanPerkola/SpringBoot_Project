CREATE DATABASE dmproj4;
USE dmproj4;

CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    profile_picture VARCHAR(255),
    bio TEXT
);

CREATE TABLE Posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    text_content TEXT,
    image_content VARCHAR(255),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES Users(id)
);

CREATE TABLE Comments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    text_content TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id INT,
    post_id INT,
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (post_id) REFERENCES Posts(id)
);

CREATE TABLE Followers (
    follower_id INT,
    following_id INT,
    PRIMARY KEY (follower_id, following_id),
    FOREIGN KEY (follower_id) REFERENCES Users(id),
    FOREIGN KEY (following_id) REFERENCES Users(id)
);

CREATE TABLE FriendRequests (
    requester_id INT,
    requestee_id INT,
    PRIMARY KEY (requester_id, requestee_id),
    FOREIGN KEY (requester_id) REFERENCES Users(id),
    FOREIGN KEY (requestee_id) REFERENCES Users(id)
);
