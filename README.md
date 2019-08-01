1.使用した技術要素  
言語:java,HTML,CSS  
フレームワーク:Spring Boot  
データベース:MySQL  
ネットワーク:localhost  
セキュリティ:未実装のためまだです  

2.全体の設計・構成についての説明
ソースコードはSpring Initializerでひな型を作成しました。  
src/main/java/com/example/demo配下に  
DemoApplication.java  
Todo.java  
TodoController.java  
TodoRepository.java  
の４つのファイルを置いています。  
DemoApplication.javaはメイン関数を呼び出すためのものです。  
Todo.javaは、データベースを利用するためにEntitiyアノテーション、Idアノテーションを付与させたクラスを作成しています。中身はgetter/setterになっています。  
TodoController.javaは,コントローラクラスを生成しています。RequestMappingアノテーションでマッピングし、トップ画面、編集画面、検索画面に遷移させています。トップ画面(index.html)ではデータベースの中身を表示させ、ToDo名、期限を入力し、追加ボタンを押すとデータベースに追加した後、全件表示させます。編集、検索画面は遷移のみ実装しています。  
TodoRepository.javaは、データベースを扱うためのレポジトリクラスを生成しています。  
src/main/resources/templates配下に  
base.html  
edit.html  
index.html  
search.html  
の４つのファイルを置いています。  
base.htmlは、共通ヘッダーです。トップ、検索画面に遷移します。  
edit.htmlは、編集画面です。入力フォームのみ記述していますが、特に何も動きません。  
index.htmlはトップ画面です。todo名、期限を入力してデータベースに書き込みます。下部にデータベースの内容を全て表示しています。  
search.htmlは検索画面です。まだ書けていません。  

3.開発環境のセットアップ手順  
javaのバージョン:1.8.0_221  
JDKのバージョン:jdk1.8.0_221  
開発環境:IntelliJ IDEA Community Edition 2019.1.3  
MySQLのバージョン:8.0  
開発環境にはIntelliJ IDEA　Community Edition 2019.1.3を利用しています。  
Jetbrainsのサイトより無料版をダウンロードし、インストールウィザードに従いインストールしました。その後、oracleのサイトよりJDKをインストールしました。

4.出来ているところ、出来ていないところ
トップ画面  
出来ているところ  
・共通ヘッダを含む各画面の遷移  
・データベースへのToDoの新規登録
・データベースの全件表示
・デフォルトは未完了
出来ていないところ（＋今考えているアイデア）  
・新規登録時に特殊文字の特殊文字があった場合のエスケープ→if文でエラーを分岐させる
・エラーメッセージ→入力フォームに空白送信、３１文字以上、ToDo名被りがあった場合、それを表示(html側？かコントローラ側？)  
・ToDo内容表示（期限、作成日を年月日で表示、編集、未完了・完了ボタン）→Date型を無理やり年月日表示にする？　編集ボタンを押したらその名前、期限を編集画面で表示をどうやってさせるか（変数で渡す？）　未完了、完了はデータベースのbit型の値で判断して表示させる  
・Todoが一つもない場合のメッセージ表示→データベースの中身が空の場合、表示

編集画面
まだ実装に手を出せていません。  
・ToDoの内容を変更可能な状態で表示する→トップ画面から渡された値をhtml側でそのまま入力フォームに表示させる  
・更新→updateのクエリを実行させる？要調査  

検索画面  
まだ実装に手を出せていません  
・検索機能→要調査  
・件数表示→該当するToDoの個数をカウントして表示  
・作成日が新しい順→ソートして表示  
・エラーメッセージ→該当するToDoの数が０の時表示  

5.その他  
SpringBootを使うのが初めてであることと、月末にあった学会のため中々手をつけることが出来ず、8/2現在ではデータベースに新規登録するところまでしか実装できませんでした。今後も完成を目指して開発を続けていきます。   
余計なコードが多かったり、変なコメントアウトが多く読み辛いと思いますが、アドバイス、修正点等がありましたらコメントお願いします。 
また、実装で行き詰まったらまたご相談させて頂きます。ご迷惑をおかけしますが、よろしくお願い申し上げます。  
８月中には仕様書の要件をすべて満たすToDoリストを完成させ、再度提出しようと思います。  
何卒よろしくお願いします。
