# OpenSource_SpecialtyProject

CBNU_WooNam 김동용, 김지원, 박재원, 최성운

## SW전공 프로젝트 팀원 찾기 서비스

## 간단한 git 메뉴얼

- master 브랜치에 있는 코드를 클론하여 내 컴퓨터 저장소에 가져온다.<br>

```Linux
$ git clone https://github.com/kingyong9169/CBNU_WooNam.git
```

- 브랜치 생성
  "git branch [브랜치 이름]" 을 해도 되지만 아래 방법을 추천
  ※브랜치 이름은 Kebab 표현법으로 하도록 하자 ( ex main-page, front-color )

```Linux
$ git checkout -b [브랜치 이름]
```

- git status<br>
  항상 add, commit 전에는
  ```Linux
   git status
  ```
  위 코드로 자신이 수정한 파일의 상태를 확인하여 맞다면 아래 사항들을 진행할 것

* git add
  "git add ." 대신에 어떤 일의 단위로 묶기 ( 나중에 comment 달 때에 힘들어지기 때문에 )
  ex) 내가 main page의 버튼의 style을 바꾸는 작업을 하면서 style.html과 MainActivity를 건드렸다면
  아래 예시와 같이 2개만 add하고 commit하는 것이 다른 사람들이 보기에도 편해진다

```Linux
$ git add style.html MainActivity
```

- git commit
  commit은 위에서 말한 일의 단위로 묶어서 바로 commit을 날려주는 것이 좋다. 그리고 만약에 issue에 묶을 때는 아래 예시와 같이 하면 된다.
  ※commit message는 영어로 해도 되지만 우리끼리 보고 빠르게 피드백하기위해 한글을 권장

```Linux
$ git commit -m "#[이슈번호] 메인 페이지의 버튼 style을 변경"
```

- git push
  현재 master브랜치는 테스트와 작업이 완료된 즉 바로 배포해도 괜찮은 파일들만이 존재하는 곳이다. 그러니까 **master가 아닌 따로 브랜치를 만들어서 pull request를 날리거나** 급한 상황이면 모든 팀원들에게 말하고 master 브랜치에 push하도록 한다.

```Linux
$ git push origin [자신의 브랜치]
```

- pull request (pr) 보내기
  본인의 브랜치에 push했다면 github 페이지에서 자신의 branch에 들어갔을 시에 상단에 초록색 pull request 박스가 보일 것이다. 그러면 그것을 클릭하고 message 만들어서 git pull request하면 된다. 그리고 팀원들에게 merge할 것을 얘기하고 merge를 진행하면 된다. 현재 자신이 맡은 일이 마무리가 되었다고 판단되면 merge 시에 기존 브랜치를 삭제한다.
  ※[pull request message 튜토리얼](https://www.pullrequest.com/blog/writing-a-great-pull-request-description/)

# ChattingApp

Hello everyone, this is my first ever Android App which runs on Firebase Realtime Database.
Some of the features are written below, don't forget to put the Star if you like it.

### Download App : <a href="/">Click Here</a>

## Features

1. **Register using Email ( contains Name, Username, Email, Password )**
2. **Login using Email & Password**
3. **Autologin**
4. **Forget Password Option ( using Firebase Password Resest function )**
5. **Edit your profile section**
6. **Update your About**
7. **Send message to a user**
8. **Online and Offline of Users whom you have chatted**
9. **Search User by thier username**
10. **Check users profile**
11. **Message Deliver and Seen feature**
12. **Logout**

Things which are not implemented in this app :

- Delete Chat
- Block User
- Notification
- Timestamp

## Screenshots

| Main Page Before Login                                                                                    | Register View                                                                                                 | Forget Password View                                                                                               |
| --------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------ |
| <img src = "https://github.com/mr0kaushik/ChattingApp/blob/master/Screenshots/MainView.jpg" width="300" > | <img src = "https://github.com/mr0kaushik/ChattingApp/blob/master/Screenshots/RegisterView.jpg" width="300" > | <img src = "https://github.com/mr0kaushik/ChattingApp/blob/master/Screenshots/ForgetPasswordView.jpg" width="300"> |

| Main Page After Login                                                                                                 | Users View                                                                                                            | Message View                                                                                                |
| --------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| <img src = "https://github.com/mr0kaushik/ChattingApp/blob/master/Screenshots/MainChatActivityChat.jpg" width="300" > | <img src = "https://github.com/mr0kaushik/ChattingApp/blob/master/Screenshots/MainChatActivityUser.jpg" width="300" > | <img src = "https://github.com/mr0kaushik/ChattingApp/blob/master/Screenshots/MessageArea.jpg" width="300"> |

| Profile View                                                                                                     | User Profile View                                                                                                |
| ---------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------- |
| <img src = "https://github.com/mr0kaushik/ChattingApp/blob/master/Screenshots/ProfileSection.jpg" width="300"  > | <img src = "https://github.com/mr0kaushik/ChattingApp/blob/master/Screenshots/UserProfileView.jpg" width="300" > |
