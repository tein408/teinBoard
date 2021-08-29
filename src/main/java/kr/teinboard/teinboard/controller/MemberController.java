package kr.teinboard.teinboard.controller;

import kr.teinboard.teinboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/members/new")
    public String createMemberForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "/members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String createMember(@RequestBody @Valid MemberForm memberForm, BindingResult result) {
        //BindingResult -> name field에 대해 에러를 뽑아준다.
        if(result.hasErrors()){
            return "/members/createMemberForm";
        }
        memberService.saveMember(memberForm);
        return "/members/signUp";
    }

    @ResponseBody
    @PostMapping(value = "/members/dupEmail")
    public String dupEmail (@RequestBody MemberForm memberForm) {
        String message;
        boolean findEmailResult = memberService.findByEmail(memberForm.getEmail());
        if(findEmailResult) {
            message = "이미 사용중인 이메일";
        } else {
            message = "사용 가능한 이메일";
        }
        return message;
    }

}
