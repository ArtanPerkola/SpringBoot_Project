INSERT INTO User (username, password, email, profile_picture, bio)
VALUES ('JohnDoe', 'password123', 'johndoe@gmail.com', '/images/johndoe.jpg', 'Hello, I am John Doe.');

INSERT INTO User (username, password, email, profile_picture, bio)
VALUES ('JaneDoe', 'password456', 'janedoe@gmail.com', '/images/janedoe.jpg', 'Hello, I am Jane Doe.');

INSERT INTO User (username, password, email, profile_picture, bio)
VALUES ('MikeSmith', 'password789', 'mikesmith@gmail.com', '/images/mikesmith.jpg', 'Hello, I am Mike Smith.');

-- Assume the IDs of JohnDoe, JaneDoe, and MikeSmith are 1, 2, and 3 respectively

INSERT INTO Post (text_content, image_content, timestamp, user_id)
VALUES ('This is my first post!', '/posts/johndoe/firstpost.jpg', UNIX_TIMESTAMP(), 1);

INSERT INTO Post (text_content, image_content, timestamp, user_id)
VALUES ('This is my first post!', '/posts/janedoe/firstpost.jpg', UNIX_TIMESTAMP(), 2);

INSERT INTO Post (text_content, image_content, timestamp, user_id)
VALUES ('This is my first post!', '/posts/mikesmith/firstpost.jpg', UNIX_TIMESTAMP(), 3);

-- Assume the IDs of the posts are 1, 2, and 3 respectively

INSERT INTO Comment (text_content, timestamp, user_id, post_id)
VALUES ('Nice post!', UNIX_TIMESTAMP(), 2, 1);

INSERT INTO Comment (text_content, timestamp, user_id, post_id)
VALUES ('Nice post!', UNIX_TIMESTAMP(), 3, 1);

INSERT INTO Comment (text_content, timestamp, user_id, post_id)
VALUES ('Nice post!', UNIX_TIMESTAMP(), 1, 2);
