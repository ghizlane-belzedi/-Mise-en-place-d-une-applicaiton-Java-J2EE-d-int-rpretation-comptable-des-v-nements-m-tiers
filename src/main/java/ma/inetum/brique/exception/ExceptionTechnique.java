package ma.inetum.brique.exception;

public class ExceptionTechnique extends Exception {


	private static final long serialVersionUID = 217928808111656509L;

	public ExceptionTechnique(String errorMessage) {
		super(errorMessage);
	}
	
	public ExceptionTechnique(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}
}
