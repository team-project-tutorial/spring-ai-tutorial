package io.sparta.springaitutorial.step1.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.sparta.springaitutorial.step1.application.BasicChatService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class BasicChatController {
	private final BasicChatService basicChatService;

	@GetMapping("/basic")
	ResponseEntity<String> basicChat(@RequestParam String prompt) {
		String answer = basicChatService.basicChat(prompt);
		return ResponseEntity.ok(answer);
	}
}
