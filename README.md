# teinBoard
Spring Boot Project


## 개발 목적
- Web 백엔드의 보편적인 기술 중 하나인 Spring boot의 기본기를 다지기 위함입니다.

## 개발 환경
- Java 11
- Spring boot 2.5.3, Spring Data JPA 2.5.3
- H2 database 1.4.200
- gradle 1.0.11
- thymeleaf 3.0.12
- bootstrap 5.0.2

## 문제 해결
<details>
  <summary>데이터 타입 불일치로 인한 문제</summary>
  <div markdown="1">

- 회원 가입 시 입력한 데이터를 서버에 전송하는 과정에서 Error 발생 
  1. 테스트
     - postman : JSON 타입 전송 -> 정상
     - 실 구동 : JSON 타입으로 변환하지 않아 실패
  2. 문제
     - `MemberController` : JSON 타입으로 받도록 설정
     - 화면 : Param 전송
  3. 원인
     - `MemberController`에서 JSON 타입을 받기 위해 사용된 `@RequestBody` 어노테이션
     - `@RequestBody` 어노테이션은 JSON 타입으로 데이터를 주고 받기 위해 설정한 것
  4. 해결
     - `Controller`와 화면에서 주고 받는 데이터 타입 일치시켜야 한다.
     - 화면에서 데이터 전송시 JSON 타입으로 전송하도록 설정

  </div>
</details>

## 기타
- 소스 코드 버전 관리를 위해 git을 사용합니다.
    - 보다 좋은 commit message를 남기기 위해 [Udacity Git Commit Message Style Guide](https://udacity.github.io/git-styleguide/) 를 참조하였습니다.