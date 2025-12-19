package com.kh.jpa2.controller;

import com.kh.jpa2.dto.DecisionDto;
import com.kh.jpa2.dto.RetrospectiveDto;
import com.kh.jpa2.service.DecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/decisions")
@RequiredArgsConstructor
public class DecisionController {

    private final DecisionService decisionService;

    @PostMapping
    public ResponseEntity<DecisionDto.Response> createDecision(@RequestBody DecisionDto.Create createDto) {
        DecisionDto.Response response = decisionService.createDecision(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<DecisionDto.ListResponse>> getDecisions(
            @RequestParam(required = false) Long memberId) {
        List<DecisionDto.ListResponse> response = decisionService.getDecisions(memberId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{decisionId}")
    public ResponseEntity<DecisionDto.Response> getDecision(@PathVariable Long decisionId) {
        DecisionDto.Response response = decisionService.getDecision(decisionId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{decisionId}")
    public ResponseEntity<Map<String, String>> deleteDecision(@PathVariable Long decisionId) {
        decisionService.deleteDecision(decisionId);
        return ResponseEntity.ok(Map.of("message", "의사결정이 삭제되었습니다."));
    }

    @PostMapping("/{decisionId}/retrospective")
    public ResponseEntity<RetrospectiveDto.Response> addRetrospective(
            @PathVariable Long decisionId,
            @RequestBody RetrospectiveDto.Create createDto) {
        RetrospectiveDto.Response response = decisionService.addRetrospective(decisionId, createDto);
        return ResponseEntity.ok(response);
    }
}