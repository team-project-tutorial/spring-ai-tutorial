package io.sparta.springaitutorial.step2.application;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleBasedChatService {
	private static final String ROLE = "role";
	private final ChatClient roleBasedChatClient;

	public String roleBasedChat(String role, String prompt) {
		return roleBasedChatClient.prompt()
			.system(promptSystemSpec ->
				promptSystemSpec.param(ROLE, role)
			)
			.user(prompt)
			.call()
			.content();
	}
}
