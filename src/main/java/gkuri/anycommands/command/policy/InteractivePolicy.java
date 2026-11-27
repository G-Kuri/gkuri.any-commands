package gkuri.anycommands.command.policy;

public class InteractivePolicy extends CommandPolicy {

	protected boolean checkAuth = false;
	protected boolean checkUser = true;
	protected boolean castArgs = false;
	
	public boolean checkAuth() {
		return this.checkAuth;
	}
	
	public boolean checkUser() {
		return this.checkUser;
	}
	
	public boolean castArgs() {
		return this.castArgs;
	}
	
	public void checkAuth(boolean checkAuth) {
		checkLocked();
		this.checkAuth = checkAuth;
	}
	
	public void checkUser(boolean checkUser) {
		checkLocked();
		this.checkUser = checkUser;
	}
	
	public void castArgs(boolean castArgs) {
		checkLocked();
		this.castArgs = castArgs;
	}
}
