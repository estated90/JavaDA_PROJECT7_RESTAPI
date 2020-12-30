package com.nnk.springboot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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

	@Test
	void test_password_encoded_are_decoded_text() {
		String password = "AZERTY";
		String decoded = passwordManagerImpl.passwordEncoder(password);
		assertTrue(passwordManagerImpl.passwordDecoder(password, decoded));
	}

	@Test
	void test_password_encoded_are_decoded_numbers() {
		String password = "123456";
		String decoded = passwordManagerImpl.passwordEncoder(password);
		assertTrue(passwordManagerImpl.passwordDecoder(password, decoded));
	}

	@Test
	void test_password_encoded_are_decoded_text_numbers() {
		String password = "AzertDF123456FD";
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

	@Test
	void test_paasword_null() {
		String password = null;
		assertFalse(passwordManagerImpl.isValidPassword(password));
	}

	@Test
	void test_paasword_not_long_enough() {
		String password = "Qwert1@";
		assertFalse(passwordManagerImpl.isValidPassword(password));
	}

	@Test
	void test_paasword_no_upper_case() {
		String password = "qwerty1@";
		assertFalse(passwordManagerImpl.isValidPassword(password));
	}

	@Test
	void test_paasword_no_lower_case() {
		String password = "QWERTY1@";
		assertFalse(passwordManagerImpl.isValidPassword(password));
	}

	@Test
	void test_paasword_no_number() {
		String password = "QWERTYu@";
		assertFalse(passwordManagerImpl.isValidPassword(password));
	}

	@Test
	void test_paasword_no_special_Character() {
		String password = "Qwertyu1";
		assertFalse(passwordManagerImpl.isValidPassword(password));
	}

}
