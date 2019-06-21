package lesson30_exception;

public class UserNotExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionID = 1L;
	
	public UserNotExistException() {
		// TODO Auto-generated constructor stub
	}
	
	public UserNotExistException(String msg){
		super(msg);
	}
}
