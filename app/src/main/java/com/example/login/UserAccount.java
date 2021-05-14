package com.example.login;

/*
사용자 계정 정보 모델 클래스
여기에 프로필등 추가할수있다.
여기모델에서 확장해서 관리
 */
public class UserAccount
{
    private String idToken;//firebase Uid (고유 토큰정보)
    private String emailId;//이메일 아이디
    private String password;//비밀번호

    public UserAccount()//파이어베이스슬때 꼭 빈생성자 가져와야 오류가안남 알트 + 인서트
    {

    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
