package sample;

import gkuri.anycommands.command.CommandArgument;
import gkuri.anycommands.command.CommandManager;
import gkuri.anycommands.command.CommandManagerBuilder;
import sample.base.AddCommand;

public class Tester {

	public static void main(String[] args) {
		
		CommandManager manager = new CommandManagerBuilder()
				.addHelpCommand(true)
				.addCommand(new AddCommand())
				.build();
		
		System.out.println(manager.executeCommand(new CommandArgument("help")).getResult());
		
		System.out.println(manager.executeCommand(new CommandArgument("add 123")).getResult());
		
		System.out.println(manager.executeCommand(new CommandArgument("add 123 456 789")).getResult());
		
		System.out.println(manager.executeCommand(new CommandArgument("add 123 456")).getResult());
	}
	
}
