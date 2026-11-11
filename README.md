# any-commands

Java向け統合コマンド実行フレームワーク

CLI・GUI・Javaコード。コマンドを一度定義すれば、さまざまなインターフェースから実行できます。

any-commandsは、Javaアプリケーションにコマンド実行機能を提供する統合フレームワークです。

引数解析、型変換、入力検証、サブコマンド、パイプライン、権限制御、非同期実行など、コマンド実行に必要となるさまざまな機能を一つのライブラリで提供します。

複数のライブラリを組み合わせたり、機能ごとに独自の実装を用意したりすることなく、一貫した仕組みでコマンド実行環境を構築できます。

---

## 特徴

統合されたコマンド実行基盤 — コマンドに必要なさまざまな機能を一つのフレームワークで利用できます。

複数のインターフェースに対応 — CLIだけでなく、GUIやJavaコードからもコマンドを利用できます。

豊富な標準機能 — 引数解析、型変換、検証、補完、権限制御などを提供します。

高度な実行制御 — パイプライン、非同期実行、同時実行数制限などに対応します。

拡張可能な設計 — イベントフックなどを利用して、アプリケーションに合わせた動作を構築できます。

---

## なぜ any-commands なのか？

Javaアプリケーションにコマンド実行機能を実装するとき、必要になるのは単純な引数解析だけではありません。

例えば、次のような機能が必要になることがあります。

- コマンドや引数の解析
- 文字列から適切な型への変換
- 入力値の検証
- サブコマンドの管理
- コマンドの実行権限管理
- 非同期実行や実行数の制御
- コマンドの補完や入力ミスのサジェスト
- GUIからのコマンド実行

これらを個別のライブラリで実現しようとすると、それぞれのAPIや設計思想を理解し、機能同士を連携させる必要があります。

また、ライブラリ間のデータ変換やエラー処理、実行ライフサイクルの調整なども必要になる場合があります。

any-commandsは、こうしたコマンド実行に関する機能を一つのフレームワークにまとめます。

必要な機能を個別に組み合わせるのではなく、共通の仕組みの上で利用できることが大きなメリットです。

One Command, Multiple Interfaces

any-commandsの大きな特徴は、コマンドを実行するインターフェースを限定しないことです。

同じコマンド実行基盤を、異なるインターフェースから利用できます。

| インターフェース | 用途 |
| --- | --- |
| CLI | ターミナルからのコマンド実行 |
| GUI | デスクトップアプリケーションなどからのコマンド実行 |
| Java API | Javaプログラムからの直接実行 |

例えば、アプリケーションの機能をコマンドとして定義しておけば、CLIとGUIでそれぞれ異なる処理を実装する必要を減らせます。

コマンドの定義と実行ロジックを共通化することで、インターフェースごとの実装の重複を抑えられます。

---

## 主な機能

### コマンド定義・解析

- コマンドの解析と実行
- 引数の解析
- サブコマンド
- 引数の型変換
- 入力値の検証

### 入力支援

- Tab Completion
- コマンドや引数の入力補完
- Typo Suggestions
- 入力ミスに対する候補の提示

### 実行制御

- コマンドの権限制御
- 非同期実行
- 同時実行数の制限
- パイプライン実行

### 拡張機能

- イベントフック
- 実行前後の処理への介入
- アプリケーションに応じた機能拡張

※各機能の詳細な仕様や利用方法については、後述のドキュメントを参照してください。

---

## 利用シーン

any-commandsは、さまざまなJavaアプリケーションで利用できます。

### CLIツール

高機能なコマンドラインツールを構築できます。

引数解析や入力検証、サブコマンド、補完などを個別に実装する必要を減らせます。

### デスクトップアプリケーション

GUIからコマンドを実行する仕組みを構築できます。

CLIと共通のコマンド実行基盤を利用することで、操作インターフェースが異なっても処理を共通化できます。

### 開発者向けツール

複雑なコマンド体系やパイプラインを持つ開発者向けツールにも利用できます。

### 業務アプリケーション

アプリケーション内部の操作をコマンドとして体系化し、権限制御や実行制御を組み合わせた仕組みを構築できます。

---

## 他のCLIライブラリとの違い

Javaには、CLIを構築するための優れたライブラリがいくつも存在します。

any-commandsは、それらと同じくCLIの構築にも利用できますが、CLIの引数解析だけにとどまらず、コマンドの実行基盤全体を提供することを目指しています。

比較項目

| 比較項目 | 一般的なCLIライブラリ | any-commands |
| --- | --- |
| 引数解析 | ○ | ○ |
| 型変換 | ○ | ○ |
| CLI実行 | ○ | ○ |
| GUIからの実行 | 基本的に対象外 | ○ |
| Java APIからの実行 | ライブラリによる | ○ |
| 権限制御 | 基本的に対象外 | ○ |
| パイプライン | 基本的に対象外 | ○ |
| 実行制御 | 限定的 | ○ |

※これはREADMEに記載された設計目標を基にした比較で、各ライブラリの実装可能性を否定するものではない。

---

## インストール

### 必要環境

Java 21 以上

### 導入方法

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

## Quick Start

最小構成はこの通りです。

```java
CommandManager manager = new CommandManagerBuilder()
				.addEchoCommand(true)
				.build();

System.out.println(manager.executeCommand(new CommandArgument("echo Hello World!")).getResult());
// 結果 : Hello World!
```

例として、2値の和を返す`add`コマンドを実装する場合は以下の通りです。

```java
public class AddCommand implements Command {

	@Override
	public String getName() {
		return "add"; // コマンド名を定義します。
	}

	@Override
	public String getDescription() {
		return "引数の2値の和を返します"; // コマンドの簡易説明を定義します。
	}
	
	@Override
	public int getMaxArgsCount() {
		return 2; // 引数の個数の最大値を定義します。
	}
	
	@Override
	public int getMinArgsCount() {
		return 2; // 引数の個数の最小値を定義します。
	}
	
	@Override
	public CommandArgsType getArgsType() {
		return new CommandArgsType().addArgsTypePattern(Integer.class, Integer.class); // 受け付ける引数の型をInteger Integerのパターンに限定します。
	}

	@Override
	public CommandResult execute(CommandArgument args) {
		return new CommandResult(args.getArgInteger(0) + args.getArgInteger(1)); // CommandResultとして第一引数と第二引数の合計値を返します。
	}

}
```

実行は以下の通りです。

```java
CommandManager manager = new CommandManagerBuilder()
				.addCommand(new AddCommand())
				.build();

System.out.println(manager.executeCommand(new CommandArgument("add 123 456")).getResult());
// 結果 : 579
```

また、このライブラリの特徴であるJavaオブジェクトをそのまま引数に渡す方式で実行したい場合は

manager.executeCommandに渡す引数の`new CommandArgument("add 123 456")`を以下の3パターンのいずれかに変更します。

```java
new CommandArgumentBuilder().setCommand("add").setArgs(123, 456).build();

new CommandArgumentBuilder().setCommand("add").addArg(123).addArg(456).build();

new CommandArgumentBuilder().setCommandAndArgs("add", 123, 456).build();
```

`new CommandArgument("add 123 456")`も含め、CommandArgumentは完全に同一となるため

どの方式で生成されたCommandArgumentなのかCommand側では一切分かりません。

Quick Startとして示す例は以上となります。

詳しい使い方についてはドキュメントを、API仕様はJavaDocを参照してください。

## FAQ

Q. CLI専用のライブラリですか？

いいえ。CLIだけでなく、GUIやJavaコードからもコマンドを実行するための仕組みを提供します。

Q. 複数のライブラリを組み合わせる必要はありますか？

any-commandsが提供する機能については、一つのフレームワーク内で利用できます。個別のライブラリを組み合わせる手間を減らせます。

Q. Javaのアプリケーションに組み込めますか？

はい。Javaアプリケーションのコマンド実行基盤として利用できます。

Q. Javaのバージョン要件は？

Java 21以上を対象としています。

## ライセンス

LICENSE を参照してください。

### 商用利用について（任意）

商用利用時にご連絡いただけると嬉しいですが、必須ではありません。

連絡先:`gkuri730@gmail.com`

## Links

整備中...