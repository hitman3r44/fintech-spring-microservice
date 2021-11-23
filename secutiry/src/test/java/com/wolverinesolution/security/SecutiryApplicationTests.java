package com.wolverinesolution.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SecutiryApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void whenCreatesEmptyOptional_thenCorrect() {
		Optional<String> empty = Optional.empty();
		assertFalse(empty.isPresent());
	}

	@Test
	public void givenNonNull_whenCreatesNonNullable_thenCorrect() {
		String name = "baeldung";
		Optional<String> opt = Optional.of(name);
		assertTrue(opt.isPresent());
	}

//	@Test(expected = NullPointerException.class)
//	public void givenNull_whenThrowsErrorOnCreate_thenCorrect() {
//		String name = null;
//		Optional.of(name);
//	}

	@Test
	public void givenNonNull_whenCreatesNullable_thenCorrect() {
		String name = "baeldung";
		Optional<String> opt = Optional.ofNullable(name);
		assertTrue(opt.isPresent());
	}

	@Test
	public void givenNull_whenCreatesNullable_thenCorrect() {
		String name = null;
		Optional<String> opt = Optional.ofNullable(name);
		assertFalse(opt.isPresent());
	}

	@Test
	public void whenOptionalFilterWorks_thenCorrect() {
		Integer year = 2016;
		Optional<Integer> yearOptional = Optional.of(year);
		boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
		assertTrue(is2016);
		boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
		assertFalse(is2017);
	}

	@Test
	public void whenFiltersWithoutOptional_thenCorrect() {
		assertTrue(priceIsInRange1(new Modem(10.0)));
		assertFalse(priceIsInRange1(new Modem(9.9)));
		assertFalse(priceIsInRange1(new Modem(null)));
		assertFalse(priceIsInRange1(new Modem(15.5)));
		assertFalse(priceIsInRange1(null));
	}

	public boolean priceIsInRange1(Modem modem2) {
		return Optional.ofNullable(modem2)
				.map(Modem::getPrice)
				.filter(p -> p >= 10)
				.filter(p -> p <= 15)
				.isPresent();
	}

	@Test
	public void givenOptional_whenMapWorks_thenCorrect() {
		List<String> companyNames = Arrays.asList(
				"paypal", "oracle", "", "microsoft", "", "apple");
			Optional<List<String>> listOptional = Optional.of(companyNames);

		int size = listOptional
				.map(List::size)
				.orElse(0);
		assertEquals(6, size);
	}

}
