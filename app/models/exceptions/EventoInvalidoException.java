package models.exceptions;

public class EventoInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 785342341L;

	public EventoInvalidoException(String mensagem) {
		super(mensagem);
	}
}
