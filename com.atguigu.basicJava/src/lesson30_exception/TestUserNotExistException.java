package lesson30_exception;

import java.util.Arrays;
import java.util.List;

public class TestUserNotExistException {

	public static void main(String[] args) {
		
		
		User result = getUser("ee");
		
		

	}

	public static User getUser(String user){
		
		List<String> users = Arrays.asList("AA","BB","CC");
		
		if(users.contains(user)){
			System.out.println("用户存在");
			return new User();
			
		}else{
			throw new UserNotExistException("用户不存在！");
		}
		
		
	}
}
