package gkuri.anycommands.command.policy;

public class ExecutePolicy extends CommandPolicy {

	protected int allowPipeCount = Integer.MAX_VALUE;
	protected int allowSubCommandsCount = Integer.MAX_VALUE;
	
	public int getAllowPipeCount() {
		return this.allowPipeCount;
	}
	
	public int getAllowSubCommandsCount() {
		return this.allowSubCommandsCount;
	}
	
	public void setAllowPipeCount(int allowPipeCount) {
		checkLocked();
		this.allowPipeCount = allowPipeCount;
	}
	
	public void setAllowSubCommandsCount(int allowSubCommandsCount) {
		checkLocked();
		this.allowSubCommandsCount = allowSubCommandsCount;
	}
}
