package com.example.calenderproject.repository;

import com.example.calenderproject.dto.PlanRequestDto;
import com.example.calenderproject.dto.PlanResponseDto;
import com.example.calenderproject.entity.Plan;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public interface PlanRepository {

    PlanResponseDto savePlan(Plan plan);

    List<PlanResponseDto> findAllPlans();

    List<PlanResponseDto> findPlans(String modifiedDate, String writer);

    Optional<Plan> findPlanById(Long id);

    // Plan findPlanByIdOrElseThrow(Long id);
    PlanResponseDto updateWriterOrContents(Long id, String modifiedWriter, String modifiedContents);

    int deletePlan(Long id, String password);

}
