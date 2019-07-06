# wmp-test
## 개발환경
* Java 1.8
* Spring Boot 2.1.6.RELEASE
* Gradle
* Vue.js
   
## 시작 방법
Start Bootrun
```
./gradlew bootRun
```

npm 의존성 설치 및 Vue 시작c
```
cd client
npm install
npm start
```

http://localhost:8081/#/
Swagger: http://localhost:8080/swagger-ui.html
   
## 입력 
* url
* html tag 포함 옵션
* 출력 묶음 단위 (자연수)
    
## 구현 기능
* url, html tag 포함 여부, 출력 묶음 단위를 입력받는다
* url로 해당 사이트를 파싱 해온다
    * url 패턴 예외처리를 한다
* html 태그 제외 옵션 true일 경우에 대해서 태그를 제거한다
* 영어와 숫자만 필터한다
* 오름차순으로 정렬한다(ex. AaBbCc)
* 영어, 숫자 교차출력 한다(ex. A0a1B2b3C3c)
* 출력 묶음 단위로 묶는다 (몫, 나머지)
