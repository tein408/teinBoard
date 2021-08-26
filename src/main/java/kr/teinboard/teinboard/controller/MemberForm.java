package kr.teinboard.teinboard.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class MemberForm {

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String pwd;

    private String auth;

    public MemberForm() {
    }

    public MemberForm(String email, String name, String pwd, String auth) {
        this.email = email;
        this.name = name;
        this.pwd = pwd;
        this.auth = auth;
    }

}
