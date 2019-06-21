package lesson30_exception;

public class EcDef extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EcDef() {

	}
	
	public EcDef(String msg){
		super(msg);
	}
}
