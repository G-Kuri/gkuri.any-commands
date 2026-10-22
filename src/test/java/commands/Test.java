package commands;

import commands.commandclass.ArgTypeCommand;
import gkuri.anycommands.command.CommandArgument;
import gkuri.anycommands.command.CommandManager;
import gkuri.anycommands.command.CommandManagerBuilder;

public class Test {
	
	private static CommandManager manager;

	public static void main(String[] args) {
		manager = new CommandManagerBuilder()
				.addHelpCommand(true)
				.addEchoCommand(true)
				.addHistoryCommand(true)
				.addSudoCommand(true, "pass")
				.addCommand(new ArgTypeCommand())
				.build();
		
		executeCommand("help");
		executeCommand("help history");
		executeCommand("args 123");
		executeCommand("args 456.456F");
		executeCommand("args 2003.10.26");
		executeCommand("args '' 123");
		executeCommand("args 456.456");
		executeCommand("args 123 456.456");
	}
	
	private static void executeCommand(String args) {
		executeCommand(new CommandArgument(args));
	}
	private static void executeCommand(CommandArgument args) {
		System.out.println(manager.executeCommand(args).getResult());
	}
	
}
