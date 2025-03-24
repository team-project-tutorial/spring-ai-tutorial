package io.sparta.springaitutorial.step3.application.dto;

import java.util.List;

public record SortDeliveryAddressRequest(
	String hubAddress,
	List<DeliveryAddress> deliveryAddresses
) {

}
