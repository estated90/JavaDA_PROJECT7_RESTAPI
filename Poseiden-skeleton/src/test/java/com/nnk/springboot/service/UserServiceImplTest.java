package com.nnk.springboot.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nnk.springboot.Application;
import com.nnk.springboot.domain.User;
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
    void testSavingUserDb() {
	User user = new User("test1","test1","test1", "USER");
	userService.saveUserDb(user);
	List<User> listUser = userRepository.findAll();
	assertEquals(1, listUser.size());
	assertEquals("test1", listUser.get(0).getFullname());
	assertEquals("test1", listUser.get(0).getUsername());
	assertEquals("test1", listUser.get(0).getUsername());
	assertTrue("test1", passwordManager.passwordDecoder("test1", listUser.get(0).getPassword()));
    }

}
