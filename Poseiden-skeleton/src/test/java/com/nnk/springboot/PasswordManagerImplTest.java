package com.nnk.springboot;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;

import com.nnk.springboot.service.PasswordManagerImpl;

class PasswordManagerImplTest {

	@InjectMocks
	private static PasswordManagerImpl passwordManagerImpl;

	@BeforeAll
	private static void init() {
		passwordManagerImpl = new PasswordManagerImpl();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@ParameterizedTest
	@ValueSource(strings = {"AZERTY", "123456", "AzertDF123456FD"})
	void test_password_encoded_are_decoded_text(String arg) {
		String password = arg;
		String decoded = passwordManagerImpl.passwordEncoder(password);
		assertTrue(passwordManagerImpl.passwordDecoder(password, decoded));
	}
	
	@Test
	void test_different_password_encoded_are_false_text_numbers() {
		String password = "AzertDF123456FD";
		String password2 = "AzertDF1256FD";
		String decoded = passwordManagerImpl.passwordEncoder(password);
		assertFalse(passwordManagerImpl.passwordDecoder(password2, decoded));
	}

	@Test
	void test_paasword_correct() {
		String password = "AzertDF123456FD@";
		assertTrue(passwordManagerImpl.isValidPassword(password));
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "Qwert1@", "qwerty1@", "QWERTY1@", "QWERTYu@", "Qwertyu1"})
	void test_paasword_null(String arg) {
		String password = arg;
		assertFalse(passwordManagerImpl.isValidPassword(password));
	}

}
