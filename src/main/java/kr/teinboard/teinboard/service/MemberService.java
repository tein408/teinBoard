package kr.teinboard.teinboard.service;

import kr.teinboard.teinboard.controller.MemberForm;
import kr.teinboard.teinboard.domain.Member;
import kr.teinboard.teinboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long saveMember(MemberForm memberForm) {
        validateDuplicateEmail(memberForm.getEmail());
        String auth = "USER";
        Member member = new Member(memberForm.getEmail(), memberForm.getName(), memberForm.getPwd(), auth);
        return memberRepository.save(member);
    }

    public void validateDuplicateEmail(String email) {
        memberRepository.findByEmail(email)
                .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 이메일");});
    }

    public Optional<Member> findId(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public boolean findByEmail(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

}
