package es.urjc.code.pricing.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ErrorResponseTest {

	private static final String DEFAULT_MESSAGE = "Default message";
	private static final Integer DEFAULT_STATUS = 500;

	@Test
	public void givenValidDataWhenBuildThenReturnOspErrorResponse() {
		final ErrorResponse response = new ErrorResponse.Builder().withStatus(DEFAULT_STATUS)
				.withMessage(DEFAULT_MESSAGE).build();
		assertEquals(DEFAULT_STATUS, response.getStatus());
		assertEquals(DEFAULT_MESSAGE, response.getMessage());
	}

	@Test
	public void shouldBeEqualsAndSymmetric() {
		final ErrorResponse response = new ErrorResponse.Builder().withStatus(DEFAULT_STATUS)
				.withMessage(DEFAULT_MESSAGE).build();
		final ErrorResponse otherResponse = new ErrorResponse.Builder().withStatus(DEFAULT_STATUS)
				.withMessage(DEFAULT_MESSAGE).build();

		Assert.assertTrue(response.equals(otherResponse) && otherResponse.equals(response));
		Assert.assertTrue(response.hashCode() == otherResponse.hashCode());
	}
}
