# 프로젝트 소개

## 프로젝트 명: NOT TO DO LIST ❎⭕

### 깃헙주소: [https://github.com/Invidam/not-todo-list](https://github.com/Invidam/not-todo-list)

### API 문서: [https://documenter.getpostman.com/view/18176061/UzBsJjoU](https://documenter.getpostman.com/view/18176061/UzBsJjoU)

## 프로젝트 설명

- NOT TO DO LIST는 **하지 말아야 것들**을 목표로 설정하고 이를 달성하지 못했을 시 ❎ → ⭕표시로 변경 함.
    - 예시 목표
        - 야식 먹지 않기
        - 담배 끊기
        - 늦게 잠들지 않기
        - 과소비하지 않기
        - 등
- 기타 내용
    
    ## 프로젝트 특징
    
    - 고치고 싶은 습관을 개선하는데 도움을 줌
    - 거절하는 습관을 들일 수 있음
    - 시간 절약에 도움을 줌
    
    ### 참고 자료들
    
    - [https://www.spica.com/blog/not-to-do-list#:~:text=A not-to-do list,on your to-do list](https://www.spica.com/blog/not-to-do-list#:~:text=A%20not%2Dto%2Ddo%20list,on%20your%20to%2Ddo%20list).
    - [https://brunch.co.kr/@yooncohg/55](https://brunch.co.kr/@yooncohg/55)
    

## 프로젝트 기능

- 유저
    - ✅ CRUD
    - ✅ 로그인/로그아웃
- Todo Item
    - ✅ CRUD
    - ✅ ❎⭕  표시 기능
- ✅타 사용자의 목표들을 확인 가능
    - ✅정해진 감정표현을 이용하여 표현 가능 (좋아요 싫어요와 유사)
    - ❌자신의 것으로 복사 가능 (공유 기능)
- ✅월 별로 어느 사용자가 목표를 잘 달성했는지 순위를 표현함
- ✅해시태그 기능
    - ❌해시태그 검색기능
- ❌이름을 통한 검색

# 프로젝트 설계

[데이터베이스 설계]
<details>
<summary>데이터베이스 설계</summary>
<div markdown="1">

    # 데이터베이스 설계

![Untitled](%E1%84%83%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%90%E1%85%A5%E1%84%87%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%89%E1%85%B3%20%E1%84%89%E1%85%A5%E1%86%AF%E1%84%80%E1%85%A8%2089d2b93bf0b548e191a50b5e28b92315/Untitled.png)

💜타

1. 탈취
2. 기존 사용자가 재갱신 (acc, ref)
3. 

## user

| Column Name | Data Type | 연관 관계 |
| --- | --- | --- |
| id | INT |  |
| account | VARCHAR(255) |  |
| password | VARCHAR(255) |  |
| nickname | VARCHAR(255) |  |
| refresh_token | VARCHAR(255) |  |
| created_at | DATETIME |  |
| is_deleted | TINY INT |  |

## item_emotion

| Column Name | Data Type | 연관 관계 |
| --- | --- | --- |
| item_id | INT | item.id |
| emotion_id | SMALLINT |  |
| user_id | INT | user.id |

## item

| Column Name | Data Type | 연관 관계 |
| --- | --- | --- |
| id | INT |  |
| user_id | VARCHAR(255) | user.id |
| title | VARCHAR(255) |  |
| priority | TINYINT |  |
| created_at | DATETIME |  |
| edited_at | DATETIME |  |
| deadline | DATETIME |  |
| description | TEXT |  |
| is_done | TINYINT |  |
| is_edited | TINYINT |  |
| is_shared | TINYINT |  |
| is_deleted | TINYINT |  |

## hash_tag

| Column Name | Data Type | 연관 관계 |
| --- | --- | --- |
| id | INT |  |
| hash_tag_name | VARCHAR(255) |  |

## item_hash_tag

| Column Name | Data Type | 연관 관계 |
| --- | --- | --- |
| item_id | INT | item.id |
| hash_tag_id | SMALLINT | hash_tag.id |

</div>
</details>

[기술 설명]
<details>
<summary>기술 설명</summary>
<div markdown="1">

    # 기술 설명

- Java
    - JDK: `1.8`
- Spring
    - mvc: `4.3.18.RELEASE`
    - secrurity: `5.7.1`
- Mysql
    - `8.0.28`
- Mybatis
    - `3.2.2`

</div>
</details>
