package com.example.calenderproject.service;

import com.example.calenderproject.dto.PlanRequestDto;
import com.example.calenderproject.dto.PlanResponseDto;
import com.example.calenderproject.entity.Plan;
import com.example.calenderproject.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository){
        this.planRepository = planRepository;
    }

    @Override
    public PlanResponseDto savePlan(PlanRequestDto dto) {

        // 요청 받은 데이터로 PLAN 객체 생성, ID 없음
        Plan plan = new Plan(dto.getWriter(), dto.getContents(), dto.getPassword());

        return planRepository.savePlan(plan);
    }

    @Override
    public List<PlanResponseDto> findAllPlans() {
        return planRepository.findAllPlans();
    }

    @Override
    public PlanResponseDto findPlanById(Long id) {
        Plan plan = planRepository.findPlanByIdOrElseThrow(id);
        return new PlanResponseDto(plan);
    }
}
