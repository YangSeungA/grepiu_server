Springboot 2.0 Framework<br>
##프레임웍 구현<br>
MVC Web<br>
Freemarker<br>
Mybatis<br>
MongoDB connect<br>
MariaDB connect</br>

History
<pre>
1. Spring Oauth => Spring cloud 변경
=> Oauth 적용시 GlobalAuthenticationConfigurerAdapter 에러 발생으로 변경
</pre>

#구동 전 필수 세팅 <br>
###<b>1. Mongo DB 설치</b><br>
ref.https://docs.mongodb.com/manual/tutorial/install-mongodb-on-red-hat<br>
###2. DB 유저 등록<br>
step1. 몽고 DB 설치<br>
step2. mongod 실행<br>
<pre>
mongod
</pre>
step3. mongo shell 시작<br>
<pre>
mongo 엔터
use study 엔터
</pre>
step4. 유저 등록<br>
<pre>
관지자 계정
use admin
db.createUser( { user: "<username>",
           pwd: "<password>",
           roles: [ "root" ]
})
ex) db.createUser( { user: "iukim21c",pwd: "test1!", roles: [ "root" ]});
use grepiu
db.createUser( { user: "<username>",
           pwd: "<password>",
           roles: [ "readWrite"  ] 
})
ex)
use grepiu
db.createUser( { user: "iukim21c", pwd: "xptmxm1!", roles: [ { role: "readWrite", db: "grepiu" } ] })
</pre>
step4. 유저등록 후 몽고 서비스 재기동<br>
<pre>
mongod --auth
</pre>
##Swagger UI 적용
<pre>
[host]:[port]/swagger-ui.html
</pre>

##WebSocket 적용 - STOMP
<pre>
[host]:[port]/ws
</pre>

##Spring Security 적용
 <pre>
[host]:[port]/login
</pre>
##구조
<pre>
test
   java 
      com.grepiu.test
          process   - 업무용 TDD
          [*] - 일반적인 샘플러
src
   main
      java
         com.grepiu.www
               process
                   api -    공통 API 
                            controller  - 컨트롤러
                            dao - repository 
                            domain - 도메인 
                            service - 서비스
                   common - 공통기능[global use]
                            config - 설정
                            helper - helper 클레스
                            job - 배치
                            tools  - 도구
                                  crawler - 크롤러
                                  task - 프로세스 유틸
                            util - 유틸
                   api -    api /{ver}/기능
                            controller  - 컨트롤러
                            dao - repository 
                            domain - 도메인 
                            service - 서비스
                    grepiu - grepiu API
                            controller  - 컨트롤러
                            dao - repository 
                            domain - 도메인 
                            service - 서비스
                    sample - 기능 샘플[참조]
                            controller - 컨트롤러
                            dao - repository
                            domain - 도메인
                            service - service
      resources
          config - 설정 파일 저장
          mappers  - mybatis xml
          static   - 정적파일
          templates - 어플리케이션 템플릿[freemarker]
          application.yml  - springboot propertifile
          lombok.config - lombok config file
      build.gradle - gradle build 파일         
</pre>

##build&execute
Windows<br>
gradlew build && java -jar build/libs/springboot-0.0.1-SNAPSHOT.jar<br>
centos-local<br>
gradle build && java -jar build/libs/springboot-0.0.1-SNAPSHOT.jar<br>
centos-prod<br>
gradle build -Pprofile=prod && java -jar build/libs/springboot-0.0.1-SNAPSHOT.jar<br>

##참조
https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-structuring-your-code.html
<br>properties 설정 참조</br>
https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html