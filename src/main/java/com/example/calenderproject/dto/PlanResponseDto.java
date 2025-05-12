package com.example.calenderproject.dto;

import com.example.calenderproject.entity.Plan;
import com.example.calenderproject.service.PlanService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PlanResponseDto {


    private Long id;
    private String writer;
    private String contents;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PlanResponseDto(Plan plan){
        this.id = plan.getId();
        this.writer = plan.getWriter();
        this.contents = plan.getContents();
        this.password = plan.getPassword();
        this.createdDate = plan.getCreatedDate();
        this.modifiedDate = plan.getModifiedDate();
    }

}
