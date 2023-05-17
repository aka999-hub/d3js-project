import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVFileChecker {
    public static void main(String[] args) {
        String csvFile = "data.csv"; // CSVファイルのパス
        String[] inputArray = {"apple", "banana", "orange"}; // 入力パラメータの配列データ

        // CSVファイルのデータを配列に格納
        String[] csvArray = readCSVFile(csvFile);

        // 配列データとCSVファイルのデータが一致するかチェック
        boolean match = checkArrayMatch(inputArray, csvArray);

        if (match) {
            System.out.println("入力パラメータの配列データとCSVファイルのデータが一致しました。");
        } else {
            System.out.println("入力パラメータの配列データとCSVファイルのデータが一致しませんでした。");
        }
    }

    // CSVファイルのデータを配列に格納するメソッド
    private static String[] readCSVFile(String csvFile) {
        String line;
        String[] dataArray = null;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // CSVファイルの内容をカンマで分割し、配列に格納
                dataArray = line.split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataArray;
    }

    // 配列データとCSVファイルのデータが一致するかチェックするメソッド
    private static boolean checkArrayMatch(String[] inputArray, String[] csvArray) {
        // 配列の要素数が異なる場合は一致しないと判断
        if (inputArray.length != csvArray.length) {
            return false;
        }

        for (int i = 0; i < inputArray.length; i++) {
            // 配列の要素が一致しない場合は一致しないと判断
            if (!inputArray[i].equals(csvArray[i])) {
                return false;
            }
        }

        return true; // 配列の要素がすべて一致した場合は一致すると判断
    }
}


// ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;

// public class CSVFileChecker {
//     public static void main(String[] args) {
//         String csvFile = "data.csv"; // CSVファイルのパス
//         String[] inputArray = {"apple", "banana", "orange"}; // 入力パラメータの配列データ

//         // CSVファイルのデータを配列に格納
//         String[][] csvData = readCSVFile(csvFile);

//         // 配列データとCSVファイルのデータが一致するかチェック
//         boolean match = checkArrayMatch(inputArray, csvData);

//         if (match) {
//             System.out.println("入力パラメータの配列データとCSVファイルのデータが一致しました。");
//         } else {
//             System.out.println("入力パラメータの配列データとCSVファイルのデータが一致しませんでした。");
//         }
//     }

//     // CSVファイルのデータを二次元配列に格納するメソッド
//     private static String[][] readCSVFile(String csvFile) {
//         String line;
//         String[][] dataArray = null;

//         try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//             String[] headers = br.readLine().split(","); // ヘッダー行を読み込んでカンマで分割

//             // データ行の数を数える
//             int rowCount = 0;
//             while (br.readLine() != null) {
//                 rowCount++;
//             }

//             // データ行の数とヘッダーの要素数で二次元配列を初期化
//             dataArray = new String[rowCount][headers.length];

//             // CSVファイルのデータを二次元配列に格納
//             br.close(); // ファイルを閉じて先頭に戻る
//             br = new BufferedReader(new FileReader(csvFile)); // 新たにファイルを開く
//             br.readLine(); // ヘッダー行をスキップ
//             int rowIndex = 0;
//             while ((line = br.readLine()) != null) {
//                 String[] values = line.split(","); // 行のデータをカンマで分割
//                 for (int i = 0; i < headers.length; i++) {
//                     dataArray[rowIndex][i] = values[i]; // 分割したデータを二次元配列に格納
//                 }
//                 rowIndex++;
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }

//         return dataArray;
//     }

//     // 配列データとCSVファイルのデータが一致するかチェックするメソッド
//     private static boolean checkArrayMatch(String[] inputArray, String[][] csvData) {
//         if (inputArray.length != csvData[0].length) {
//             return false; // 配列の
//         // 配列の要素数とCSVファイルのヘッダーの要素数が一致しない場合は一致しないと判断
//         if (inputArray.length != csvData[0].length) {
//             return false;
//         }

//         // 配列データとCSVファイルのデータが一致するかチェック
//         for (String[] row : csvData) {
//             boolean rowMatch = true;
//             for (int i = 0; i < inputArray.length; i++) {
//                 // 配列の要素とCSVファイルのデータが一致しない場合は一致しないと判断
//                 if (!inputArray[i].equals(row[i])) {
//                     rowMatch = false;
//                     break;
//                 }
//             }
//             if (rowMatch) {
//                 return true; // 一致する行が見つかった場合は一致すると判断
//             }
//         }

//         return false; // 一致する行が見つからなかった場合は一致しないと判断
//     }
// }
