package gkuri.anycommands.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h3>Commandクラス内のメソッドに付けるアノテーションです。</h3>
 * 
 * <p>引数の型をオーバーロードするように複数登録されている場合、このアノテーションを付けることで
 * executeに代わってargs.getArgsTypePatternNumberが一致するメソッドが実行されます。</p>
 * 
 * 例えば、引数に期待する型としてInteger,String,Booleanを持っているコマンドに
 * <p>@ExecuteArgsTypeNumber(1)<br>public CommadnResult executeStringPattern(CommandArgument args)</p>
 * のようなメソッドが定義されていた場合、Integer又はBooleanが渡された際はexecuteが、
 * Stringが渡された際はexecuteStringPatternが実行されます。
 * 
 * <h4>このアノテーションを付けるメソッドは以下を満たす必要があります。</h4>
 * <li>Commandクラス内で定義されている</li>
 * <li>CommandArgumentを引数に取る</li>
 * <li>CommandResultを返す</li>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExecuteArgsTypeNumber {

	public int value();

}
