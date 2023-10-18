# ZB MINI COURSE

## Dependency

* Device : Macbook Air (M1)
* Platform : Ventura 13.5.1
* SpringBoot : 2.7.17-SNAPSHOT
* Java : JDK11(AWS Corretto)
* Test Tool : Junit5
* Database : h2-2.2.224

## 환경변수

```shell
export DATABASE_URL="your h2 database url"
export DATABASE_USERNAME="your h2 database username"
export DATABASE_PASSWORD="your h2 database password"
```

## 실행

### 환경변수 설정한 경우

```shell
java -jar mini-course.jar
```

### 환경변수 미설정

```shell
java -jar -DDATABASE_URL="yourdbname" -DDATABASE_USERNAME="yourusername" -DDATABASE_PASSWORD="yourpassword" ./build/libs/mini-course-0.0.1-SNAPSHOT.jar    
```

## 1) 참여자 초대

1. 그룹 매니저는 그룹에 참여자를 초대할 수 있다.
2. 회원 초대시 디비에 임시 회원을 생성하고 초대 링크를 생성한다.
3. 생성시 회원의 이름, 이메일, 전화번호 필수

## 2) 초대 수락

1. 초대 받은 사용자는 그룹 참여를 초대 링크를 통해 참여 가능
2. 초대 링크 수락 시 임시 회원을 활성화하고, 초대 링크를 만료
3. 초대 링크는 1회 사용시 만료

# Entity 설계

1. Group ( Name(pk) , User, CraetedDated )
2. GroupUser ( Id(pk), Group, User , level, RegDate )
3. User ( Email(pk), name, phone, IsActivated, RegDate, ActivatedDate )
4. InviteLog ( Link(pk) , User , SentDate, VisitDate )

# Feature

## [POST] 가입/초대 하기 API

- URL : /api/v1/invite
- Request Body :

    ```json
    {
    	"email" : "str|required",
    	"name" : "str|required",
    	"phone" : "int:required",
    	"groupName" : "str|nullable",
    	"groupOwner" : "str|nullable"
    }
    ```

- Description:
    - 가입 : 그룹 필드를 제외하고 요청시, 회원가입 링크를 통해 회원가입을 수행할 수 있습니다.
    - 초대 : 그룹 필드에 그룹명을 넣을시, 그룹장 여부 확인 후 그룹 초대 링크를 생성할 수 있습니다.
    - 만료 : 만료시간 N 분 초과시 초대 링크가 만료됩니다, 1회 방문만 허용됩니다.
    - 재요청에, 만료되어 있다면 링크를 새롭게 만들어 줍니다.
- Response ( 201 ) 최초 성공

    ```json
    {
    	"link" : "/invite/${link}",
    	"SentDate": "YYMMDD-HHMMSS"
    }
    ```

- Response ( 200 ) 재발급 성공

    ```json
    {
    	"link" : "/invite/${link}",
    	"SentDate": "YYMMDD-HHMMSS"
    }
    ```

- Response ( 400 ) 그룹장아님, 이미 정보가 있음, 유효하지 않음

    ```json
    {
    	"message" : "{error}"
    }
    ```

## [GET] 인증 API

- URL : /api/v1/invite/{link}
- Response (400) 만료된 링크, 없는 링크, 이미 방문한 페이지

    ```json
    {
    	"message": "{error}"
    }
    ```

- Response 200 :

    ```json
    {
    	"email": "str",
    	"name" : "str",
    	"phone" : "str"
    }
    ```

- Description :
    - 만료 : 링크가 만료된 경우 에러…
    - 없음 : 링크가 없는 경우 에러..
    - 1회 방문 : 이미 방문한 경우 에러
    - 첫 방문 : 유저 데이터 전송 및 유저계정 활성화

## [POST] 그룹 생성 API

- URL : /api/v1/group
- Request Body

    ```json
    {
    	"name" : "str|required",
    	"user" : "str|required"
    }
    ```

- Response

    ```json
    {
    	"name" : "str",
    	"user" : "str",
    	"CraetedDated" : "str"
    }
    ```