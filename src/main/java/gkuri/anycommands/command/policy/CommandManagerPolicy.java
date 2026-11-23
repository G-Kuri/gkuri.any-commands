package gkuri.anycommands.command.policy;

public class CommandManagerPolicy extends CommandPolicy {

	private ExecutePolicy executePolicy;
	private InteractivePolicy interactivePolicy;
	private EventPolicy eventPolicy;
	
	public CommandManagerPolicy() {
		this(new ExecutePolicy(), new InteractivePolicy(), new EventPolicy());
	}
	
	public CommandManagerPolicy(ExecutePolicy executePolicy, InteractivePolicy interactivePolicy, EventPolicy eventPolicy) {
		this.executePolicy = executePolicy;
		this.interactivePolicy = interactivePolicy;
		this.eventPolicy = eventPolicy;
		this.locked = false;
	}
	
	@Override
	public void lockPolicy() {
		this.eventPolicy.lockPolicy();
		this.executePolicy.lockPolicy();
		this.interactivePolicy.lockPolicy();
		this.locked = true;
	}
	
	@Override
	protected void unlockPolicy() {
		this.eventPolicy.unlockPolicy();
		this.executePolicy.unlockPolicy();
		this.interactivePolicy.unlockPolicy();
		this.locked = false;
	}
	
	public ExecutePolicy getExecutePolicy() {
		return this.executePolicy;
	}
	
	public void setExecutePolicy(ExecutePolicy executePolicy) {
		checkLocked();
		if(executePolicy == null) return;
		this.executePolicy = executePolicy;
	}
	
	public InteractivePolicy getInteractivePolicy() {
		return this.interactivePolicy;
	}
	
	public void setInteractivePolicy(InteractivePolicy interactivePolicy) {
		checkLocked();
		if(interactivePolicy == null) return;
		this.interactivePolicy = interactivePolicy;
	}
	
	public EventPolicy getEventPolicy() {
		return this.eventPolicy;
	}
	
	public void setEventPolicy(EventPolicy eventPolicy) {
		checkLocked();
		if(eventPolicy == null) return;
		this.eventPolicy = eventPolicy;
	}
}
