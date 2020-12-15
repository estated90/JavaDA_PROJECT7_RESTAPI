package com.nnk.springboot.service;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.Application;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.InvalidUserException;
import com.nnk.springboot.interfaces.PasswordManager;
import com.nnk.springboot.interfaces.UserService;
import com.nnk.springboot.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordManager passwordManager;

    @Test
    void testSavingUserDb() throws InvalidUserException {
	User user = new User("test1", "test1", "test1", "USER");
	userService.saveUserDb(user);
	List<User> listUser = userRepository.findAll();
	assertEquals(1, listUser.size());
	assertEquals("test1", listUser.get(0).getFullname());
	assertNotNull(listUser.get(0).getId());
	assertEquals("test1", listUser.get(0).getUsername());
	assertTrue(passwordManager.passwordDecoder("test1", listUser.get(0).getPassword()));
	assertEquals("USER", listUser.get(0).getRole());
	int userDb = userRepository.findByUsername("test1").getId();
	userService.deleteUser(userDb);
	Exception exception = assertThrows(InvalidUserException.class, () -> {
	    userService.findById(userDb);
	    ;
	});
	String expectedMessage = "Invalid user Id:" + userDb;
	String actualMessage = exception.getMessage();
	assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testUpdatingUserDb() throws InvalidUserException {
	User user = new User("test2", "test2", "test2", "USER");
	userService.saveUserDb(user);
	user.setFullname("test3");
	user.setPassword("test3");
	int userDb = userRepository.findByUsername("test2").getId();
	userService.updateUserId(userDb, user);
	User userData = userService.findById(userDb);
	assertEquals("test3", userData.getFullname());
	userService.deleteUser(userDb);
    }

    @Test
    void testDeleteFail() throws InvalidUserException {
	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	    userService.deleteUser(10);
	});
	String expectedMessage = "Invalid user Id:" + 10;
	String actualMessage = exception.getMessage();
	assertEquals(expectedMessage, actualMessage);
    }

}
