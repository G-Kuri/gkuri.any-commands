package gkuri.anycommands.command.annotation.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import gkuri.anycommands.command.Command;
import gkuri.anycommands.command.CommandArgument;
import gkuri.anycommands.command.CommandResult;
import gkuri.anycommands.command.annotation.ExecuteArgsTypeNumber;

public class ExecuteArgsTypeNumberProcessor extends AbstractProcessor {
	
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		Map<TypeElement, List<Integer>> used = new HashMap<>();
		
		for (Element element : roundEnv.getElementsAnnotatedWith(ExecuteArgsTypeNumber.class)) {

			if (element.getKind() != ElementKind.METHOD) {
				continue;
			}
			
			ExecutableElement method = (ExecutableElement) element;
			
			TypeElement clazz = (TypeElement) method.getEnclosingElement();

		    TypeElement allowedElement = processingEnv.getElementUtils().getTypeElement(Command.class.getName());

		    if (allowedElement == null) return false;
		    
		    if(!processingEnv.getTypeUtils().isSubtype(clazz.asType(),allowedElement.asType())) {
		    	processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
			    		"@ExecuteArgsTypeNumber は Command クラス内のメソッドにのみ付けられます", method);
		    }
			
			if (!isArrowedMethod(method)) {
			    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
		    		"@ExecuteArgsTypeNumber は CommandArgument を1つ受け取り、"
					+ "CommandResult を返すメソッドにのみ付けられます", method);
	        }
			
			if(used.get(clazz) == null) used.put(clazz, new ArrayList<>());
			
			ExecuteArgsTypeNumber eatn = method.getAnnotation(ExecuteArgsTypeNumber.class);
			
			if(used.get(clazz).contains(eatn.value())) {
				processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
			    		"@ExecuteArgsTypeNumber(" + eatn.value() + ") が複数定義されています", method);
			}
			
			used.get(clazz).add(eatn.value());
	    }
        
	    return true;
	}
	
	private boolean isArrowedMethod(Element elem) {
		ExecutableElement method = (ExecutableElement) elem;
		return isArrowedArg(method) && isArrowedReturn(method);
	}

	private boolean isArrowedArg(ExecutableElement method) {
		if(method.getParameters().size() != 1) return false;
		return processingEnv.getTypeUtils().isSubtype(method.getParameters().get(0).asType(), processingEnv.getElementUtils().getTypeElement(CommandArgument.class.getName()).asType());
	}
	
	private boolean isArrowedReturn(ExecutableElement method) {
		return processingEnv.getTypeUtils().isSubtype(method.getReturnType(), processingEnv.getElementUtils().getTypeElement(CommandResult.class.getName()).asType());
	}
	
}
