package io.sparta.springaitutorial.step1.presentation;

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
class BasicChatControllerTest {
	@Autowired
	private MockMvc mockMvc;

	private static final String BASIC_CHAT_URI = "/api/chat/basic";

	@Test
	@DisplayName("""
		ChatClient를 통해 ChatGPT와 통신하여
		요청 prompt에 대한 답변을 조회한다.
		""")
	void basicChat() throws Exception {
		// Given
		final String prompt = "Dirty Checking에 대해 설명해줘";

		// When
		ResultActions resultActions = this.mockMvc.perform(get(BASIC_CHAT_URI)
			.param("prompt", prompt)
		);

		// Then
		resultActions.andDo(print())
			.andExpect(status().isOk())
		;
	}

}
