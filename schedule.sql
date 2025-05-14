use plan;


CREATE TABLE plan
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '식별자',
    writer VARCHAR(100) COMMENT '작성자',
    contents VARCHAR(200) COMMENT '일정',
    password VARCHAR(200) COMMENT '비밀번호',
    createdDate DATETIME COMMENT '작성일',
    modifiedDate DATETIME COMMENT '수정일'
);