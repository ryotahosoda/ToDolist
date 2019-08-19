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
TodoService.java  
の5つのファイルを置いています。  
DemoApplication.javaはメイン関数を呼び出すためのものです。  
Todo.javaは、データベースを利用するためにEntitiyアノテーション、Idアノテーションを付与させたクラスを作成しています。中身はgetter/setterになっています。  
TodoController.javaは,コントローラクラスを生成しています。RequestMappingアノテーションでマッピングし、トップ画面、編集画面、検索画面に遷移させています。トップ画面(index.html)ではデータベースの中身を表示させ、ToDo名、期限を入力し、追加ボタンを押すとデータベースに追加した後、全件表示させます。編集、検索画面は遷移のみ実装しています。  
TodoRepository.javaは、データベースを扱うためのレポジトリクラスを生成しています。  
TodoService.javaは、データベースに変更等を加えるための関数を生成しています。
src/main/resources/templates配下に  
base.html  
edit.html  
index.html  
search.html  
の４つのファイルを置いています。  
base.htmlは、共通ヘッダーです。トップ、検索画面に遷移します。  
edit.htmlは、編集画面です。編集ボタンがクリックされたら、ここに遷移して編集を行います。ToDo名と期限が予め入力フォームにあり、それを変更することができます。期限は年月日で入力させます。  
index.htmlはトップ画面です。todo名、期限を入力してデータベースに書き込みます。下部にデータベースの内容を全て表示しています。
search.htmlは検索画面です。入力フォームに検索ワードを入力すると、ToDo名であいまい検索を行います。検索が見つからなかった場合はその旨を表示します。見つかった場合は件数を表示して、内容も表示します。*未完了ボタンを押すと完了に変わってデータベースの内容を更新するところができていません。  

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
・ToDo内容表示
。編集機能
・あいまい検索機能
・件数表示

出来ていないところ（＋今考えているアイデア）  
・新規登録時に特殊文字の特殊文字があった場合のエスケープ→if文でエラーを分岐させる
・エラーメッセージ→入力フォームに空白送信、３１文字以上、ToDo名被りがあった場合、それを表示(html側？かコントローラ側？)    
・Todoが一つもない場合のメッセージ表示→データベースの中身が空の場合、表示

検索画面    
・作成日が新しい順→ソートして表示  
・未完了ボタンを押したら完了ボタンに変わってデータベースも更新される

5.その他  
SpringBootを使うのが初めてであることと、月末にあった学会のため中々手をつけることが出来ず、8/2現在ではデータベースに新規登録するところまでしか実装できませんでした。今後も完成を目指して開発を続けていきます。   
余計なコードが多かったり、変なコメントアウトが多く読み辛いと思いますが、アドバイス、修正点等がありましたらコメントお願いします。 
また、実装で行き詰まったらまたご相談させて頂きます。ご迷惑をおかけしますが、よろしくお願い申し上げます。  
８月中には仕様書の要件をすべて満たすToDoリストを完成させ、再度提出しようと思います。  
何卒よろしくお願いします。  

6.永山氏へ
トップ画面は未完了、完了ボタンを押すとボタンの表示を入れ替えて全件表示だから出来たけど、検索画面は、全件表示じゃなくて検索されたかつ未完了のものを表示させる必要があって、ボタンを押して未完了を完了に変更させたら制約に引っかかって検索できなくなってしまうからここの実装が出来ません。助けて下さい。  
とりあえず、エラーメッセージの表示とかは置いといて主要機能の実装を優先してやってます。
見た目は今HTNL/CSSネットで調べながらやろうとしてるから、全部の機能が使えるようになったら細かく変えようと思ってます。  
その他コード読んでもらってより良い実装方法とかまずいところあったらコメントくれたらありがたい！  
忙しいところ申し訳ないけど、よろしくお願いします。
