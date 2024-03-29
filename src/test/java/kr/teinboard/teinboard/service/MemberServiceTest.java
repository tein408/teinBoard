package kr.teinboard.teinboard.service;

import kr.teinboard.teinboard.controller.MemberForm;
import kr.teinboard.teinboard.domain.Member;
import kr.teinboard.teinboard.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    public void memberSave() throws Exception {
        //given
        MemberForm member = new MemberForm("testEmail@test.email", "name", "pwd", "USER");

        //when
        Long savdId = memberService.saveMember(member);

        //then
        //assertEquals(expected, actual)
        //assertEquals(1, savdId);
        Member findMember = memberService.findId(savdId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void validateDuplicateEmail() throws Exception {
        //given
        MemberForm member1 = new MemberForm("test@test.email", "name", "pwd", "USER");
        MemberForm member2 = new MemberForm("test@test.email", "name", "pwd", "USER");

        //when
        memberService.saveMember(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.saveMember(member2));
        //member2 entity를 DB에 저장시 예외가 발생할 경우 예외를 IllegalStateException 에 담음

        //then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 이메일");
    }

    @Test
    public void findByEamil() throws Exception {
        //given
        MemberForm member1 = new MemberForm("test@test.email", "name", "pwd", "USER");
        MemberForm member2 = new MemberForm("test2@test.email", "name2", "pwd2", "USER");

        //when
        memberService.saveMember(member1);
        memberService.saveMember(member2);

        //then
        assertEquals(true, memberService.findByEmail("test2@test.email"));
    }

}