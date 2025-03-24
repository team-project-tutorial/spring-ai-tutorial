package io.sparta.springaitutorial.step3.application;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.sparta.springaitutorial.step3.application.dto.DeliveryAddress;

@DisplayName("Formatter:DeliveryAddress")
class DeliveryAddressFormatterTest {

	@Test
	@DisplayName("배송지 목록 객체를 ChatGPT에 전달하기 위해 일관된 문자열로 변환한다.")
	void format() {
		// Given
		List<DeliveryAddress> given = List.of(
			new DeliveryAddress("부산 서구 해돋이로 250"),
			new DeliveryAddress("부산 서구 천마로 224-2"),
			new DeliveryAddress("부산 중구 중구로47번길 7"),
			new DeliveryAddress("부산 서구 아미동2가 81-68"),
			new DeliveryAddress("부산 중구 자갈치로 42")
		);

		String expected = """
			- 부산 서구 해돋이로 250
			- 부산 서구 천마로 224-2
			- 부산 중구 중구로47번길 7
			- 부산 서구 아미동2가 81-68
			- 부산 중구 자갈치로 42""";

		// When
		String actual = DeliveryAddressFormatter.format(given);

		// Then
		assertThat(actual).isEqualTo(expected);
	}
}
