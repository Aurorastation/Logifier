package backend;

public class logColorArray {
	private static String[] logColor = new String[logger.getFileSize(false)];
	private static String[] searchedColor;

	public static void setColor(int arrayNum, String givenString, boolean search) {
		if(search == true) {
			logColorArray.searchedColor[arrayNum] = givenString;
		}
		else {
			logColorArray.logColor[arrayNum] = givenString;
		}
	}
	public static String getColor(int arrayNum, boolean search) {
		if(search == true) {
			return logColorArray.searchedColor[arrayNum];
		}
		else {
			return logColorArray.logColor[arrayNum];
		}
	}

	public static void initSearchArr(int arrSize) {
		logColorArray.searchedColor = new String[arrSize];
	}
}