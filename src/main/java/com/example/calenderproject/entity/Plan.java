package com.example.calenderproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Plan {

    // 날짜 시간 포맷터 정의
    private LocalDateTime now = LocalDateTime.now();
    private Long id;
    private String writer;
    private String contents;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public Plan(String writer, String contents, String password){
        this.writer = writer;
        this.contents = contents;
        this.password = password;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    // 조회용
    public Plan(Long id, String writer, String contents, String password, LocalDateTime createdDate, LocalDateTime modifiedDate ){
        this.id = id;
        this.writer = writer;
        this.contents = contents;
        this.password = password;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }


}
