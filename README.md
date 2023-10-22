# 🧑‍💻 QCARD - "슬로건"

[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FQ-CARD%2FQCard-server&count_bg=%234282FF&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)

## 🥳 백엔드 팀원 소개

<table border="1" cellspacing="0" cellpadding="0" width="90%">
    <tr width="100%">
        <td width="30%" align="center"><a href= "https://github.com/siyeonkm">김시연</a></td>
        <td width="30%" align="center"><a href= "https://github.com/sunnyineverywhere">이선의</a></td>
    </tr>
    <tr width="100%">
        <td width="30%" align="center"><img src = "https://github.com/siyeonkm.png" width="80%"/></td>
        <td width="30%" align="center"><img src = "https://github.com/sunnyineverywhere.png" width="80%"/></td>
    </tr>
    <tr width="100%">
        <td width="30%" align="center">
            [유저] <br>
            [답변] <br>
            [좋아요] <br>
        </td>
        <td width="30%" align="center">
            [질문] <br>
            [면접] <br>
            [공통] <br>
        </td>
   </tr>
</table>
<br>

## 👋 서비스 소개
```**QCARD**는 소개소개```

<br>

## ⚙️ 기술 스택    
- DEVELOP &nbsp; 
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=round-square&logo=Spring&logoColor=white) <img src="https://img.shields.io/badge/MongoDB-47A248?style=flat-square&logo=MongoDB&logoColor=white"/> <img src="https://img.shields.io/badge/Google-4285F4?style=flat-square&logo=Google&logoColor=white"/> <img src="https://img.shields.io/badge/Stomp-010101?style=flat-square&logo=Stomp&logoColor=white"/> <img src="https://img.shields.io/badge/Websocket-010101?style=flat-square&logo=Websocket&logoColor=white"/> <img src="https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=Redis&logoColor=white"/>

- INFRA &nbsp;
<img src="https://img.shields.io/badge/Amazon CodeDeploy-7D9B4B?style=flat-square&logo=Amazon CodeDeploy&logoColor=white"/> <img src="https://img.shields.io/badge/Travis CI-3EAAAF?style=flat-square&logo=Travis CI&logoColor=white"/>


- ETC &nbsp; 
<img src="https://img.shields.io/badge/GitHub -181717?style=flat-square&logo=GitHub&logoColor=white"/> <img src="https://img.shields.io/badge/GitHub Actions-256EE0?style=flat-square&logo=GitHub Actions&logoColor=white"/></br>

<br>

## 🗂️ 프로젝트 구조

### ERD
<img width="765" alt="image" src="https://github.com/Q-CARD/QCard-server/assets/68330823/8e3f0396-332a-4681-a1cc-6dd73130750f">


### 폴더 
<pre>
<code>
└── 🗂 main
    ├── 🗂 java
    │   └── 🗂 web
    │       └── 🗂 slack
    │           ├── 📑 SlackCloneProjectApplication.java
    │           ├── 🗂 config
    │           │   ├── 🗂 annotation
    │           │   │   └── 📑 AuthMember.java
    │           │   ├── 🗂 handlers
    │           │   │   └── 📑 ChatPreHandler.java
    │           │   │   └── 📑 CustomLoginSuccessHandler.java
    │           │   │   └── 📑 CustomLogoutSucessHandler.java
    │           │   ├── 🗂 jwt
    │           │   │   └── 📑 JwtAuthenticationFilter.java
    │           │   │   └── 📑 JwtTokenProvider.java
    │           │   ├── 📑 AuthMemberArgumentResolver.java
    │           │   ├── 📑 CorsConfig.java
    │           │   ├── 📑 WebConfig.java
    │           │   ├── 📑 WebSocketConfig.java
    │           │   └── 📑 SecurityConfig.java
    │           ├── 🗂 controller
    │           │   ├── 🗂 dto - 생략
    │           │   ├── 📑 ChatroomController.java
    │           │   ├── 📑 ChatTestController.java
    │           │   ├── 📑 EmailController.java
    │           │   ├── 📑 MemberController.java
    │           │   ├── 📑 MessageController.java
    │           │   ├── 📑 ProfileController.java
    │           │   └── 📑 WorkspaceController.java
    │           ├── 🗂 domain
    │           │   ├── 📑 BodyMessage.java
    │           │   ├── 📑 Chatroom.java
    │           │   ├── 📑 ChatroomType.java
    │           │   ├── 📑 EmailToken.java
    │           │   ├── 📑 GoogleCode.java
    │           │   ├── 📑 Member.java
    │           │   ├── 📑 Message.java
    │           │   ├── 📑 MessageType.java
    │           │   ├── 📑 Profile.java
    │           │   ├── 📑 Role.java
    │           │   └── 📑 Workspace.java
    │           ├── 🗂 repository
    │           │   ├── 📑 ChatroomRepository.java
    │           │   ├── 📑 EmailTokenRepository.java
    │           │   ├── 📑 GoogleCodeRepsoitory.java
    │           │   ├── 📑 MemberInviteRepository.java
    │           │   ├── 📑 MemberRepository.java
    │           │   ├── 📑 MessageRepository.java
    │           │   ├── 📑 ProfileRepository.java
    │           │   └── 📑 WorkspaceRepository.java
    │           ├── 🗂 service
    │           │   ├── 📑 ChatroomService.java
    │           │   ├── 📑 CustomOauth2UserService.java
    │           │   ├── 📑 EmailService.java
    │           │   ├── 📑 EmailHandler.java
    │           │   ├── 📑 MemberService.java
    │           │   ├── 📑 MessageService.java
    │           │   ├── 📑 ProfileService.java
    │           │   ├── 📑 RedisService.java
    │           │   └── 📑 WorkspaceService.java
    │           ├── 🗂 util
    │           │   ├── 📑 ErrorResponse.java
    │           │   ├── 📑 GlobalExceptionHandler.java
    │           │   ├── 📑 ResponseMessage.java
    │           └── └── 📑 StatusEnum.java
    └── 🗂 resources
        ├── 🗂 templates
        ├── 📑 application.properties
        └── 📑 application-oauth.properties
</code>
</pre>


