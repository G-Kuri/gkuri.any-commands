# any-commands

any-commands は、Java 21以降に対応したコマンド解析・実行フレームワークです。

単純なCLIコマンドから、対話型シェル、サーバ管理コンソール、GUIアプリケーションまで、さまざまな操作インターフェースで共通して利用できるコマンド基盤を提供します。

コマンドの定義、引数の型変換・検証、入力補完、権限制御、イベント監視、非同期実行など、コマンドの実装と運用に必要な仕組みを統一されたAPIで扱えます。

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

## 実装例

詳細な実装例は以下を参照してください。

独自コマンドの作成
型付き引数の利用
サブコマンドの実装
対話型コマンドの実装
イベントリスナの実装

※サンプルは今後追加予定


### API(執筆中)

#### インターフェイス

##### Command

###### 必須実装メソッド
- String getName()
  - コマンド名を返すメソッドです。
- String getDescription()
  - コマンドの簡易説明を返すメソッドです。
- CommandResult execute(CommandArgument args)
  - コマンドの実際の処理を行うメソッドです。
  - NULLを返すと例外となります。

###### 任意実装メソッド
- CommandArgsType getArgsType()
  - 引数の型を定義できるメソッドです。
  - 複数の引数パターンをオーバーロードして定義できます。
  - CommandArgument.getArgsTypeNumberで返される値はここで何番目に登録したパターンが使用されているかを意味します。
- int getMinArgsCount()
  - 引数の最低数を定義できます。
- int getMaxArgsCount()
  - 引数の最大数を定義できます。
- SubCommands getSubCommands()
  - Commandインターフェースを実装したクラスをサブコマンドとして定義できます。
  - InteractiveCommandインターフェースを実装したクラスをサブコマンドとすると例外となります。
- boolean canExecuteCommand(Object executeUser)
  - コマンドを実行したユーザがこのコマンドを実行できるか否かを定義できます。
  - falseを返せば実行をブロックします。
  - Object型を引数にとるため自前実装のユーザをそのまま渡すことができます。
  - sudoコマンド経由でコマンドが実行された場合はスキップされます。
- String getHelp()
  - コマンドの詳細な説明を返すメソッドです。
- Object stringArgObjectMapper(String arg)
  - String型引数を自前のルールに則ってJavaオブジェクトに変換するためのメソッドです。
  - 例えば"@user:gkuri"という文字列を"gkuri"というUser型へ変換する処理等を書くことができます。
  - 引数型判定はこのメソッドが実行された後に行われます。
- String[] getAlias()
  - コマンド名のエイリアスを定義できます。
  - エイリアスが他のコマンドと重複した場合はエイリアス数が少ないコマンドが優先的に登録されます。
- CommandSuggestion[] getArgSuggestions(int[] argsTypeNumbers, int argsPos, CommandArgument args)
  - 補完を要求された際に実行されるメソッドです。
  - サブコマンドは自動的にサジェストが生成されるため、ここでは引数のみのサジェストを返してください。
  - 各引数の詳細はJavaDocを参照ください。
- CommandResult catchException(Exception exception, CommandArgument args)
  - executeメソッド内で例外が発生した際に呼び出されるメソッドです。
  - CommandResultを返すことで例外メッセージ以外の実行結果に差し替えることができます。
- Command getNewInteractiveInstance(Object interactiveData)
  - コマンドの実行を対話モードへ移行する際に呼び出されます。
  - コマンドクラスはステートレスな想定なので、ここでステートフルな対話用クラスの新規インスタンスを返してください。
  - 対話用クラスのインスタンスを初期化する際に使用したいデータはCommandResultへ渡すと引数のinteractiveDataから受け取ることができます。
- boolean isHiddenCommand()
  - trueを返すとコマンドがサジェストに表れなくなります。
  - 実行自体を制限するものではありません。

##### InteractiveCommand

##### CommandEventListener

##### ExecuteArgsTypeNumber(アノテーションインターフェイス)

##### ExperimentalCommand(アノテーションインターフェイス)

#### DTO

##### CommandArgument

##### CommandResult

##### CommandArgsType

##### CommandSuggestion

##### SubCommands

#### Builder

##### CommandArgumentBuilder

##### CommandResultBuilder

##### CommandManagerBuilder

#### その他

##### CommandManager(実行制御クラス)

##### CommandResultStatus(enum)

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