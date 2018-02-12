package jp.hexachord.reader;

import java.math.BigDecimal;
import java.util.List;

import jp.hexachord.dto.Rate;

/**
 * 為替データを読み込んで明細を出力する
 * @author Meiko, Akita
 */
public class RateDetailsReader extends AbstractRateReader {

	/**
	 * 明細を出力
	 * @param data
	 * @param startYear
	 * @param endYear
	 */
	@Override
	void OutputData(List<String[]> data, int startYear, int endYear) {
		data.stream()
		.skip(3)	// 先頭3行のヘッダーをスキップ
		.map(r -> new Rate() {{ setDateStr(r[0]); setUsd(new BigDecimal(r[1])); }})	// 日付と米ドルのみ取得
		.filter(r ->  Integer.parseInt(r.getDateStr().substring(0, r.getDateStr().indexOf("/"))) >= startYear)
		.filter(r -> Integer.parseInt(r.getDateStr().substring(0, r.getDateStr().indexOf("/"))) <= endYear)
		.forEach(r -> System.out.println(String.format("%s:%s", r.getDateStr(), r.getUsd())));
	}

}
