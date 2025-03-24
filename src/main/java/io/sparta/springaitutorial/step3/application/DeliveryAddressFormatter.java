package io.sparta.springaitutorial.step3.application;

import static org.apache.logging.log4j.util.Strings.*;

import java.util.List;
import java.util.stream.Collectors;

import io.sparta.springaitutorial.step3.application.dto.DeliveryAddress;

public class DeliveryAddressFormatter {
	private static final String PREFIX = "- ";
	private static final String DELIMITER = "\n";

	public static String format(List<DeliveryAddress> deliveryAddresses) {
		return deliveryAddresses.stream()
			.map(DeliveryAddress::address)
			.collect(Collectors.joining(DELIMITER + PREFIX, PREFIX, EMPTY));
	}
}
