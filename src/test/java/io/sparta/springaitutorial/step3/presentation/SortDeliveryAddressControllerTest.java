package io.sparta.springaitutorial.step3.presentation;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.sparta.springaitutorial.step3.application.dto.DeliveryAddress;
import io.sparta.springaitutorial.step3.application.dto.SortDeliveryAddressRequest;
import io.sparta.springaitutorial.step3.infrastructure.dto.SequencedDeliveryAddress;

@SpringBootTest
@AutoConfigureMockMvc
class SortDeliveryAddressControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private static final String SORT_DELIVERY_ADDRESS_URI = "/api/delivery-addresses/sort-by-distance-from-hub";

	@Test
	@DisplayName("""
		ChatClient를 통해 Chat GPT와 통신하여 허브로 부터 거리순으로 가까운 배송지를 정렬합니다.
		""")
	void sortDeliveryAddress() throws Exception {
		// Given
		SortDeliveryAddressRequest sortDeliveryAddressRequest = createSortDeliveryAddressRequest();

		// When
		ResultActions resultActions = this.mockMvc.perform(post(SORT_DELIVERY_ADDRESS_URI)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsBytes(sortDeliveryAddressRequest))
		);

		// Then
		assertResult(resultActions);
	}

	private void assertResult(ResultActions resultActions) throws Exception {
		List<SequencedDeliveryAddress> actual = extractBody(resultActions);

		assertThat(actual).containsExactly(
			new SequencedDeliveryAddress("부산 중구 자갈치로 42", 1),
			new SequencedDeliveryAddress("부산 중구 중구로47번길 7", 2),
			new SequencedDeliveryAddress("부산 서구 해돋이로 250", 3),
			new SequencedDeliveryAddress("부산 서구 천마로 224-2", 4),
			new SequencedDeliveryAddress("부산 서구 아미동2가 81-68", 5)
		);

		resultActions.andDo(print())
			.andExpect(status().isOk())
		;
	}

	private List<SequencedDeliveryAddress> extractBody(ResultActions resultActions) throws Exception {
		return objectMapper.readValue(
			resultActions.andReturn().getResponse().getContentAsString(),
			new TypeReference<>() {
			}
		);
	}

	private SortDeliveryAddressRequest createSortDeliveryAddressRequest() {
		return new SortDeliveryAddressRequest(
			"부산광역시 동구 중앙대로 206",
			createDeliveryAddressRequests()
		);
	}

	private List<DeliveryAddress> createDeliveryAddressRequests() {
		return List.of(
			new DeliveryAddress("부산 서구 해돋이로 250"),
			new DeliveryAddress("부산 서구 천마로 224-2"),
			new DeliveryAddress("부산 중구 중구로47번길 7"),
			new DeliveryAddress("부산 서구 아미동2가 81-68"),
			new DeliveryAddress("부산 중구 자갈치로 42")
		);
	}
}
