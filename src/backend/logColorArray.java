package backend;

public class logColorArray {
    private static String[] type = new String[logger.getFileSize()];
    private static String[] sType;
    
    public static void setType(int arrayNum, String type) {
        logColorArray.type[arrayNum] = type;
    }
    public static String getType(int arrayNum) {
        return type[arrayNum];
    }
    
    public static void initSearchArr(int arrSize) {
        sType = new String[arrSize];
    }
	
    public static void setSType(int arrayNum, String type) {
        logColorArray.sType[arrayNum] = type;
    }
    
    public static String getSType(int arrayNum) {
        return sType[arrayNum];
    }
}
