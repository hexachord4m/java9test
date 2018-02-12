package jp.hexachord.exception;

public class CustomException extends Exception {
	public static final int FILE_NOT_FOUND = 1;
	public static final int FILE_READ_ERROR = 2;

	private int errorNum;

	public CustomException(int errorNum, Exception e) {
		this.errorNum = errorNum;
	}

	public int getErrorNum() {
		return errorNum;
	}
}
