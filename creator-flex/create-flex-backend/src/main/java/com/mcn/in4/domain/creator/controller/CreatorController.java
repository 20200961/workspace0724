package com.mcn.in4.domain.creator.controller;

import com.mcn.in4.domain.creator.dto.request.CreatorRequestDTO;
import com.mcn.in4.domain.creator.dto.response.CreatorResponseDTO;
import com.mcn.in4.domain.creator.service.CreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creators")
@RequiredArgsConstructor
public class CreatorController {

    private final CreatorService creatorService;

    @PostMapping
    public ResponseEntity<CreatorDto.CreateResponse> createCreator(@RequestBody CreatorDto.CreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreatorDto.CreateResponse(creatorService.createCreator(request)));
    }

    @GetMapping
    public ResponseEntity<List<CreatorDto.Response>> getAllCreators() {
        return ResponseEntity.ok(creatorService.getAllCreators());
    }

    @GetMapping("/{creatorId}")
    public ResponseEntity<CreatorDto.Response> getCreatorById(@PathVariable Long creatorId) {
        return ResponseEntity.ok(creatorService.getCreatorById(creatorId));
    }

    @PatchMapping("/{creatorId}")
    public ResponseEntity<CreatorDto.Response> updateCreator(
            @PathVariable Long creatorId,
            @RequestBody CreatorDto.UpdateRequest request) {
        return ResponseEntity.ok(creatorService.updateCreator(creatorId, request));
    }

    @DeleteMapping("/{creatorId}")
    public ResponseEntity<Void> deleteCreator(@PathVariable Long creatorId) {
        creatorService.deleteCreator(creatorId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<CreatorDto.Response>> getMyCreators(@PathVariable Long managerId) {
        return ResponseEntity.ok(creatorService.getMyCreators(managerId));
    }
}