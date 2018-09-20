# AIN-FRAME (가칭)

## 사용한 것들...

- java 7
- spring 4 & spring-boot 1.5
- spring-data-jpa
- ehcache

## 할일

- context
    - [x] config
    - [ ] menu
    - [ ] layout
    - [ ] module
- ModuleViewResolver
    - [ ] Module, Layout Context Handler
    - [ ] ModuleView & ModuleViewResolver
    - [ ] ModuleViewResolver Annotation : AOP
    - [ ] layout
    - [ ] skin
- View UI : Controller
    - [ ] layout
    - [ ] module
    - [ ] config
    - [ ] menu
- Security


## package flow

```
|- spring-boot
  |- ainframe-core
    |- ainframe-cache
    |- ainframe-data-jpa
    |- ainframe-context
      |- ainframe-web-config
      |- ainframe-web-module
      |- ainframe-web-menu
      |- ainframe-web-layout
        |- ainframe-web -> boot.starter 변경
          |- aflow
```

## spring boot properties 전략

**문제**

- apllication.properties 는 같은 경로에 위치할때  중복으로 읽어지지 않는 다. 즉 각기 다른 jar 라이브러리에서 중복된 위치의 프로파일이라면 최종 하나만 읽어진다.
- apllication.yml 은 @PropertySource 에서 사용할 수 없다.

**분석**
- @PropertySource 이용하여 직접 프로퍼티를 읽을 수 있다.
- 아래의 3가지 로드순서에 의해 해결한다. 중복된 키는 아래가 위의 값을 덮어쓰기한다.
  - PropertySource
  - /application.properties
  - /config/application.properties
  - /application-{profile}.properties

**해결**

- yaml 확장자는 사용하지 않는 다.
- 지역 설정은 각 jar 라이브러리 properties 는 PropertySource 선언으로 직접 불러온다. 프로퍼티 경로를 최상위와 ./config 혹은 /config 경로가 아니여야 한다.
- 전역 설정(기본)은 /application.properties 에서 설정한다.
- 테스트시 사용할 프로퍼티는 Profile 을 사용해야 한다.

## spring boot component scan 전략

**문제**

- 멀티 프로젝트로 개발시 서로 다룬 둘이상의 패키지 경로를 스캔할 수 있어야 한다.
- 설정 프로퍼티는 원활한 확장.

**해결**

- @SpringBootApplication 하위 패키지만 읽어들이기 때문에 메인 프로젝트를 제외한 의존 프로젝트는 직접 autoConfiguration 을 구현하여 작동하게 한다.
- 설정 프로퍼티는 메인 프로젝트에 필요한 모든 설정을 추가해야 한다.
