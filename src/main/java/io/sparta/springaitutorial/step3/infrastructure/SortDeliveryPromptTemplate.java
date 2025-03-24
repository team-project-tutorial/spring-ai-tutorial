package io.sparta.springaitutorial.step3.infrastructure;

public class SortDeliveryPromptTemplate {
	static String promptTemplate = """
		아래에 제공하는 여러 배송 주소를 기준 허브 주소인 {hub_address}에서 가까운 순서대로 정렬해줘.
		
		정렬된 결과는 반드시 다음 형식의 JSON 배열로만 응답해줘.
		- 정렬된 배송 주소는 반드시 address 필드에 입력해줘.
		- 정렬된 순서는 반드시 sequence 필드로 나타내고, 1부터 시작하는 숫자로 순차적으로 표현해줘
		- 기타 부연 설명은 절대 포함하지 말고 오직 위의 형태를 따르는 JSON 배열 형태로만 반환해줘.
		
		배송 순서를 정할 때 다음 사항을 준수해줘.
		
		- 반드시 기준 허브 주소{hub_address}로부터의 거리나 이동 시간을 기준으로 가까운 순서대로 배송 순서를 정해야 해.
		- 제공된 모든 주소는 빠짐없이 결과에 포함되어야 하며, 동일한 순서는 없어야 해.
		- JSON 형식 이외의 추가 설명은 포함하지 말고 오직 JSON 배열만 응답에 포함해줘.
		
		정렬이 필요한 배송 주소 목록:
		{request_addresses}
		""";
}
