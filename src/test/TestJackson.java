package test;

import java.io.IOException;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.model.User;

public class TestJackson {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		User user = new User(12, "vu", "abc", 123, 24343, 122121);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(user));
	}

}
