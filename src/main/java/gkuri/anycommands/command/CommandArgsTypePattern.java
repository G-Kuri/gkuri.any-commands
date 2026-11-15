package gkuri.anycommands.command;

public class CommandArgsTypePattern {
	
	private Class<?>[] argsType;
	private boolean isStrictQuantity;

	public CommandArgsTypePattern(Class<?>... argsType) {
		this.argsType = argsType;
		this.isStrictQuantity = true;
	}
	
	public CommandArgsTypePattern(boolean isStrictQuantity, Class<?>... argsType) {
		this.argsType = argsType;
		this.isStrictQuantity = isStrictQuantity;
	}
	
	public Class<?>[] getArgsType(){
		return this.argsType;
	}
	
	public boolean isStrictQuantity() {
		return this.isStrictQuantity;
	}
}
