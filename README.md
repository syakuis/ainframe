# AIN-FRAME (가칭)

개발된 모든 프로그램은 재사용되어야 하고 확장되어야 하며 유지보수가 쉬워야 한다. 그리고 프로그램끼리 상호작용 될 수 있어야 한다. 그렇기 위해 개별 프로그램은 독립적으로 구동되어야 하며, 단일 책임의 원칙으로 개발되어야 한다.

가칭은 프로그램을 모듈이라 하며 모듈간의 상호작용을 위해 공유 데이터를 제공한다. 독립적으로 구동될 수 있도록 개발 가이드라인을 제시한다. 또한 필수적으로 사용되는 모듈을 빌트인하여 제공한다.

## 빌트인 모듈
- 사용자 관리
- 권한 관리
- 모듈 관리
- 레이아웃 관리
- 메뉴 관리
- 관리자


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