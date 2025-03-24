package io.sparta.springaitutorial.step2.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.sparta.springaitutorial.step2.application.RoleBasedChatService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class RoleBasedChatController {
	private final RoleBasedChatService roleBasedChatService;

	@GetMapping("/role")
	ResponseEntity<String> roleBasedChat(
		@RequestParam String role,
		@RequestParam String prompt
	) {
		return ResponseEntity.ok(roleBasedChatService.roleBasedChat(role, prompt));
	}
}
