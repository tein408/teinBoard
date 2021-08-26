package kr.teinboard.teinboard.service;

import kr.teinboard.teinboard.controller.MemberForm;
import kr.teinboard.teinboard.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

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
        MemberForm member = new MemberForm("test@test.email", "name", "pwd", "USER");

        //when
        Long savdId = memberService.saveMember(member);

        //then
        assertEquals(savdId, 1);
    }

}