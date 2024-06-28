package ma.inetum.brique.exception;

public class ExceptionFonctionnelle extends Throwable {

	private static final long serialVersionUID = -7153804636181437291L;
	
	public ExceptionFonctionnelle(String message) {
		super(message);
	}
	
	public ExceptionFonctionnelle(String message, Throwable th) {
		super(message, th);
	}
}
