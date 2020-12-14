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

}
