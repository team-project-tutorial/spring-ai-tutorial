package io.sparta.springaitutorial.step3.application;

import java.util.List;

import org.springframework.stereotype.Component;

import io.sparta.springaitutorial.step3.application.dto.SortDeliveryAddressRequest;
import io.sparta.springaitutorial.step3.infrastructure.dto.SequencedDeliveryAddress;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SortDeliveryAddressFacade {
	private final SortDeliveryAddressService sortDeliveryAddressService;

	public List<SequencedDeliveryAddress> sortDeliveryAddress(SortDeliveryAddressRequest request) {
		return sortDeliveryAddressService.sortDeliveryAddress(
			request.hubAddress(),
			DeliveryAddressFormatter.format(request.deliveryAddresses())
		);
	}

}
