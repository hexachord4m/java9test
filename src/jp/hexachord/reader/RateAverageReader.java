package jp.hexachord.reader;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jp.hexachord.dto.Rate;

/**
 * 為替データを読み込んで平均を出力する
 * @author Meiko, Akita
 */
public class RateAverageReader extends AbstractRateReader {

	/**
	 * 年月の平均値を出力
	 * @param data
	 * @param startYear
	 * @param endYear
	 */
	@Override
	void OutputData(List<String[]> data, int startYear, int endYear) {
		data.stream()
		.skip(3)	// 先頭3行のヘッダーをスキップ
		.map(r -> new Rate() {{ setDateStr(r[0]); setUsd(new BigDecimal(r[1])); }})	// 日付と米ドルのみ取得
		.filter(r ->  r.getYear() >= startYear)	// 開始年以降を抽出
		.filter(r ->  r.getYear() <= endYear)	// 終了年以前を抽出
		.collect(Collectors.groupingBy(r -> r.getYearMonthStr(), Collectors.averagingDouble(r -> r.getUsd().doubleValue())))	// doubleに変換して平均を算出
		.entrySet().stream()	// グループ化の結果をソートするためにstreamを再取得
		.sorted(Map.Entry.comparingByKey())	// キーの昇順でソート
		.forEach(r -> System.out.println(String.format("%s:%.2f", r.getKey(), r.getValue())));		// 小数点第2位まで表示
	}
}
