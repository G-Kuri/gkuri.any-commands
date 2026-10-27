package commands.commandclass;

import gkuri.anycommands.command.Command;
import gkuri.anycommands.command.CommandArgument;
import gkuri.anycommands.command.CommandResult;

public class AdminCommand implements Command {

	@Override
	public String getName() {
		return "admin";
	}

	@Override
	public String getDescription() {
		return "admin's command";
	}
	
	@Override
	public boolean canExecuteCommand(Object executeUser) {
		return false;
	}

	@Override
	public CommandResult execute(CommandArgument args) {
		return new CommandResult("execute admin's command");
	}

}
