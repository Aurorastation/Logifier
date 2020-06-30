package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class logger {

	private static int fileSize = 0;
	private static int searchedSize = 0;
	private static String[] logArr;
	private static String[] searchArr;
	private static File logFile = new File(".");
	private static String logID;
	private static String date;

	public static void populateLog(String[] searchWords) {
		Scanner fileScan;
		try {
			File curDir = new File(".");
			File[] filesList = curDir.listFiles();
			for(File f : filesList) {
				if(f.isFile()) {
					if(f.getName().equals("logifier.log") || f.getName().equals("logifier.txt")) {
						logFile = f;
						break;
					}
				}
			}

			fileScan = new Scanner(logFile);

			boolean searching = false;
			int searchWordsLength = 0;
			if(searchWords != null && searchWords.length > 0) {
				searching = true;
				searchedSize = 0;
				for(int i = 0; i < searchWords.length; i++) {
					if(searchWords[i] != null && searchWords[i].length() > 0) {
						searchWordsLength++;
					}
				}
			}
			else {
				fileSize = 0;
			}

			String lineHolder;
			while(fileScan.hasNextLine()) {
				lineHolder = fileScan.nextLine();
				if(searching == true) {
					for(int i = 0; i < searchWordsLength; i++) {
						if(searchWords[i].length() > 0 && lineHolder.toLowerCase().contains(searchWords[i].toLowerCase())) {
							searchedSize++;
							break;
						}
					}
				}
				else {
					fileSize++;
				}
			}
			fileScan.close();

			if(searching == true) {
				logColorArray.initSearchArr(searchedSize);
				searchArr = new String[searchedSize];
			}
			else {
				logArr = new String[fileSize];
			}

			fileScan = new Scanner(logFile);

			boolean hasWord = false;
			int searchArrCount = 0;
			int numToUse = 0;
			for(int i = 0; i < logger.getFileSize(false); i++) {
				lineHolder = fileScan.nextLine() + "\n";
				if(searching == true) {
					hasWord = false;
					for(int j = 0; j < searchWordsLength; j++) {
						if(searchWords[j].length() > 0 && lineHolder.toLowerCase().contains(searchWords[j].toLowerCase())) {
							hasWord = true;
							searchArr[searchArrCount] = lineHolder;
							break;
						}
					}
					if(hasWord == false) {
						continue;
					}
					numToUse = searchArrCount;
					searchArrCount++;
				}
				else {
					logArr[i] = lineHolder;
					numToUse = i;
				}
				if (lineHolder.contains("ADMIN")) {
					logColorArray.setColor(numToUse, "#B85B56", searching);
				} else if (lineHolder.contains("OOC")) {
					logColorArray.setColor(numToUse, "#5575A6", searching);
				} else if (lineHolder.contains("ATTACK")) {
					logColorArray.setColor(numToUse, "#BF569A", searching);
				} else if (lineHolder.contains("ACCESS")) {
					logColorArray.setColor(numToUse, "#506152", searching);
				} else if (lineHolder.contains("DEBUG")) {
					logColorArray.setColor(numToUse, "#506152", searching);
				} else if (lineHolder.contains("SS")) {
					logColorArray.setColor(numToUse, "#506152", searching);
				} else if (lineHolder.contains("GC")) {
					logColorArray.setColor(numToUse, "#506152", searching);
				} else if (lineHolder.contains("Mouse")) {
					logColorArray.setColor(numToUse, "#506152", searching);
				} else if (lineHolder.contains("TGS")) {
					logColorArray.setColor(numToUse, "#506152", searching);
				} else if (lineHolder.contains("TOPIC")) {
					logColorArray.setColor(numToUse, "#506152", searching);
				} else if (lineHolder.contains("MASTER")) {
					logColorArray.setColor(numToUse, "#506152", searching);
				} else if (lineHolder.contains("VOTE")) {
					logColorArray.setColor(numToUse, "#506152", searching);
				} else if (lineHolder.contains("GAME")) {
					logColorArray.setColor(numToUse, "#506152", searching);
				} else {
					logColorArray.setColor(numToUse, "#A8D1A5", searching);
				}
			}
			if(searching == false) {
				Integer IDIndex = logArr[2].indexOf("ID: ");
				IDIndex = IDIndex + 4;
				Integer bracketIndex = logArr[2].indexOf(")");
				logID = logArr[2].substring(IDIndex, bracketIndex);

				Integer dateOpenIndex = logArr[0].indexOf("[");
				date = logArr[0].substring(dateOpenIndex + 1, dateOpenIndex + 12);
			}
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "The logifier text file not found! Make sure you named it correctly! Logs will be bugged!");
		}
	}

	public static int getFileSize(boolean search) {
		if(search == true) {
			return searchedSize;
		}
		else {
			return fileSize;
		}
	}

	public static String getLogString(int arrNum, boolean searched) {
		String returnString = "";
		if(searched == true) {
			returnString = searchArr[arrNum];
		} else {
			returnString = logArr[arrNum];
		}
		returnString = returnString.replaceAll("&#34;", "\"");
		returnString = returnString.replaceAll("&#39;", "'");
		if (arrNum != 0) returnString = returnString.replaceAll(date, "");
		if (arrNum != 2) returnString = returnString.replaceAll(logID + " ", "");
		return returnString;
	}
}