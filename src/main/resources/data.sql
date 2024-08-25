-- 회원 테이블에 데이터 삽입
INSERT INTO Member (username, password) VALUES ('test_user', 'password123');

-- 게시판 테이블에 데이터 삽입
INSERT INTO Board (name) VALUES ('General Discussion');

-- 게시글 테이블에 데이터 삽입
INSERT INTO Post (title, content, member_id, board_id)
VALUES ('First Post', 'This is the content of the first post.',
    (SELECT id FROM Member WHERE username = 'test_user'),
    (SELECT id FROM Board WHERE name = 'General Discussion'));

-- 댓글 테이블에 데이터 삽입
INSERT INTO Comment (content, member_id, post_id)
VALUES ('This is a comment.',
    (SELECT id FROM Member WHERE username = 'test_user'),
    (SELECT id FROM Post WHERE title = 'First Post'));
