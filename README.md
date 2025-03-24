Spring AI를 이용한 Chat GPT 연동 및 배송지 목록 정렬 예제 
===

## 목표

- Spring AI를 이용하여 Chat GPT와 통신하기 위한 방법을 숙지합니다.
- Spring AI의 ChatClient를 활용하여 배송지 순서를 지정하는 예제를 구현합니다.

## Spring AI의 간단한 구성 요소 살펴보기

### Message Type

#### System Message

> AI가 상황에 맞고 구체적인 응답을 생성할 수 있도록 기본 역할을 정의하는 시스템 메시지(defaultSystem) 설정

#### User Message

> AI에 작업 전달을 위한 User Message 설정 : prompt

---

# TODO

## 프로젝트 환경 설정

- [ ] 필요 의존성 추가
    - [ ] `spring ai` : Chat GPT 연동
    - [ ] `spring-web` : Spring Framework에서 용이한 web 서비스 개발
    - [ ] `lombok` : 보일러 플레이트성 코드의 손쉬운 구현
- [ ] chat GPT 연동을 위한 API Key 발급
    - [OpenAI Key 발급](https://platform.openai.com/docs/overview)

## Step1. ChatClient를 이용한 기본 Chat GPT 연동

- [ ] **Chat GPT 연동을 위한 환경 설정 구성**
    - [ ] 민감한 정보 관리를 위한 .env 구성 및 발급 받은 Open AI Key 등록
    - [ ] .env에 설정한 Open AI Key를 `application.yml`의 `spring.ai.openai.api-key` 항목에 연결

- [ ] **ChatClient Configuration 설정 : ChatClientConfig**
    - [ ] `ChatClient.Builder`를 이용하여 `ChatClient` Bean 등록

- [ ] **ChatClient를 이용한 ChatGPT 연동 Service 구현: BasicChatService**
    - [ ] 사용자 질문(prompt)을 `ChatClient`를 통해 전달하여 ChatGPT의 응답을 반환하는 메서드 구현

- [ ] **ChatClient를 이용한 ChatGPT 연동 Controller 구현 : BasicChatController**
    - [ ] `API endpoint 정의` : `GET /api/chat/basic`
    - [ ] `@RequestParam`을 이용하여 사용자 질문(prompt)을 수신
    - [ ] 수신한 prompt를 `BasicChatService의 메서드에 전달`하여 ChatGPT의 응답 반환

- [ ] **테스트 코드 작성**
    - [ ] `GET /api/chat/basic` API 동작을 검증하는 통합 테스트 작성 및 실행

---

## Step2. 구체적인 답변을 위해 System Message를 이용한 AI 모델의 역할 부여

- [ ] **ChatClient Configuration 설정**
    - [ ] `defaultSystem()`를 사용하여 AI의 역할(role)을 설정한 `ChatClient` Bean 등록

- [ ] **RoleBasedChatClient를 이용한 ChatGPT 연동 Service 구현: RoleBasedChatService**
    - [ ] ChatGPT에 사용자 질문(prompt) 전달을 위한 `User Message` 설정
    - [ ] ChatGPT에 AI의 역할(role) 전달을 위한 `System Message` 설정
    - [ ] AI의 역할(role)과 질문(prompt)을 RoleBasedChatClient를 통해 전달하여 ChatGPT의 응답을 반환하는 메서드 구현

- [ ] **RoleBasedChatController 구현**
    - [ ] `API endpoint 정의` : `GET /api/chat/role`
    - [ ] @RequestParam을 이용하여 사용자 질문(prompt)와 AI의 역할(role)을 수신
    - [ ] 수신한 prompt와 role을 `RoleChatService의 메서드에 전달` 하여 ChatGPT의 응답 반환

- [ ] **테스트 코드 작성**
    - [ ] `GET /api/chat/role` API 동작을 검증하는 통합 테스트 작성 및 실행

---

## Step 3: ChatClient를 활용한 배송지 정렬 기능 구현

- [ ] **Prompt Template 작성**
    - [ ] 배송지 정렬을 요청하는 구체적인 요청 Prompt 템플릿 작성

- [ ] **SortDeliveryAddressChatService 구현**
    - [ ] 기준 허브 주소와 정렬이 필요한 배송지 목록을 전달받아 ChatGPT를 통해 정렬된 결과를 반환하는 메서드 구현
        - ChatClient와 Prompt 템플릿을 사용하여 JSON 형태의 결과를 List 형태로 변환하여 반환
    - [ ] 배송지와 배송지 순서가 지정된 ChatGPT 응답 매핑을 위한 객체 정의
        - [SequencedDeliveryAddress.java](src/main/java/io/sparta/springaitutorial/step3/infrastructure/dto/SequencedDeliveryAddress.java)

- [ ] **배송지 정렬 요청 객체 정의**
    - [ ] 도착지 허브 주소와 정렬이 필요한 배송지 목록을 필드로 가지는 record 객체 정의
        - [SortDeliveryAddressRequest.java](src/main/java/io/sparta/springaitutorial/step3/application/dto/SortDeliveryAddressRequest.java)

- [ ] 배송지 목록 객체를 ChatGPT에 전달하기 위해 일관된 문자열로 변환하는 Formatter 구현
    - [ ] 배송지 목록 객체의 일관된 문자열 변환 검증을 위한 Test 작성
      - [DeliveryAddressFormatterTest.java](src/test/java/io/sparta/springaitutorial/step3/application/DeliveryAddressFormatterTest.java)

- [ ] **배송지 목록 정렬 Service 구현: SortDeliveryAddressFacade**
    - DIP 원칙에 따라 고수준 모듈이 ChatGPT와 통신하는 저수준 모듈의 구체적인 구현체에 직접 의존하지 않도록 인터페이스를 이용하여 의존 관계를 설정
        - 인터페이스를 통해 고수준 모듈에서 저수준 모듈의 구현체를 교체하거나 확장할 수 있도록 구성
            - 고수준 모듈: [SortDeliveryAddressFacade.java](src/main/java/io/sparta/springaitutorial/step3/application/SortDeliveryAddressFacade.java)
            - 저수준 모듈: [SortDeliveryAddressChatService.java](src/main/java/io/sparta/springaitutorial/step3/infrastructure/SortDeliveryAddressChatService.java)
            - 인터페이스: [SortDeliveryAddressService.java](src/main/java/io/sparta/springaitutorial/step3/application/SortDeliveryAddressService.java)

- [ ] **SortDeliveryAddressController 구현**
    - [ ] API Endpoint 정의: `POST /api/chat/delivery-addresses/sort`
    - [ ] @RequestBody를 이용하여 기준 허브 주소(hubAddress)와 배송지 목록(addresses)을 수신
    - [ ] 수신 데이터를 `SortDeliveryAddressChatService`로 전달하여 ChatGPT를 통해 응답받고 반환

- [ ] **테스트 코드 작성**
    - [ ] `POST /api/chat/delivery-addresses/sort` API의 동작을 검증하는 통합 테스트 작성 및 실행

---

## 구현 시 참고 사항

### Spring AI 사용을 위해 아래와 같이 의존성을 추가할 수 있어요

```groovy
ext {
    set('springAiVersion', "1.0.0-M6")
}

dependencies {
    implementation 'org.springframework.ai:spring-ai-openai-spring-boot-starter'
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.ai:spring-ai-bom:${property("springAiVersion")}")
    }
}

```

### Spring AI 의존성을 통해 Chat GPT와 연동하기 위해 필요한 API Key는 아래와 같이 설정할 수 있어요.

```yml
spring:
  application:
    name: spring-ai-tutorial

  ai:
    openai:
      api-key: ${OPEN_AI_API_KEY}
```

### Spring AI의 ChatClient를 이용하여 Chat GPT 연동하기 위해 아래와 같은 환경을 구성할 수 있어요.

```java

@Configuration
public class ChatClientConfig {

	@Bean
	public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
		return chatClientBuilder.build();
	}

}
```

### ChatClient를 이용하여 기본적인 질문(prompt)를 아래와 같은 형태로 GPT에 전달하고 응답을 수신할 수 있어요.

```java

@Component
@RequiredArgsConstructor
public class BasicChatService {
	private final ChatClient chatClient;

	public String basicChat(String prompt) {
		return chatClient.prompt()
			.user(prompt)
			.call()
			.content();
	}

}

```

---

## 예제 코드

> 위 TODO를 모두 구현하셨다면, 아래 작성된 예제 코드와 비교해보세요

- [Step1 예제 코드](https://github.com/team-project-tutorial/spring-ai-tutorial/tree/step1/basic-chat-client-example): step1/basic-chat-client-example
- [Step2 예제 코드](https://github.com/team-project-tutorial/spring-ai-tutorial/tree/step2/role-based-chat-client-example): step2/role-based-chat-client-example
- [Step3 예제 코드](https://github.com/team-project-tutorial/spring-ai-tutorial/tree/step3/sort-delivery-address-with-spring-ai-example): step3/sort-delivery-address-with-spring-ai-example

---

## 참고 자료

- [Spring AI Reference Docs](https://docs.spring.io/spring-ai/reference/)
- [Spring AI Chat Client Docs](https://docs.spring.io/spring-ai/reference/api/chatclient.html)
- [Open AI developer docs](https://platform.openai.com/docs/overview)

![low-level OpenAiAPi Class Diagram](https://docs.spring.io/spring-ai/reference/_images/openai-chat-api.jpg)
![low-lebv](https://docs.spring.io/spring-ai/reference/_images/spring-ai-message-api.jpg)
