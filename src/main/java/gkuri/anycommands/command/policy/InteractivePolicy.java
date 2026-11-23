package gkuri.anycommands.command.policy;

public class InteractivePolicy extends CommandPolicy {

	protected boolean checkAuth = false;
	protected boolean checkUser = true;
	
	public boolean checkAuth() {
		return this.checkAuth;
	}
	
	public boolean checkUser() {
		return this.checkUser;
	}
	
	public void checkAuth(boolean checkAuth) {
		checkLocked();
		this.checkAuth = checkAuth;
	}
	
	public void checkUser(boolean checkUser) {
		checkLocked();
		this.checkUser = checkUser;
	}
}
