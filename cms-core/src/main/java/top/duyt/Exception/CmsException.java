package top.duyt.Exception;

public class CmsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8254092126607895424L;

	public CmsException() {
		super();
	}

	public CmsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CmsException(String message, Throwable cause) {
		super(message, cause);
	}

	public CmsException(String message) {
		super(message);
	}

	public CmsException(Throwable cause) {
		super(cause);
	}

}
