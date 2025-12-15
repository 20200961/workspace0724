package com.kh.jpa2.controller;

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
    public ResponseEntity<Map<String, Object>> createDecision(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = decisionService.createDecision(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getDecisions(
            @RequestParam(required = false) Long memberId) {
        List<Map<String, Object>> response = decisionService.getDecisions(memberId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{decisionId}")
    public ResponseEntity<Map<String, Object>> getDecision(@PathVariable Long decisionId) {
        Map<String, Object> response = decisionService.getDecision(decisionId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{decisionId}")
    public ResponseEntity<Map<String, String>> deleteDecision(@PathVariable Long decisionId) {
        decisionService.deleteDecision(decisionId);
        return ResponseEntity.ok(Map.of("message", "의사결정이 삭제되었습니다."));
    }

    @PostMapping("/{decisionId}/retrospective")
    public ResponseEntity<Map<String, Object>> addRetrospective(
            @PathVariable Long decisionId,
            @RequestBody Map<String, String> request) {
        Map<String, Object> response = decisionService.addRetrospective(decisionId, request);
        return ResponseEntity.ok(response);
    }
}
