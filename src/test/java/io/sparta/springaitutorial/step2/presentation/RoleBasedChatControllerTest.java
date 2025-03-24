package io.sparta.springaitutorial.step2.presentation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class RoleBasedChatControllerTest {
	@Autowired
	private MockMvc mockMvc;

	private static final String BASIC_CHAT_URI = "/api/chat/role";

	@Test
	@DisplayName("""
		시스템 메시지(defaultSystem)를 이용하여 AI에게 역할을 부여하고,
		ChatClient를 통해 Chat GPT와 통신하여 prompt에 대한 답변을 조회합니다.
		""")
	void roleBasedChat() throws Exception {
		// Given
		final String role = "Spring Boot와 JPA 기반 백엔드 개발";
		final String prompt = "Dirty Checking에 대해 설명해줘";

		// When
		ResultActions resultActions = this.mockMvc.perform(get(BASIC_CHAT_URI)
			.param("role", role)
			.param("prompt", prompt)
		);

		// Then
		resultActions.andDo(print())
			.andExpect(status().isOk())
		;
	}
}
