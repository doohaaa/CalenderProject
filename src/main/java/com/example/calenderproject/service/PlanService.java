package com.example.calenderproject.service;

import com.example.calenderproject.dto.PlanRequestDto;
import com.example.calenderproject.dto.PlanResponseDto;

import java.util.List;

public interface PlanService {

    PlanResponseDto savePlan(PlanRequestDto dto);

    /*
    List<PlanResponseDto> findAllPlans();
    */

    List<PlanResponseDto> findPlans(String modifiedDate, String writer);

    PlanResponseDto findPlanById(Long id);

    /* lv2 미완성
    PlanResponseDto updateWriterOrContents(Long id, String writer, String contents);

    void deletePlan(Long id, String password);
     */
}
