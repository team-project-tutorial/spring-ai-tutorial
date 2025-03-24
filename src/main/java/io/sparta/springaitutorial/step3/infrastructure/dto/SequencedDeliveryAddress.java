package io.sparta.springaitutorial.step3.infrastructure.dto;

public record SequencedDeliveryAddress(
	String address,
	int sequence
) {

}
