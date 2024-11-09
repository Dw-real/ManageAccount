# ManageAccount

## 프로젝트 개요
간단한 계정 관리 API 프로젝트입니다.

## 주요 기능
- 회원 가입
- id 중복 체크
- 로그인
- 로그아웃
- 아이디 찾기
- 비밀번호 찾기
- 비밀번호 변경
- 계정 탈퇴

## 회원 가입 API

회원 가입 성공(/accounts)

요청 바디

```json
{
    "name": "user1",
    "id": "user1234",
    "pwd": "123456",
    "birthDate": "20000301",
    "gender": "남자",
    "phoneNumber": "010-2222-2222",
    "email": "user1@naver.com"
}
```

응답 바디

```json
{
    "name": "user1",
    "id": "user1234",
    "pwd": "123456",
    "birthDate": "20000301",
    "gender": "남자",
    "phoneNumber": "010-2222-2222",
    "email": "user1@naver.com"
}
```

회원 가입 실패(중복 id)

요청 바디

```json
{
    "name": "user2",
    "id" : "user1234",
    "pwd" : "12345",
    "birthDate" : "20000302",
    "gender" : "남자",
    "phoneNumber" : "010-3333-3333",
    "email" : "user2@naver.com"
}
```

응답 바디(상태 코드 400)

```json
{
    "errors": [
        "이미 존재하는 ID입니다."
    ]
}
```

## id 중복 체크 API

중복 체크 성공 (/accounts/id-check?id=???)

요청 바디

```json
{
    "id": "user1235"
}
```

응답 바디

```text
  ok
```

중복 체크 실패

요청 바디

```json
{
    "id" : "user1234"
}
```

응답 바디

```text
  no
```

## 로그인 API

로그인 성공(/accounts/login)

요청 바디

```json
{
    "id": "user1234",
    "pwd": "123456"
}
```

응답 바디

```json
{
    "name": "user1",
    "id": "user1234",
    "pwd": "123456",
    "birthDate": "20000301",
    "gender": "남자",
    "phoneNumber": "010-2222-2222",
    "email": "user1@naver.com"
}
```

로그인 실패(등록되지 않은 id)

요청 바디

```json
{
    "id" : "user123",
    "pwd" : "123456"
}
```

응답 바디(상태 코드 401)

```text
해당 id로 가입된 정보가 없습니다.
```

로그인 실패(잘못된 비밀번호)

요청 바디

```json
{
    "id" : "user1234",
    "pwd" : "1234567"
}
```

응답 바디(상태 코드 401)

```text
비밀번호가 일치하지 않습니다.
```

## 아이디 찾기 API

아이디 찾기 성공(/accounts/{name}/{phoneNumber})

요청 바디

```json
{
    "name": "user1",
    "phoneNumber": "010-2222-2222"
}
```

응답 바디

```json
{
    "id": "user1234"
}
```

아이디 찾기 실패(등록되지 않은 정보)

요청 바디

```json
{
    "name": "user2",
    "phoneNumber": "010-2222-2222"
}
```

응답 바디(상태코드 404)

```json
{
    "errors": [
        "해당 정보로 가입된 id가 없습니다."
    ]
}
```

## 비밀번호 찾기 API

비밀번호 찾기 성공(/accounts/{id})

요청 바디

```json
{
   "id" : "user1234"
}
```

응답 바디

```json
{
    "name": "user1",
    "id": "user1234",
    "pwd": "123456",
    "birthDate": "20000301",
    "gender": "남자",
    "phoneNumber": "010-2222-2222",
    "email": "user1@naver.com"
}
```

비밀번호 찾기 실패(등록되지 않은 id)

요청 바디

```json
{
    "id": "user123"
}
```

응답 바디(상태코드 404)

```json
{
    "errors": [
        "해당 id로 가입된 정보가 없습니다."
    ]
}
```

## 비밀번호 변경 API

비밀번호 변경 성공(/accounts/{id}) 

요청 바디

```json
{
    "currentPwd" : "123456",
    "newPwd" : "1234567"
}
```

응답 바디

```json
{
    "name": "user1",
    "id": "user1234",
    "pwd": "1234567",
    "birthDate": "20000301",
    "gender": "남자",
    "phoneNumber": "010-2222-2222",
    "email": "user1@naver.com"
}
```

비밀번호 변경 실패(잘못된 현재 비밀번호)

요청 바디

```json
{
    "currentPwd" : "12345",
    "newPwd" : "1234567"
}
```

응답 바디(상태 코드 400)

```json
{
    "errors": [
        "현재 비밀번호가 일치하지 않습니다."
    ]
}
```
