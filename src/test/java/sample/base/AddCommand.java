package sample.base;

import gkuri.anycommands.command.Command;
import gkuri.anycommands.command.CommandArgsType;
import gkuri.anycommands.command.CommandArgument;
import gkuri.anycommands.command.CommandResult;

public class AddCommand implements Command {

	@Override
	public String getName() {
		return "add";
	}

	@Override
	public String getDescription() {
		return "引数の2値の和を返します";
	}
	
	@Override
	public int getMaxArgsCount() {
		return 2;
	}
	
	@Override
	public int getMinArgsCount() {
		return 2;
	}
	
	@Override
	public CommandArgsType getArgsType() {
		return new CommandArgsType().addArgsTypePattern(Integer.class, Integer.class);
	}

	@Override
	public CommandResult execute(CommandArgument args) {
		return new CommandResult(args.getArgInteger(0) + args.getArgInteger(1));
	}

}
