package backend;

public class logColorArray {
	private static String[] type = new String[logger.getFileSize(false)];
	private static String[] sType;

	public static void setColor(int arrayNum, String type, boolean search) {
		if(search == true) {
			logColorArray.sType[arrayNum] = type;
		}
		else {
			logColorArray.type[arrayNum] = type;
		}
	}
	public static String getColor(int arrayNum, boolean search) {
		if(search == true) {
			return sType[arrayNum];
		}
		else {
			return type[arrayNum];
		}
	}

	public static void initSearchArr(int arrSize) {
		sType = new String[arrSize];
	}
}