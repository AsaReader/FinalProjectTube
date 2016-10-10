package tube.web;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import tube.entities.User;
import tube.persistence.UserDAO;
import tube.web.controllers.UserController;

public class UserControllerTest {

	@Test
	public void testGetRegistrationPage() throws Exception {
		UserController controller = new UserController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/user/register")).andExpect(view().name("registerForm"));
	}
	
	@Test
	public void testGetLoginPage() throws Exception {
		UserController controller = new UserController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/user/login")).andExpect(view().name("loginForm"));
	}
	
	@Test
	public void testProcessRegistration() throws Exception {
//		UserDAO mockUserDao = mock(UserDAO.class);
//		User unsaved = new User("Jimmy", "jim@gmail.com", "JimHasAStrongPassword");
//		User saved = new User(0L, "Jimmy", "jim@gmail.com", "JimHasAStrongPassword");
//		when(mockUserDao.save(unsaved)).thenReturn(saved);
//		
//		UserController controller = new UserController(mockUserDao);
//		MockMvc mockMvc = standaloneSetup(controller).build();
//
//		mockMvc.perform(post("/user/register")
//			   .param("username", unsaved.getUsername())
//			   .param("email", unsaved.getEmail())
//			   .param("password", unsaved.getPassword()))
//			   .andExpect(redirectedUrl("/user/" + unsaved.getUsername()));
//		
//		verify(mockUserDao, atLeastOnce()).save(unsaved);
	}
}
