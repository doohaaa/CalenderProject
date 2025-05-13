package com.example.calenderproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Repository;

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


}
