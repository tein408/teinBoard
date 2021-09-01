package kr.teinboard.teinboard.controller;

import kr.teinboard.teinboard.domain.Member;
import kr.teinboard.teinboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/member/new")
    public String createMemberForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "/members/createMemberForm";
    }

    @ResponseBody
    @PostMapping(value = "/member/new")
    public String createMember(@RequestBody @Valid MemberForm memberForm, BindingResult result) {
        //BindingResult -> name field에 대해 에러를 뽑아준다.
        if(result.hasErrors()){
            return "/members/createMemberForm";
        }
        memberService.saveMember(memberForm);
        return "/member/signUp";
    }

    @ResponseBody
    @PostMapping(value = "/member/dupEmail")
    public String dupEmail(@RequestBody MemberForm memberForm) {
        String message = "";
        Optional<Member> findEmailResult = memberService.findByEmail(memberForm.getEmail());
        if(findEmailResult.isPresent()) {
            if(findEmailResult.get().getEmail().equals(memberForm.getEmail())) {
                message = "이미 사용중인 이메일";
            }
        } else {
            message = "사용 가능한 이메일";
        }
        return message;
    }

    @GetMapping(value = "/member/signUp")
    public String signUp(@ModelAttribute("memberForm") MemberForm memberForm) {
        return "/members/signUp";
    }

    @ResponseBody
    @PostMapping(value = "/member/signUp")
    public String signUp(@RequestBody @Valid MemberForm memberForm, Model model) {
        Optional<Member> findMember = memberService.findByEmail(memberForm.getEmail());
        String message = "";
        if(findMember.isPresent()) {
            if(findMember.get().getEmail().equals(memberForm.getEmail())) {
                if(findMember.get().getPwd().equals(memberForm.getPwd())) {
                    model.addAttribute("email", findMember.get().getEmail());
                    model.addAttribute("name", findMember.get().getName());
                    message = "email : " + findMember.get().getEmail() + ", name : " + findMember.get().getName();
                } else {
                    message = "비밀번호가 틀렸습니다.";
                }
            }
        } else {
            message = "존재하지 않는 이메일입니다.";
        }
        return message;
    }

}
