package lexyaccgen;

public class EOFException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public EOFException(String message, Throwable cause) {
		super(message, cause);
	}

	public EOFException(String message) {
		super(message);
	}

}
