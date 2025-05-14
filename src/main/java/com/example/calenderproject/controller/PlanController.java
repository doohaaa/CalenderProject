package com.example.calenderproject.controller;

import com.example.calenderproject.dto.PlanRequestDto;
import com.example.calenderproject.dto.PlanResponseDto;
import com.example.calenderproject.entity.Plan;
import com.example.calenderproject.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/plans")
public class PlanController {

    // 주입된 의존성을 변경할 수 없어 객체의 상태를 안전하게 유지할 수 있음
    private final PlanService planService;

    public PlanController(PlanService planService){this.planService = planService;}

    @PostMapping
    public ResponseEntity<PlanResponseDto> createPlan(@RequestBody PlanRequestDto dto){
        return new ResponseEntity<>(planService.savePlan(dto), HttpStatus.CREATED);
    }

    /* 전체 조회
    @GetMapping
    public List<PlanResponseDto> findAllPlans() {
        return planService.findAllPlans();
    }
    */

    // 필터 있는 전체 조회
    @GetMapping
    public List<PlanResponseDto> getPlans(
            @RequestParam(required = false) String modifiedDate,
            @RequestParam(required = false) String writer
    ){
        return planService.findPlans(modifiedDate, writer);
    }

    // 일정 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDto> findPlanById(@PathVariable Long id) {
        return new ResponseEntity<>(planService.findPlanById(id), HttpStatus.OK);
    }


    /* lv2 미완성
    // 일정 할일, 작성자 수정
    @PatchMapping("{/id}")
    public ResponseEntity<PlanResponseDto> updateWriterOrContents(
            @PathVariable Long id,
            @RequestBody PlanRequestDto requestDto
    ){
        return new ResponseEntity<>(planService.updateWriterOrContents(id, requestDto.getWriter(), requestDto.getContents() ), HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id){
        planService.deletePlan(id);
        // 성공한 경우
        return new ResponseEntity<>(HttpStatus.OK);
    }
    */
}
