package com.mcn.in4.domain.creator.service;

import com.mcn.in4.domain.creator.dto.request.CreatorRequestDTO;
import com.mcn.in4.domain.creator.dto.response.CreatorResponseDTO;
import com.mcn.in4.domain.creator.repository.CreatorDetailRepository;
import com.mcn.in4.domain.creator.repository.CreatorRepository;
import com.mcn.in4.domain.creator.repository.MemberProfileRepository;
import com.mcn.in4.entity.member.Member;
import com.mcn.in4.entity.member.MemberCreatorDetail;
import com.mcn.in4.entity.member.MemberProfile;
import com.mcn.in4.entity.member.memberEnum.CreatorPlatform;
import com.mcn.in4.entity.member.memberEnum.CreatorStatus;
import com.mcn.in4.entity.member.memberEnum.MemberRole;
import com.mcn.in4.entity.member.memberEnum.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreatorService {

    private final CreatorRepository creatorRepository;
    private final CreatorDetailRepository creatorDetailRepository;
    private final MemberProfileRepository memberProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long createCreator(CreatorRequestDTO.Create request) {
        validateDuplicateAccount(request.getMemberAccount());

        Member manager = findAndValidateManager(request.getMemberManagerId());
        Member creator = buildCreator(request);
        creatorRepository.save(creator);

        MemberCreatorDetail detail = buildCreatorDetail(request, creator, manager);
        creatorDetailRepository.save(detail);

        return creator.getMemberId();
    }

    public List<CreatorResponseDTO.Info> getAllCreators() {
        List<Member> creators = creatorRepository.findAllCreatorsWithDepartment(
                MemberRole.CREATOR, MemberStatus.WORKING);
        return buildCreatorResponses(creators);
    }

    public CreatorResponseDTO.Info getCreatorById(Long creatorId) {
        Member creator = findCreatorById(creatorId);
        MemberCreatorDetail detail = findCreatorDetailById(creatorId);
        MemberProfile profile = findProfileById(creatorId);
        return buildCreatorResponse(creator, detail, profile);
    }

    @Transactional
    public CreatorResponseDTO.Info updateCreator(Long creatorId, CreatorRequestDTO.Update request) {
        Member creator = findCreatorById(creatorId);
        MemberCreatorDetail detail = findCreatorDetailById(creatorId);

        if (hasCreatorInfoChanged(request)) {
            creator = updateCreatorMember(creator, request);
        }

        Member manager = getUpdatedManager(detail, request.getMemberManagerId());
        detail = updateCreatorDetail(creator, detail, manager, request);

        return buildCreatorResponse(creator, detail, findProfileById(creatorId));
    }

    @Transactional
    public void deleteCreator(Long creatorId) {
        Member creator = findCreatorById(creatorId);
        creatorRepository.save(buildDeletedCreator(creator));
    }

    public List<CreatorResponseDTO.Info> getMyCreators(Long managerId) {
        List<Member> creators = creatorRepository.findCreatorsByManagerIdWithDepartment(
                managerId, MemberRole.CREATOR, MemberStatus.WORKING);
        return buildCreatorResponses(creators);
    }

    // ========== Private Helper Methods ==========

    private void validateDuplicateAccount(String memberAccount) {
        if (creatorRepository.existsByMemberAccount(memberAccount)) {
            throw new IllegalArgumentException("이미 존재하는 사번입니다: " + memberAccount);
        }
    }

    private Member findAndValidateManager(Long managerId) {
        return creatorRepository.findManagerById(managerId, MemberRole.MANAGER)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 매니저입니다: " + managerId));
    }

    private Member findCreatorById(Long creatorId) {
        return creatorRepository.findCreatorByIdWithDepartment(
                creatorId, MemberRole.CREATOR, MemberStatus.WORKING)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 크리에이터입니다: " + creatorId));
    }

    private MemberCreatorDetail findCreatorDetailById(Long creatorId) {
        return creatorDetailRepository.findByCreatorIdWithManager(creatorId)
                .orElseThrow(() -> new IllegalArgumentException("크리에이터 상세 정보가 없습니다"));
    }

    private MemberProfile findProfileById(Long creatorId) {
        return memberProfileRepository.findByMember_MemberId(creatorId).orElse(null);
    }

    private Member buildCreator(CreatorRequestDTO.Create request) {
        return Member.builder()
                .memberAccount(request.getMemberAccount())
                .memberPassword(passwordEncoder.encode(request.getMemberPassword()))
                .memberName(request.getMemberName())
                .memberRole(MemberRole.CREATOR)
                .memberStatus(MemberStatus.WORKING)
                .build();
    }

    private MemberCreatorDetail buildCreatorDetail(CreatorRequestDTO.Create request, Member creator, Member manager) {
        return MemberCreatorDetail.builder()
                .memberCreator(creator)
                .memberManager(manager)
                .creatorSubscribe(request.getCreatorSubscribe())
                .creatorCategory(request.getCreatorCategory())
                .creatorPlatform(CreatorPlatform.valueOf(request.getCreatorPlatform()))
                .creatorStatus(CreatorStatus.valueOf(request.getCreatorStatus()))
                .build();
    }

    private boolean hasCreatorInfoChanged(CreatorRequestDTO.Update request) {
        return request.getMemberName() != null ||
                request.getMemberAccount() != null ||
                request.getMemberPassword() != null;
    }

    private Member getUpdatedManager(MemberCreatorDetail detail, Long newManagerId) {
        if (newManagerId != null && !newManagerId.equals(detail.getMemberManager().getMemberId())) {
            return findAndValidateManager(newManagerId);
        }
        return detail.getMemberManager();
    }

    private Member updateCreatorMember(Member creator, CreatorRequestDTO.Update request) {
        return creatorRepository.save(Member.builder()
                .memberId(creator.getMemberId())
                .memberAccount(
                        request.getMemberAccount() != null ? request.getMemberAccount() : creator.getMemberAccount())
                .memberPassword(
                        request.getMemberPassword() != null ? passwordEncoder.encode(request.getMemberPassword())
                                : creator.getMemberPassword())
                .memberName(request.getMemberName() != null ? request.getMemberName() : creator.getMemberName())
                .memberRole(creator.getMemberRole())
                .memberStatus(creator.getMemberStatus())
                .department(creator.getDepartment())
                .build());
    }

    private MemberCreatorDetail updateCreatorDetail(Member creator, MemberCreatorDetail detail,
            Member manager, CreatorRequestDTO.Update request) {
        return creatorDetailRepository.save(MemberCreatorDetail.builder()
                .creatorDetailId(detail.getCreatorDetailId())
                .memberCreator(creator)
                .memberManager(manager)
                .creatorSubscribe(request.getCreatorSubscribe() != null ? request.getCreatorSubscribe()
                        : detail.getCreatorSubscribe())
                .creatorCategory(request.getCreatorCategory() != null ? request.getCreatorCategory()
                        : detail.getCreatorCategory())
                .creatorPlatform(
                        request.getCreatorPlatform() != null ? CreatorPlatform.valueOf(request.getCreatorPlatform())
                                : detail.getCreatorPlatform())
                .creatorStatus(request.getCreatorStatus() != null ? CreatorStatus.valueOf(request.getCreatorStatus())
                        : detail.getCreatorStatus())
                .build());
    }

    private Member buildDeletedCreator(Member creator) {
        return Member.builder()
                .memberId(creator.getMemberId())
                .memberAccount(creator.getMemberAccount())
                .memberPassword(creator.getMemberPassword())
                .memberName(creator.getMemberName())
                .memberRole(creator.getMemberRole())
                .memberStatus(MemberStatus.SUSPENDED)
                .department(creator.getDepartment())
                .build();
    }

    private List<CreatorResponseDTO.Info> buildCreatorResponses(List<Member> creators) {
        if (creators.isEmpty())
            return List.of();

        List<Long> creatorIds = creators.stream()
                .map(Member::getMemberId)
                .collect(Collectors.toList());

        Map<Long, MemberCreatorDetail> detailMap = creatorDetailRepository
                .findByCreatorIdsWithManager(creatorIds).stream()
                .collect(Collectors.toMap(d -> d.getMemberCreator().getMemberId(), d -> d));

        Map<Long, MemberProfile> profileMap = memberProfileRepository
                .findByMemberIds(creatorIds).stream()
                .collect(Collectors.toMap(p -> p.getMember().getMemberId(), p -> p));

        return creators.stream()
                .map(creator -> {
                    MemberCreatorDetail detail = detailMap.get(creator.getMemberId());
                    if (detail == null) {
                        throw new IllegalArgumentException("크리에이터 상세 정보가 없습니다: " + creator.getMemberId());
                    }
                    return buildCreatorResponse(creator, detail, profileMap.get(creator.getMemberId()));
                })
                .collect(Collectors.toList());
    }

    private CreatorResponseDTO.Info buildCreatorResponse(Member creator, MemberCreatorDetail detail,
            MemberProfile profile) {
        return profile != null ? CreatorResponseDTO.Info.fromWithProfile(creator, detail,
                profile.getProfileImage(), profile.getProfileBanner()) : CreatorResponseDTO.Info.from(creator, detail);
    }
}