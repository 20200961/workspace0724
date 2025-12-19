package com.kh.jpa2.service;

import com.kh.jpa2.dto.MemberDto;
import com.kh.jpa2.entity.Member;
import com.kh.jpa2.repository.DecisionRepository;
import com.kh.jpa2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final DecisionRepository decisionRepository;

    @Transactional
    public MemberDto.Response register(MemberDto.Create createDto) {
        // 이메일 중복 체크
        Long count = memberRepository.countByEmail(createDto.getEmail());
        if (count > 0) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다: " + createDto.getEmail());
        }

        Member member = createDto.toEntity();
        memberRepository.save(member);

        return MemberDto.Response.of(
                member.getId(),
                member.getEmail(),
                member.getName(),
                member.getCreatedAt()
        );
    }

    public MemberDto.Response login(MemberDto.Login loginDto) {
        Member member = memberRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 회원이 존재하지 않습니다: " + loginDto.getEmail()));

        return MemberDto.Response.of(
                member.getId(),
                member.getEmail(),
                member.getName(),
                member.getCreatedAt()
        );
    }

    public MemberDto.Response getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. ID: " + memberId));

        return MemberDto.Response.of(
                member.getId(),
                member.getEmail(),
                member.getName(),
                member.getCreatedAt()
        );
    }

    public MemberDto.Stats getStats(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. ID: " + memberId));

        Long total = decisionRepository.countByMemberId(memberId);
        Long personal = decisionRepository.countByMemberIdAndType(memberId, "개인");
        Long team = decisionRepository.countByMemberIdAndType(memberId, "팀");
        Long withRetrospective = decisionRepository.countByMemberIdWithRetrospective(memberId);

        return MemberDto.Stats.of(total, personal, team, withRetrospective);
    }
}