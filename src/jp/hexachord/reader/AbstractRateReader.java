package jp.hexachord.reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jp.hexachord.exception.CustomException;

public abstract class AbstractRateReader {

	public void ReadAndOutput(String filePath, int startYear, int endYear) throws CustomException {
		// CSVデータ読み込み
		List<String[]> data = fileToList("data\\market_quote.csv");

		// データ出力
		OutputData(data, startYear, endYear);
	}

	abstract void OutputData(List<String[]> data, int startYear, int endYear);

	/**
	 * CSVファイル読み込み
	 * @param filePath
	 * @return List<String[]>
	 * @throws CustomException
	 */
	private List<String[]> fileToList(String filePath) throws CustomException {
		List<String[]> lines = new ArrayList<>();
		// try-with-resources
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"Shift-JIS"))) {
			String line;
			while((line = reader.readLine()) != null) {
				lines.add(line.split(","));
			}
		} catch(FileNotFoundException e) {
			throw new CustomException(CustomException.FILE_NOT_FOUND, e);
		} catch(IOException e) {
			throw new CustomException(CustomException.FILE_READ_ERROR, e);
		}

		return lines;
	}

}
