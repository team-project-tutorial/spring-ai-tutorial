package io.sparta.springaitutorial.step3.infrastructure;

import static io.sparta.springaitutorial.step3.infrastructure.SortDeliveryPromptTemplate.*;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import io.sparta.springaitutorial.step3.application.SortDeliveryAddressService;
import io.sparta.springaitutorial.step3.infrastructure.dto.SequencedDeliveryAddress;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SortDeliveryAddressChatService implements SortDeliveryAddressService {
	private final ChatClient chatClient;

	@Override
	public List<SequencedDeliveryAddress> sortDeliveryAddress(String hubAddress, String formattedAddresses) {
		return chatClient.prompt()
			.user(userSpec -> userSpec.text(promptTemplate)
				.param("hub_address", hubAddress)
				.param("request_addresses", formattedAddresses)
			)
			.call()
			.entity(new ParameterizedTypeReference<>() {
			});
	}
}
