# d3js-project


### 1 ローカルリポジトリを作成する
git init

### 2 ローカルリポジトリにコミットする
git add index.html
git commit -m "[Add] index"

### 3 リモートリポジトリにプッシュする
#### ブランチ名を「main」に変更
git branch -M main
git remote add origin git@github.com:aka999-hub/d3js-project.git
git push -u origin main

※`git remote add origin`実行して下記エラーが発生する場合、Gitリポジトリのリモートの設定が既に存在している場合に表示される
`error: remote origin already exists.`
新たに別のリモートリポジトリを使用する場合は、既存のリモートを削除する
git remote remove origin