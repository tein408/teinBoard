package kr.teinboard.teinboard.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(length = 15)
    private String name;

    private String pwd;

    @Column(length = 15)
    private String auth;

    public Member(String email, String name, String pwd, String auth) {
        this.email = email;
        this.name = name;
        this.pwd = pwd;
        this.auth = "ROLE_"+auth;
    }

}
