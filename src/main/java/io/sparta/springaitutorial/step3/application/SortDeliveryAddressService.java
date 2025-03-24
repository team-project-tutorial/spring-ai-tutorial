package io.sparta.springaitutorial.step3.application;

import java.util.List;

import io.sparta.springaitutorial.step3.infrastructure.dto.SequencedDeliveryAddress;

public interface SortDeliveryAddressService {
	List<SequencedDeliveryAddress> sortDeliveryAddress(String hubAddress, String formattedAddresses);
}
