# any-commands

any-commands は、Java 21以降に対応したコマンド解析・実行フレームワークです。

単純なCLIコマンドから、対話型シェル、サーバ管理コンソール、GUIアプリケーションまで、さまざまな操作インターフェースで共通して利用できるコマンド基盤を提供します。

コマンドの定義、引数の型変換・検証、入力補完、権限制御、イベント監視、非同期実行など、コマンドの実装と運用に必要な仕組みを統一されたAPIで扱えます。

現状コマンド実行時の各種メッセージではja-jp及びen-usに対応しています。

## any-commands の特徴
- 型安全なコマンド実行 — 引数の型を定義し、実行前の検証と変換を自動化
- CLIに依存しない設計 — 文字列入力だけでなく、Javaオブジェクトを利用したコマンド実行にも対応
- 柔軟なコマンド構成 — サブコマンド、パイプ実行、対話型コマンドをサポート
- 実運用を支える機能 — 権限制御、イベントフック、実行履歴、非同期実行、同時実行数制限
- 拡張可能な入力支援 — コマンド補完、引数補完、Typoサジェストに対応

## コマンドを、文字列入力の枠から解放する
コマンドの処理と操作インターフェースを分離することで、同じコマンド基盤をCLIや独自コンソール、GUIなどから利用できます。

操作方法が変わっても、コマンドのロジックをそれぞれのUIに合わせて作り直す必要はありません。

any-commands は、単なるコマンドライン解析ライブラリではなく、Javaアプリケーションの操作を共通化するためのコマンド基盤を目指しています。

元々は anylib にて実装されていた command パッケージを、コマンド専用ライブラリとして抽出したものです。

https://github.com/G-Kuri/gkuri.anylib

---

## 特徴

### 既存ライブラリとの違い

多くのCLIライブラリは、コマンドライン引数の解析やサブコマンド管理を主な目的としていると思いますが

any-commands はそれに加えて、

- 引数の自動型変換
- 引数補完
- Typoサジェスト
- パイプ実行
- 対話型コマンド
- 権限制御
- イベントフック
- 非同期実行制御

など、実際のアプリケーション運用で必要になる機能を標準で提供します。

コマンド処理とUIを分離して設計できるため、CLIだけでなく独自コンソールやGUIとの連携も容易です。

また、**Javaの変数オブジェクトを直接引数にすることも可能**なため、GUIアプリケーションを

コマンドパターンで構築する際にも文字列のコマンドに縛られる必要がなく非常に便利で直感的です。

### こんな人におすすめ
- CLIツールを作りたい
- 対話型シェルを作りたい
- サーバ管理コンソールを実装したい
- コマンドごとの引数検証を毎回実装したくない
- 入力補完を実装したい
- 権限管理を組み込みたい
- パイプによるコマンド連携を利用したい
- イベント駆動でコマンドを監視したい
- UIとコマンド処理を分離して設計したい

## 設計思想

any-commands は、

- 簡単なことは簡単に
- 高度なことも実現できるように
- 最小限の設定で利用できるように
- 一貫性のあるAPIで扱えるように

という考え方で設計されています。

単純なCLIツールから、対話型コンソールや運用ツールまで同じAPIで実装できることを目標としています。

---

## クイックスタート

```Java
CommandManager manager = new CommandManagerBuilder().addEchoCommand(true).build();
 
CommandResult result = manager.executeCommand(new CommandArgument("echo Hello World"));
 
System.out.println(result.getResult());
```

出力

```Plain Text
Hello World
```

## 導入

gkuri.any-commands_jdk21_X.X.X.jar をプロジェクトへ追加してください。

Releases

https://github.com/G-Kuri/gkuri.any-commands/releases

### Gradle
```Gradle
dependencies {
implementation files("libs/gkuri.any-commands_jdk21_X.X.X.jar")
}
```

### Maven
```XML
<dependency>
<groupId>local</groupId>
<artifactId>any-commands</artifactId>
<version>X.X.X</version>
<scope>system</scope>
<systemPath>${project.basedir}/libs/gkuri.any-commands_jdk21_X.X.X.jar</systemPath>
</dependency>
```

---

# コマンドエンジン

コマンドの登録・管理・解析・実行を行うためのエンジンです。

CLIアプリケーションやサーバ管理機能など、コマンドベースの操作を必要とするアプリケーションで利用できます。

---

## 主な機能
### コマンド管理
- コマンド登録
- エイリアス
- 隠しコマンド
- 権限制御

### 引数解析
- 引数数チェック
- 引数型チェック
- 引数の自動型変換
- 複数パターン定義
- オプション解析

### 階層構造
- サブコマンド
- サブコマンドイベント監視

### 入力支援
- コマンド補完
- 引数補完
- Typoサジェスト

### 実行制御
- 引数型別専用メソッド
- 実行結果管理
- イベントフック
- 非同期実行
- 実行履歴

### 高度な機能
- 対話型コマンド
- パイプ実行
- sudo実行

---

## 型安全な引数解析

コマンドごとに受け取る引数の型を定義できます。

### 対応している主な型

- String
- Integer
- Long
- Double
- Boolean
- Character
- LocalDate
- LocalTime
- LocalDateTime

また、独自オブジェクトへの変換も可能です。

引数は実行前に自動検証・自動変換されます。

---

## サブコマンド

階層構造を持つコマンドを実装できます。

例

```Plain Text
user add kuri
user remove kuri
user list
```

コマンドルーティングはライブラリ側で自動的に処理されます。

---

## オプション解析

短縮形式とロング形式の両方に対応します。

例

```Plain Text
copy -rf
Plain Text
copy --recursive --force
```

コマンド内では

```Java
args.hasOption("r");
args.hasOption("f");
 
args.hasOption("recursive");
args.hasOption("force");
```

のように利用できます。

---

## 入力補完

コマンド名だけでなく、サブコマンドや引数候補の補完にも対応しています。

独自コンソールやGUIと連携するための補完候補取得APIを提供します。

---

## Typoサジェスト

存在しないコマンドが実行された際、類似コマンドを提案できます。

例

```Plain Text
hepl
```
↓
```Plain Text
コマンドが見つかりません。(もしかして...help)
```

## パイプ実行

コマンド同士をパイプで接続できます。

```Plain Text
echo hello | upper
```

後続コマンドは前段の実行結果を受け取れます。

---

## 対話型コマンド

複数回の入力を必要とするコマンドを実装できます。

例

```Plain Text
login
↓
ID :
↓
admin
↓
Password :
↓
******
↓
Login Success
```

ライブラリ側で

セッション管理
状態の引継ぎ
対話ID管理

をサポートします。

---

## イベントシステム

コマンド実行時のイベントを監視できます。

### グローバルイベント

すべてのコマンド実行を監視します。

### コマンド単位イベント

特定コマンドのみ監視します。

### イベントタイミング
- 実行前
- 実行後
  - SUCCESS
  - WARNING
  - ERROR
  - RUNNING
  - UNEXECUTED

---

## 非同期実行

コマンドを非同期で実行できます。

```Java
CompletableFuture<CommandResult> future = manager.executeCommandAsync(args);
```
また、

- インスタンス単位の同時実行数制限
- JVM全体の同時実行数制限

を設定できます。

---

## 標準コマンド

必要なものだけを Builder から有効化できます。

| コマンド | 説明 |
| --- | --- |
| help | ヘルプ表示 |
|echo | テキスト出力 |
|history | 実行履歴表示 |
|sudo | 権限昇格実行 |

---

## 実装例(執筆中)

ここでは、各機能を利用する実装例を紹介します。

クラス、メソッド別のドキュメントはJavaDocをご参照ください。

### CommandManagerBuilderから標準機能の有効/無効を設定
```java
CommandManager manager = new CommandManagerBuilder()
		.addHelpCommand(true)
		.addEchoCommand(true)
		.addHistoryCommand(true)
		.addSudoCommand(true, "pass") // パスワードがNULLか空文字だとtrueになりません
		.showTypoSuggest(true) // コマンドが見つからなかった際のメッセージに候補を含めるかを設定します
		.setHoldHistoriesCount(100)
		.setLanguage("ja-jp") // 現状ja-jpとen-usに対応しています
		.setMaxRunningAsyncCommandsCount(3)
		.setExecutor(Executors.newFixedThreadPool(3))
		.addExperimentalCommand(false)
		.build();
```

### 自分で作成したコマンドを追加

まず`Command`を実装したクラスを作成してください。

例として`add`コマンドを作成します。

```java
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
```

このクラスを`CommandManagerBuilder`から登録します。

```java
CommandManager manager = new CommandManagerBuilder()
				.addHelpCommand(true) // 今回はhelpも有効化しています
				.addCommand(new AddCommand())
				.build();
```

これを以下のように実行します。

```java
System.out.println(manager.executeCommand(new CommandArgument("help")).getResult());
		
System.out.println(manager.executeCommand(new CommandArgument("add 123")).getResult());

System.out.println(manager.executeCommand(new CommandArgument("add 123 456 789")).getResult());

System.out.println(manager.executeCommand(new CommandArgument("add Hello World")).getResult());

System.out.println(manager.executeCommand(new CommandArgument("add 123 456")).getResult());
```

すると、以下のような出力となります。

```Plain Text
add - 引数の2値の和を返します
help - コマンド一覧を返します。

引数が不足しています。
引数が多すぎます。
引数の型が不正です。
579
```

これが基本的なコマンド作成から実行までの流れです。

### Javaオブジェクトを引数に

現在の

```java
new CommandArgument("add 123 456")
```

は、すべて文字列で入力する場合を想定していますが、Javaオブジェクトを直接引数にすることも可能です。

Javaオブジェクトを引数に入れる場合は以下のように引数を生成します。

```java
new CommandArgumentBuilder()
			.setCommand("add")
			.addArg(123)
			.addArg(456)
			.build();
```
又は
```java
new CommandArgumentBuilder()
			.setCommandAndArgs("add", 123, 456)
			.build();
```

### 引数型のオーバーロード

現在受け付ける引数型は`Integer`と`Integer`の組み合わせのみですが

引数型はオーバーロードすることができます。

例として、`add`コマンドを`Double`型に対応するように引数型をオーバーロードします。

`getArgsType`メソッドを以下のようにします。

```java
@Override
public CommandArgsType getArgsType() {
	return new CommandArgsType()
			.addArgsTypePattern(Integer.class, Integer.class)
			.addArgsTypePattern(Double.class, Double.class)
			.addArgsTypePattern(Integer.class, Double.class)
			.addArgsTypePattern(Double.class, Integer.class);
}
```

これで、`Double`型に対応できます。

ただし、このままでは`execute`メソッドで使用している`args.getArgInteger`で例外が発生します。

そのため、以下のように変更します。

```java
@Override
public CommandResult execute(CommandArgument args) {
	switch (args.getArgsTypePatternNumber()) {
		case 0:
			return new CommandResult(args.getArgInteger(0) + args.getArgInteger(1));
		case 1:
			return new CommandResult((Double)args.getArg(0) + (Double)args.getArg(1));
		case 2:
			return new CommandResult(args.getArgInteger(0) + (Double)args.getArg(1));
		case 3:
			return new CommandResult((Double)args.getArg(0) + args.getArgInteger(1));
		default:
			return new CommandResult("error", CommandResultStatus.ERROR);
	}
}
```

`args.getArgsTypePatternNumber`は、今回合致した引数のパターンが何番目に登録されたものかを返します。

### 引数型パターン別メソッドの定義

現在の実装ではswitchやif-else if-elseによる分岐が大量に発生してしまいます。

その際は`ExecuteArgsTypeNumber`アノテーションを使用することで回避が可能です。

このアノテーションを使用して先ほどの`execute`と概念的に同じ実装をする場合、以下のようになります。

```java
@Override
public CommandResult execute(CommandArgument args) {
	return new CommandResult("error", CommandResultStatus.ERROR);
}

@ExecuteArgsTypeNumber(0)
public CommandResult executePattern0(CommandArgument args) {
	return new CommandResult(args.getArgInteger(0) + args.getArgInteger(1));
}

@ExecuteArgsTypeNumber(1)
public CommandResult executePattern1(CommandArgument args) {
	return new CommandResult((Double)args.getArg(0) + (Double)args.getArg(1));
}

@ExecuteArgsTypeNumber(2)
public CommandResult executePattern2(CommandArgument args) {
	return new CommandResult(args.getArgInteger(0) + (Double)args.getArg(1));
}

@ExecuteArgsTypeNumber(3)
public CommandResult executePattern3(CommandArgument args) {
	return new CommandResult((Double)args.getArg(0) + args.getArgInteger(1));
}
```

以下加筆中。

---

## ステータス

Stable

※API追加や機能拡張は継続的に行われますが、通常利用向けの機能は安定しています。

---

## ライセンス
MIT License

このソフトウェアは MIT License のもとで公開されています。

- 商用利用可
- 改変可
- 再配布可
- 私的利用可

再配布時は著作権表示およびライセンス文を含めてください。

このソフトウェアは現状のまま（AS IS）提供され、いかなる保証もありません。

商用利用について（任意）

商用利用時にご連絡いただけると嬉しいですが、必須ではありません。

連絡先:

```Plain Text
gkuri730@gmail.com
```

ライセンス全文は LICENSE ファイルを参照してください。