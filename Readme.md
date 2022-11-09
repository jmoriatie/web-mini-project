
> # 개인 웹 미니 프로젝트
> ### (최초) 매장 구입 목록 관리용

#### 도구: spring boot / IntelliJ 
> 버전정보
* Spring boot
* Jpa
* MySQL
* H2
* Gradle
* spring security (+jwt) 적용 예정
* Swagger or Restdocs 적용 예정
---

#### [프로젝트 설명]
* 매장구입 목록 관리용 웹프로그램 미니 프로젝트

#### [개발 및 리팩토링 로그]
:o: 유저, 아이템 CRUD Controller, domian 개발  
:o: 로컬 Repository, Service 객체 개발  
:o: Item Controller 검증로직 개발 :arrow_forward: Validator 객체 개발 및 사용    
:o: Domain 및 Dto 에 Spring Validation 사용(고도화)를 위한 검증 어노테이션 추가  
:o: Item Controller 검증로직 개발 :arrow_forward: @Validated 어노테이션 및 bindingResult 사용  
:o: 세션 검증 추가  
:o: Spring Interceptor 추가 :arrow_forward: 세션검증 인터셉터, 로그 인터셉터  
:o: 아이템 마지막 수정일자 적용을 위한 item 도메인 수정(마지막 주문일자)  
:o: 금액, 날짜 formatter 적용  
:o: @RestControllerAdvice 어노테이션 활용 ItemRestController 검증 로직 추가  
:o: itemList 화면, 검색 기능 추가  
:o: itemList 화면, 페이지 넘버링 추가 :arrow_forward: 아이템 최초 출력 및 검색 시  
:o: 비동기 자체 API 호출 형태로 itemList 화면출력 및 검색 수정  
:o: 메세지 공통관리를 위한 message.properties 생성 및 적용  
:o: Dto :arrow_forward: Entity 전환의 코드 축소를 위해 각 Convertor 객체 개발  
:o: Repository 변경 :arrow_forward: JPA(MySQL)  
:o: @Builder 어노테이션 활용한 Entity 및 Dto 패턴 수정  
:o: Service 레이어 Test case 작성  
:o: Spring security 사용을 위해 도메인 객체 변경 (User :arrow_forward: Account)  
---
:x: Test DB H2로 변경    
:x: Spring security 적용   
:x: 로그인 관련 API 개발  
:x: 로그인 및 아이템 관련 jwt 적용  
:x: Mokito framework를 활용한 Test case 작성  
:x: Swagger or Restdocs 적용