package commands.commandclass;

import java.time.LocalDate;

import gkuri.anycommands.command.Command;
import gkuri.anycommands.command.CommandArgsType;
import gkuri.anycommands.command.CommandArgument;
import gkuri.anycommands.command.CommandResult;

public class ArgsSubCommand implements Command {

	private static CommandArgsType argsType = 
			new CommandArgsType()
			.addArgsTypePattern(Integer.class)
			.addArgsTypePattern(Float.class)
			.addArgsTypePattern(LocalDate.class)
			.addArgsTypePattern(String.class, Integer.class)
			.addArgsTypePattern(Integer.class, Double.class);
	
	@Override
	public String getName() {
		return "sub";
	}

	@Override
	public String getDescription() {
		return "show match pattern number";
	}
	
	@Override
	public CommandArgsType getArgsType() {
		return argsType;
	}

	@Override
	public CommandResult execute(CommandArgument args) {
		return new CommandResult("args type number is " + args.getArgsTypePatternNumber());
	}

}
