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


----------------------------------
Javascriptについて質問
背景画像を設定してwindowをリサイズした時、画像サイズの情報を取得する方法を教えて下さい。


JavaScriptを使用して、背景画像のサイズを取得するには、以下の手順に従います。

1.背景画像を設定している要素を取得します。一般的には、body要素に背景画像を設定することが多いため、document.bodyを使用してbody要素を取得します。

2.背景画像のサイズ情報を含むCSSプロパティを取得します。背景画像のサイズ情報は、background-sizeプロパティによって指定されます。したがって、以下のようにgetComputedStyle()メソッドを使用して、背景画像を設定している要素のcomputed styleオブジェクトから、background-sizeプロパティを取得します。

const backgroundImageSize = window.getComputedStyle(document.body).getPropertyValue('background-size');


3.取得したbackground-sizeプロパティの値から、背景画像のサイズ情報を抽出します。background-sizeプロパティの値は、CSSのサイズ指定方法（px、em、%、cover、containなど）によって異なります。したがって、サイズ情報を正しく解析する必要があります。以下は、背景画像のサイズ情報を抽出するための例です。

let backgroundImageWidth, backgroundImageHeight;

if (backgroundImageSize === 'cover' || backgroundImageSize === 'contain') {
  // 背景画像のサイズが'cover'または'contain'の場合は、windowのサイズを使用する
  backgroundImageWidth = window.innerWidth;
  backgroundImageHeight = window.innerHeight;
} else {
  // 背景画像のサイズがpx、em、%などの場合は、値を解析する
  const sizeValues = backgroundImageSize.split(' ');
  backgroundImageWidth = parseFloat(sizeValues[0]);
  backgroundImageHeight = sizeValues.length > 1 ? parseFloat(sizeValues[1]) : 'auto';
}

このコードでは、背景画像のサイズが'cover'または'contain'の場合は、windowのサイズを使用し、それ以外の場合は、背景画像のサイズ情報を解析して、背景画像の幅と高さを取得します。

完全なコード例を以下に示します。

function getBackgroundImageSize() {
  const backgroundImageSize = window.getComputedStyle(document.body).getPropertyValue('background-size');
  let backgroundImageWidth, backgroundImageHeight;

  if (backgroundImageSize === 'cover' || backgroundImageSize === 'contain') {
    // 背景画像のサイズが'cover'または'contain'の場合は、windowのサイズを使用する
    backgroundImageWidth = window.innerWidth;
    backgroundImageHeight = window.innerHeight;
  } else {
    // 背景画像のサイズがpx、em、%などの場合は、値を解析する
    const sizeValues = backgroundImageSize.split(' ');
    backgroundImageWidth = parseFloat(sizeValues[0]);
    backgroundImageHeight = sizeValues.length > 1

 ? parseFloat(sizeValues[1])
  : 'auto';

}

return { width: backgroundImageWidth, height: backgroundImageHeight };
}

// windowがリサイズされた時に背景画像のサイズを取得する
window.addEventListener('resize', function() {
const backgroundImageSize = getBackgroundImageSize();
console.log(Background image size: ${backgroundImageSize.width} x ${backgroundImageSize.height});
});


このように、上記のコードを使用することで、ウィンドウのリサイズに応じて背景画像のサイズ情報を取得できます。ただし、背景画像がCSSで複雑に指定されている場合には、正しく解析できない可能性があるため、注意が必要です。


---------------------
また、背景画像が複数の画像で構成されている場合、background-sizeプロパティは各画像のサイズ指定方法をカンマで区切ったものとなります。例えば、以下のようなCSSがある場合、
body {
  background-image: url('image1.jpg'), url('image2.jpg');
  background-size: 50%, cover;
}

getComputedStyle()メソッドで取得したbackground-sizeプロパティの値は、'50%, cover'となります。この場合、背景画像のサイズを正しく解析するためには、各画像のサイズ情報をそれぞれ解析する必要があります。また、CSSで指定されている画像の数と、実際に読み込まれている画像の数が異なる場合もあります。この場合には、読み込まれている画像のサイズ情報を取得する必要があります。

以上のような注意点に留意した上で、上記のコードを使用して背景画像のサイズ情報を取得することができます。

また、先程のコードでは、背景画像がbody要素に指定されている場合を想定しています。もし背景画像が別の要素に指定されている場合には、上記のコードを適宜変更する必要があります。

例えば、以下のようなHTMLとCSSがある場合を考えてみましょう。

・html
<div class="container" style="background-image: url('image.jpg'); background-size: cover;"></div>

・css
.container {
  width: 100%;
  height: 100vh;
}

この場合、背景画像がdiv要素に指定されており、getComputedStyle()メソッドで取得するプロパティ名も'background-image'となります。また、背景画像のサイズ指定方法も'background-size'プロパティで指定されています。そのため、getComputedStyle()メソッドの引数にはdiv要素を指定し、background-sizeプロパティの値を解析する必要があります。

function getBackgroundImageSize() {
  const container = document.querySelector('.container');
  const backgroundImageStyle = getComputedStyle(container).getPropertyValue('background-image');
  const backgroundImageSizeStyle = getComputedStyle(container).getPropertyValue('background-size');

  // 背景画像のURLを取得する
  const imageUrl = backgroundImageStyle.replace(/^url\(["']?/, '').replace(/["']?\)$/, '');

  // 背景画像のサイズを解析する
  let backgroundImageWidth, backgroundImageHeight;
  if (backgroundImageSizeStyle === 'cover') {
    // cover指定されている場合は、コンテナーのサイズを背景画像のサイズとして扱う
    backgroundImageWidth = container.offsetWidth;
    backgroundImageHeight = container.offsetHeight;
  } else if (backgroundImageSizeStyle === 'contain') {
    // contain指定されている場合は、背景画像のアスペクト比を保ちながら、コンテナーに収まるようにサイズを調整する
    const containerAspect = container.offsetWidth / container.offsetHeight;
    const image = new Image();
    image.src = imageUrl;
    const imageAspect = image.width / image.height;
    if (containerAspect > imageAspect) {
      backgroundImageWidth = container.offsetHeight * imageAspect;
      backgroundImageHeight = container.offsetHeight;
    } else {
      backgroundImageWidth = container.offsetWidth;
      backgroundImageHeight = container.offsetWidth / imageAspect;
    }
  } else {
    // 数値が指定されている場合は、単位を除いた値を数値として解析する
    const sizeValues = backgroundImageSizeStyle.split(' ');
    backgroundImageWidth = sizeValues[0].slice(-1) === '%'
      ? container.offsetWidth * parseFloat(sizeValues[0]) / 100
      : parseFloat(sizeValues[0]);
    backgroundImageHeight = sizeValues[1].slice(-1) === '%'
      ? container.offsetHeight * parseFloat(sizeValues[1]) / 100
      : parseFloat(sizeValues[1])
      ? parseFloat(sizeValues[1])
      : 'auto';
  }

  return { width: backgroundImageWidth, height: backgroundImageHeight };
}

このコードでは、背景画像のURLを取得する部分は、先程のコードと同じです。background-sizeプロパティの値を解析する部分では、'cover'と'contain'については、先程のコードと同様に解析しています。数値が指定されている場合には、'%'で指定された相対値を、単位を除いた数値に変換して背景画像のサイズを計算しています。

このコードを使用する場合には、HTML側で背景画像を指定している要素のセレクタを適宜変更する必要があります。また、CSSで指定されているbackground-sizeプロパティの値が、'cover'や'contain'以外の場合についても、適切に解析する必要があります。






