package commands.commandclass;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gkuri.anycommands.command.Command;
import gkuri.anycommands.command.CommandArgsType;
import gkuri.anycommands.command.CommandArgument;
import gkuri.anycommands.command.CommandResult;

public class ArgTypeCommand implements Command {

	private static CommandArgsType argsType = 
			new CommandArgsType()
			.addArgsTypePattern(Integer.class)
			.addArgsTypePattern(Float.class)
			.addArgsTypePattern(LocalDate.class)
			.addArgsTypePattern(String.class, Integer.class)
			.addArgsTypePattern(Integer.class, Double.class);
	
	@Override
	public String getName() {
		return "args";
	}

	@Override
	public String getDescription() {
		return "show args type";
	}
	
	@Override
	public int getMinArgsCount() {
		return 1;
	}
	
	@Override
	public CommandArgsType getArgsType() {
		return argsType;
	}

	@Override
	public CommandResult execute(CommandArgument args) {
		StringBuilder sb = new StringBuilder("num : %d || type : %s");
		if(args.argsCountIs(1)) {
			return new CommandResult(sb.toString().formatted(args.getArgsTypePatternNumber(), args.getArgType(0).getSimpleName()));
		}
		
		List<Object> formattArgs = new ArrayList<>();
		formattArgs.add(args.getArgsTypePatternNumber());
		formattArgs.add(args.getArgType(0).getSimpleName());
		
		for(int i = 1; i < args.getArgsCount(); i++) {
			sb.append(" & %s");
			formattArgs.add(args.getArgType(i).getSimpleName());
		}
		
		return new CommandResult(sb.toString().formatted(formattArgs.toArray()));
	}

	@Override
	public CommandResult catchException(Exception exception, CommandArgument args) {
		exception.printStackTrace();
		return null;
	}
	
}
