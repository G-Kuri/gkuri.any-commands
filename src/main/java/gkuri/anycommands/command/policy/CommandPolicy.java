package gkuri.anycommands.command.policy;

public abstract class CommandPolicy {

	protected boolean locked;
	
	public void lockPolicy() {
		this.locked = true;
	}
	
	protected void unlockPolicy() {
		this.locked = false;
	}
	
	public boolean isLocked() {
		return this.locked;
	}
	
	protected void checkLocked() {
		if(this.locked) throw new IllegalStateException("this policy is locked.");
	}
}
