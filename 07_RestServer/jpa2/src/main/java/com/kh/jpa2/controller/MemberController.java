package com.kh.jpa2.controller;

import com.kh.jpa2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> request) {
        Map<String, Object> response = memberService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        Map<String, Object> response = memberService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Map<String, Object>> getMember(@PathVariable Long memberId) {
        Map<String, Object> response = memberService.getMember(memberId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}/stats")
    public ResponseEntity<Map<String, Long>> getStats(@PathVariable Long memberId) {
        Map<String, Long> response = memberService.getStats(memberId);
        return ResponseEntity.ok(response);
    }
}