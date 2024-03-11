create table article (
    id BIGINT primary key auto_increment,
    title VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL
);

INSERT INTO article(title, content) VALUES ('블로그 제목 1', '블로그 내용 1');
INSERT INTO article(title, content) VALUES ('블로그 제목 2', '블로그 내용 2');
INSERT INTO article(title, content) VALUES ('블로그 제목 3', '블로그 내용 3');
