package io.sparta.springaitutorial.step3.presentation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.sparta.springaitutorial.step3.application.SortDeliveryAddressFacade;
import io.sparta.springaitutorial.step3.application.dto.SortDeliveryAddressRequest;
import io.sparta.springaitutorial.step3.infrastructure.dto.SequencedDeliveryAddress;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SortDeliveryAddressController {
	private final SortDeliveryAddressFacade sortDeliveryAddressFacade;

	@PostMapping("delivery-addresses/sort-by-distance-from-hub")
	ResponseEntity<List<SequencedDeliveryAddress>> sortDeliveryAddresses(
		@RequestBody SortDeliveryAddressRequest request
	) {
		return ResponseEntity.ok(sortDeliveryAddressFacade.sortDeliveryAddress(request));
	}
}
