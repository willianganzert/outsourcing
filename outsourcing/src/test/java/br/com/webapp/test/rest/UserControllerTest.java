package br.com.webapp.test.rest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.webapp.dao.UserDao;
import br.com.webapp.model.User;
import br.com.webapp.service.UserService;
import br.com.webapp.test.TestContext;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class UserControllerTest {
  
	   private MockMvc mockMvc;
	   
	    @Autowired
	    private UserService userServiceMock;
	    
	    @Autowired
        private WebApplicationContext applicationContext;
	    
	    @Before
		public void setup() {
	    	mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		}


	 
	    @Test
	    public void restUserController() throws Exception {
	    	
	    	UserDao dao = new UserDao();
	    	User user = dao.findById(1); 
	    	
	        when(userServiceMock.findById(user.getId())).thenReturn(user);
	 
	        mockMvc.perform(get("/api/user/{id}", user.getId()).contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	                
	 
	        verify(userServiceMock, times(1)).findById(user.getId());
	        verifyNoMoreInteractions(userServiceMock);
	    }
}