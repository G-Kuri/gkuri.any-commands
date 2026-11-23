package gkuri.anycommands.command.policy;

public class EventPolicy extends CommandPolicy {

	protected boolean allowListenSubcommandsEvent = true;
	protected boolean allowBeforeEvent = true;
	protected boolean allowAfterEvent = true;
	protected boolean allowStatusEvent = true;
	
	public boolean allowListenSubcommandsEvent() {
		return this.allowListenSubcommandsEvent;
	}
	
	public boolean allowBeforeEvent() {
		return this.allowBeforeEvent;
	}
	
	public boolean allowAfterEvent() {
		return this.allowAfterEvent;
	}
	
	public boolean allowStatusEvent() {
		return this.allowStatusEvent;
	}
	
	public void allowListenSubcommandsEvent(boolean allowListenSubcommandsEvent) {
		checkLocked();
		this.allowListenSubcommandsEvent = allowListenSubcommandsEvent;
	}
	
	public void allowBeforeEvent(boolean allowBeforeEvent) {
		checkLocked();
		this.allowBeforeEvent = allowBeforeEvent;
	}
	
	public void allowAfterEvent(boolean allowAfterEvent) {
		checkLocked();
		this.allowAfterEvent = allowAfterEvent;
	}
	
	public void allowStatusEvent(boolean allowStatusEvent) {
		checkLocked();
		this.allowStatusEvent = allowStatusEvent;
	}
}
