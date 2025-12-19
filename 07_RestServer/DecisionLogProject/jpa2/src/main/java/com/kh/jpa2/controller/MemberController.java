package com.kh.jpa2.controller;

import com.kh.jpa2.dto.MemberDto;
import com.kh.jpa2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberDto.Response> register(@RequestBody MemberDto.Create createDto) {
        MemberDto.Response response = memberService.register(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberDto.Response> login(@RequestBody MemberDto.Login loginDto) {
        MemberDto.Response response = memberService.login(loginDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDto.Response> getMember(@PathVariable Long memberId) {
        MemberDto.Response response = memberService.getMember(memberId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}/stats")
    public ResponseEntity<MemberDto.Stats> getStats(@PathVariable Long memberId) {
        MemberDto.Stats response = memberService.getStats(memberId);
        return ResponseEntity.ok(response);
    }
}