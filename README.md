# 일정관리 프로그램
: 일정을 CRUD 할 수 있는 캘린더 기능 구현


**Level 0**
- API 명세서 작성
- ERD 작성
- SQL 작성


**Level 1**
[일정 생성 및 조회]
- 일정 생성 (할일, 작성자명, 비밀번호, 작성/수정일을 저장, 각 일정의 고유 식별자(id)를 자동으로 생성하여 관리)
- 전체 일정 조회 (다음 조건을 바탕으로 등록된 일정을 전체 조회 후 수정일 기준 내림차순 정렬, 수정일(형식 YYYY-MM-DD), 작성자명, 조건 없음)
- 선택 일정 조회 (id 사용해 선택한 일정의 단건 정보 조회)


**Level 2 (미완성)**
[일정 수정 및 삭제]
- 일정 수정 (선택한 일정 내용 중 할일, 작성자명만 수정 가능, 비밀번호를 함께 전달해야함, 작성일은 변경할 수 없고 수정일은 수정완료 시 그 시점으로 변경됨)
- 선택한 일정 삭제 (선택한 일정을 삭제, 삭제 요청시 비밀번호 함께 전달)



### ⭐️ 책임 분리
- Controller : 요청, 응답
- Service : 비즈니스 로직
- Repository : 데이터베이스와 상호작용
- DTO : Layer 간 데이터 전달


### ⭐️ API 명세 및 ERD

**API 명세서**
https://documenter.getpostman.com/view/44720808/2sB2qUmQ66


**ERD**

<img width="391" alt="image" src="https://github.com/user-attachments/assets/148d3b85-063f-458d-b4fe-bee4242bd4e6" />


### ⭐️ 요구사항 분석
<img width="607" alt="image" src="https://github.com/user-attachments/assets/5cf1cf01-34ec-4757-b71c-4d703f8cbf38" />
<img width="792" alt="image" src="https://github.com/user-attachments/assets/8810375d-6bef-4434-951b-81f2b7a2783e" />


