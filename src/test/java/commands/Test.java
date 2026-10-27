package commands;

import commands.commandclass.AdminCommand;
import commands.commandclass.ArgTypeCommand;
import gkuri.anycommands.command.CommandArgument;
import gkuri.anycommands.command.CommandArgumentBuilder;
import gkuri.anycommands.command.CommandManager;
import gkuri.anycommands.command.CommandManagerBuilder;
import gkuri.anycommands.command.CommandResult;

public class Test {
	
	private static CommandManager manager;

	public static void main(String[] args) {
		manager = new CommandManagerBuilder()
				.addHelpCommand(true)
				.addEchoCommand(true)
				.addHistoryCommand(true)
				.addSudoCommand(true, "pass")
				.addCommand(new ArgTypeCommand())
				.addCommand(new AdminCommand())
				.build();
		
		executeCommand("help");
		executeCommand("help history");
		executeCommand("args 123");
		executeCommand("args 456.456F");
		executeCommand("args 2003.10.26");
		executeCommand("args '' 123");
		executeCommand("args 456.456");
		executeCommand("args 123 456.456");
		executeCommand(new CommandArgumentBuilder().setCommand("args").setArgs(123, 456.456).build());
		executeCommand(new CommandArgumentBuilder().setCommand("args").setArgs(123.456, new Test()).build());
		executeCommand("history");
		executeCommand("admin");
		CommandResult cr = manager.executeCommand(new CommandArgument("sudo admin"));
		System.out.println(cr.getPrompt() + " pass");
		System.out.println(manager.executeCommand(new CommandArgumentBuilder().setInteractiveThreadId(cr.getInteractiveThreadId()).setArgs("pass").build()).getResult());
		executeCommand("history 3");
		executeCommand("history 3 |");
	}
	
	private static void executeCommand(String args) {
		executeCommand(new CommandArgument(args));
	}
	private static void executeCommand(CommandArgument args) {
		System.out.println(manager.executeCommand(args).getResult());
	}
	
}
