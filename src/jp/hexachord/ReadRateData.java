package jp.hexachord;

import jp.hexachord.exception.CustomException;
import jp.hexachord.reader.RateReaderFactory;

public class ReadRateData {

	private static final String FILE_PATH = "data\\\\market_quote.csv";
	/**
	 * 主にtry-with-resourcesによるファイル読み込みとStreamAPIを試すテスト。<br/>
	 * 為替データのCSVファイルを読み込んで、標準出力に出力する。<br/>
	 * CSVファイルはみずほ銀行が公開しているヒストリカルデータを使用。<br/>
	 * 将来的にはこれをAPI化してWebアプリで表示するようにしたい。
	 * @author Meiko, AKITA
	 * @param args[0] 出力対象(必須) 1:明細 2:月平均
	 * @param args[1] 開始年(任意) 未指定の場合はすべて
	 * @param args[2] 終了年(任意) 未指定の場合はすべて
	 */
	public static void main(String[] args) {
		int mode = -1;
		int startYear = 0;
		int endYear = 9999;

		// パラメータ取得
		if(args.length < 1) {
			System.out.println("[Usage]\n\tparam1:output type (1:details 2:average)\n\t"
					+ "param2:start date (option)\n\tparam3:end date (option)");
			System.exit(0);
		} else {
			// 出力対象
			try { mode = Integer.parseInt(args[0]); } catch(NumberFormatException e) {}
			// 終了年
			if(args.length > 2) {
				if(args[2] != null) {
					try { endYear = Integer.parseInt(args[2]); } catch(NumberFormatException e) {}
				}
			}
			// 開始年
			if(args.length >1) {
				if(args[1] != null) {
					try { startYear = Integer.parseInt(args[1]); } catch(NumberFormatException e) {}
				}
			}
		}

		if(mode == -1) {
			System.err.println(String.format("Invalid Mode. [%s]", args[0]));
			System.exit(1);
		}

		// エンコーディングの確認
//		System.out.println(System.getProperty("file.encoding"));

		// データ読み込み＆出力
		try {
			RateReaderFactory.GetRateReader(mode).ReadAndOutput(FILE_PATH, startYear, endYear);
		} catch (CustomException e) {
			System.err.println(String.format("File Read Error. [Error Code:%s]", e.getErrorNum()));
		}

	}
}
