package io.sparta.springaitutorial.step1.application;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
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
