package gkuri.anycommands.command;

public class CommandArgsCastInfo {
	
	private int patternNumber;
	private boolean patternMatch;
	private Class<?>[] expected;
	private Boolean[] castSuccess;
	private Object[] resultObject;
	
	protected CommandArgsCastInfo(int patternNumber, boolean patternMatch, Class<?>[] expected, Boolean[] castSuccess, Object[] resultObject) {
		this.patternNumber = patternNumber;
		this.expected = expected;
		this.patternMatch = patternMatch;
		this.castSuccess = castSuccess;
		this.resultObject = resultObject;
	}
	
	public int getPatternNumber() {
		return this.patternNumber;
	}
	
	public boolean isPatternMatch() {
		return this.patternMatch;
	}
	
	public Class<?>[] getExpected(){
		return this.expected;
	}
	
	public Boolean[] getCastSuccess() {
		return this.castSuccess;
	}
	
	public Object[] getResultObject() {
		return this.resultObject;
	}
}
