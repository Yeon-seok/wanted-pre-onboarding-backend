# WANTED 사전과제
## 성연석

1.채용공고 등록 201 Created 409 Conmflict 500 에러 발생
2.채용공고 수정 200 OK 404 Not Found
3.채용공고 삭제 200 OK 404 Not Found
4.채용공고 목록 200 OK 404 Not Found
4-1. 채용공고 검색 기능 구현 200 OK 404 Not Found
5.채용 상세 페이지 200 OK 404 Not Found
6.채용공고에 지원  200 OK 409 Conflict

---

## 요구 사항

### 1.채용공고 등록
### 2.채용공고 수정
### 3.채용공고 삭제
### 4.채용공고 목록
### 4-1. 채용공고 검색 기능 구현
### 5.채용 상세 페이지
### 6.채용공고에 지원


---




## 0.미리 구현

InitService에 1명의 유저와 2명의 회사를 미리 등록



## 1.채용공고 등록

### Request

`POST /wanted`

    curl -i -H 'Accept: application/json' http://localhost:8080/wanted

    {
	  "id" : 1,
	  "position" : "백엔드 주니어 개발자",
	  "money" : 1000000,
	  "content" : "원티드에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
	  "techStack" : "spring"
	}

### Response

	{
	    "code": "201 CREATED",
	    "msg": "채용공고 등록 완료",
	    "data": null
	}


### etc
등록 되지 않은 회사는 Exception이 발생하도록 구현

    {
        "code": "404 NOT_FOUND",
        "msg": "해당하는 회사는 등록되지 않은 회사입니다.",
        "data": null
    }




## 2.채용공고 수정

### Request

`PUT /wanted/{recruitment_id}`

    curl -i -H 'Accept: application/json' http://localhost:8080/wanted/1

	{
	  "position" : "프론트엔드 주니어 개발자",
	  "money" : 1000000,
	  "content" : "원티드에서 프론트엔드 주니어 개발자를 채용합니다. 자격요건은..",
	  "techStack" : "react"
	}

### Response

	{
	    "code": "200 OK",
	    "msg": "채용공고 수정 완료",
	    "data": null
	}

### etc
등록 되지 채용 공고에 접근 했을 경우 Exception 발생

    {
        "code": "404 NOT_FOUND",
        "msg": "해당하는 채용 공고는 없는 공고입니다.",
        "data": null
    }



## 3.채용공고 삭제

### Request

`DELETE /wanted/{recruitment_id}`

    curl -i -H 'Accept: application/json' http://localhost:8080/wanted/3

### Response

	{
	    "code": "200 OK",
	    "msg": "채용공고 삭제 완료",
	    "data": null
	}

### etc
등록 되지 채용 공고에 접근 했을 경우 Exception 발생
    {
        "code": "404 NOT_FOUND",
        "msg": "해당하는 채용 공고는 없는 공고입니다.",
        "data": null
    }


## 4.채용공고 목록

### Request

`GET /wanted`

    curl -i -H 'Accept: application/json' http://localhost:8080/wanted

### Response

	{
	    "code": "200 OK",
	    "msg": "채용공고 목록 불러오기 완료",
	    "data": [
	        {
	            "id": 1,
	            "position": "프론트엔드 주니어 개발자",
	            "money": 1000000,
	            "techStack": "react",
	            "companyRegin": "서울",
	            "companyName": "원티드",
	            "companyNation": "대한민국"
	        },
	        {
	            "id": 2,
	            "position": "백엔드 주니어 개발자",
	            "money": 1000000,
	            "techStack": "spring",
	            "companyRegin": "판교",
	            "companyName": "네이버",
	            "companyNation": "대한민국"
	        },
	        {
	            "id": 4,
	            "position": "프론트엔드 주니어 개발자",
	            "money": 1000000,
	            "techStack": "react",
	            "companyRegin": "서울",
	            "companyName": "원티드",
	            "companyNation": "대한민국"
	        }
	    ]
	}

### etc
등록 된 채용 목록이 없을 경우 EXCEPTION 발생

    {
        "code": "404 NOT_FOUND",
        "msg": "채용 목록이 없습니다.",
        "data": null
    }

## 4-1.채용공고 검색 기능

### Request

`GET /wanted/search={word}`

    curl -i -H 'Accept: application/json' http://localhost:8080/wanted/search=백엔드

### Response

    {
        "code": "200 OK",
        "msg": "채용공고 검색 완료",
        "data": [
            {
                "id": 2,
                "position": "백엔드 주니어 개발자",
                "money": 1000000,
                "techStack": "spring",
                "companyRegin": "판교",
                "companyName": "네이버",
                "companyNation": "대한민국"
            }
            ]
    }

### etc

검색 목록이 없을 경우 EXCEPTION 발생

    {
        "code": "404 NOT_FOUND",
        "msg": "검색 목록이 없습니다.",
        "data": null
    }

## 5.채용 상세 페이지

### Request

`GET /wanted/{recruitment_id}`

    curl -i -H 'Accept: application/json' http://localhost:8080/wanted/1

### Response

    {
        "code": "200 OK",
        "msg": "채용공고 상세 페이지 불러오기 완료",
        "data": {
            "id": 1,
            "position": "프론트엔드 주니어 개발자",
            "money": 1000000,
            "techStack": "react",
            "content": "원티드에서 프론트엔드 주니어 개발자를 채용합니다. 자격요건은..",
            "idList": [
                1,
                4
            ],
            "companyRegin": "서울",
            "companyName": "원티드",
            "companyNation": "대한민국"
        }
    }

### etc
해당 하는 채용 공고가 없을 경우 EXCEPTION 발생

    {
        "code": "404 NOT_FOUND",
        "msg": "해당하는 채용 공고는 없는 공고입니다.",
        "data": null
    }

## 6.채용공고에 지원

### Request

`POST /wanted/apply`

    curl -i -H 'Accept: application/json' http://localhost:8080/wanted/apply

### Response

    {
        "code": "200 OK",
        "msg": "채용공고에 지원 완료! ",
        "data": null
    }

### etc
중복 지원을 했을 경우 EXCEPTION 발생

    {
        "code": "409 CONFLICT",
        "msg": "같은 채용공고에 중복 지원을 불가합니다!",
        "data": null
    }