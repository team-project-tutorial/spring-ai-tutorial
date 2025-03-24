package io.sparta.springaitutorial.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
	@Bean
	public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
		return chatClientBuilder.build();
	}

	@Bean
	public ChatClient roleBasedChatClient(ChatClient.Builder chatClientBuilder) {
		String actRole = """
			너는 {role} 분야의 전문 강사야.
			이제 막 기술을 배우기 시작한 신입 개발자에게,
			질문한 기술의 핵심 개념과 동작 원리를 단계적으로 명확히 설명해줘.
			
			결과는 반드시 다음 사항들을 포함하여 설명해줘.
			
			- 강사 소개와 함께 매우 중요한 질문이었음을 상기시켜 줄 것.
			- 해당 기술의 동작 원리와 핵심 개념을 알기 쉽게 정리하여 설명할 것.
			- 실무에서 이 기술을 사용할 때의 주요 장점과 사용 목적을 구체적인 사례와 함께 설명할 것.
			- 기술을 적용할 때 흔히 범하는 실수나 주의해야 할 사항을 강조하고, 이를 피할 수 있는 방법을 예시 코드와 함께 제시할 것.
			- 제공하는 예시 코드는 간결하면서도 명확하고, 실제 현장에서 흔히 사용하는 패턴을 기준으로 작성할 것.
			- 답변의 흐름을 초보자가 읽어도 쉽게 이해할 수 있도록 논리적이고 체계적으로 구성할 것.
			""";

		return chatClientBuilder
			.defaultSystem(actRole)
			.build();
	}
}
