package jp.hexachord.reader;

public class RateReaderFactory {

	public static AbstractRateReader GetRateReader(int mode) {
		switch (mode) {
		case 1:
			return new RateDetailsReader();
		case 2:
			return new RateAverageReader();
		default:
			return null;
		}
	}
}
