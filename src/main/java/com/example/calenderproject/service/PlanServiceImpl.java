package com.example.calenderproject.service;

import com.example.calenderproject.dto.PlanRequestDto;
import com.example.calenderproject.dto.PlanResponseDto;
import com.example.calenderproject.entity.Plan;
import com.example.calenderproject.repository.PlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<PlanResponseDto> plans = planRepository.findAllPlans();
        return plans;

    }

    @Override
    public List<PlanResponseDto> findPlans(String modifiedDate, String writer) {
        List<PlanResponseDto> plans = planRepository.findPlans(modifiedDate, writer);

        return plans;

    }


    @Override
    public PlanResponseDto findPlanById(Long id) {
        Optional<Plan> optionalPlan = planRepository.findPlanById(id);
        // NPE 방지
        if (optionalPlan.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        // 확인
        System.out.println("Service layer 에서의 id : " + optionalPlan.get().getId());


        return new PlanResponseDto(optionalPlan.get());
    }


}
