package kr.teinboard.teinboard.service;

import kr.teinboard.teinboard.controller.MemberForm;
import kr.teinboard.teinboard.domain.Member;
import kr.teinboard.teinboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long saveMember(MemberForm memberForm) {
        String auth = "USER";

        Member member = new Member(memberForm.getEmail(), memberForm.getName(), memberForm.getPwd(), auth);

        return memberRepository.save(member);
    }

}
