package povos;

//邮箱激活返回信息结果集
public class ActivationResult {

	private boolean res;
	private String message;

	public boolean isRes() {
		return res;
	}

	public void setRes(boolean res) {
		this.res = res;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ActivationResult [res=" + res + ", message=" + message + "]";
	}

	/**
	 * 
	 */
	public ActivationResult() {
		super();
	}

	/**
	 * @param res
	 * @param message
	 */
	public ActivationResult(boolean res, String message) {
		super();
		this.res = res;
		this.message = message;
	}

}
