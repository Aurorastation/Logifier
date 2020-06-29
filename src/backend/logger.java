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
	private static String fileName;
	private static String logID;

	public static void populateLog() {
		Scanner fileScan;
		try {
			File curDir = new File(".");
			File[] filesList = curDir.listFiles();
			for(File f : filesList) {
				if(f.isFile()) {
					if(f.getName().equals("logifier.log")) fileName = "logifier.log";
					if(f.getName().equals("logifier.txt")) fileName = "logifier.txt";
				}
			}

			fileScan = new Scanner(new File(fileName));

			while (fileScan.hasNextLine()) {
				fileSize++;
				fileScan.nextLine();
			}
			fileScan.close();

			logArr = new String[fileSize];

			fileScan = new Scanner(new File(fileName));

			for (int i = 0; i < fileSize; i++) {
				logArr[i] = fileScan.nextLine() + "\n";
				if (logArr[i].contains("ADMIN")) {
					logColorArray.setType(i, "ADMIN");
				} else if (logArr[i].contains("OOC")) {
					logColorArray.setType(i, "OOC");
				} else if (logArr[i].contains("ATTACK")) {
					logColorArray.setType(i, "ATTACK");
				} else if (logArr[i].contains("ACCESS")) {
					logColorArray.setType(i, "IGNORE");
				} else if (logArr[i].contains("DEBUG")) {
					logColorArray.setType(i, "IGNORE");
				} else if (logArr[i].contains("SS")) {
					logColorArray.setType(i, "IGNORE");
				} else if (logArr[i].contains("GC")) {
					logColorArray.setType(i, "IGNORE");
				} else if (logArr[i].contains("Mouse")) {
					logColorArray.setType(i, "IGNORE");
				} else if (logArr[i].contains("TGS")) {
					logColorArray.setType(i, "IGNORE");
				} else if (logArr[i].contains("TOPIC")) {
					logColorArray.setType(i, "IGNORE");
				} else if (logArr[i].contains("MASTER")) {
					logColorArray.setType(i, "IGNORE");
				} else if (logArr[i].contains("VOTE")) {
					logColorArray.setType(i, "IGNORE");
				} else if (logArr[i].contains("GAME")) {
					logColorArray.setType(i, "IGNORE");
				} else {
					logColorArray.setType(i, "BASIC");
				}
			}
			Integer IDIndex = logArr[2].indexOf("ID: ");
			IDIndex = IDIndex + 4;
			Integer bracketIndex = logArr[2].indexOf(")");
			logID = logArr[2].substring(IDIndex, bracketIndex);
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "The logifier text file not found! Make sure you named it correctly! Logs will be bugged!");
		}

	}

	public static void populateSearch(String search) {
		Scanner fileScan;
		try {
			String temp = "";
			fileScan = new Scanner(new File(fileName));

			searchedSize = 0;

			while (fileScan.hasNextLine()) {
				temp = fileScan.nextLine();
				if (temp.toLowerCase().contains(search.toLowerCase())) {
					searchedSize++;
				}
			}
			fileScan.close();

			searchArr = new String[searchedSize];
			logColorArray.initSearchArr(searchedSize);

			fileScan = new Scanner(new File(fileName));
			String temp2 = "";
			int searchPos = 0;

			for (int i = 0; i < fileSize; i++) {
				temp2 = fileScan.nextLine() + "\n";

				if (temp2.toLowerCase().contains(search.toLowerCase())) {
					searchArr[searchPos] = temp2;
					if (searchArr[searchPos].contains("ADMIN")) {
						logColorArray.setSType(searchPos, "ADMIN");
					} else if (searchArr[searchPos].contains("OOC")) {
						logColorArray.setSType(searchPos, "OOC");
					} else if (searchArr[searchPos].contains("ATTACK")) {
						logColorArray.setSType(searchPos, "ATTACK");
					} else if (searchArr[searchPos].contains("ACCESS")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("DEBUG")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("SS")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("GC")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("mouse")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("TGS")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("TOPIC")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("MASTER")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("VOTE")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("GAME")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else {
						logColorArray.setSType(searchPos, "BASIC");
					}
					searchPos++;
				}

			}

		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "The logifier text file not found! Make sure you named it correctly! Logs will be bugged!");
		}

	}

	public static void populateMultiSearch(String search, String search2){
		Scanner fileScan;
		try {
			String temp = "";
			fileScan = new Scanner(new File(fileName));

			searchedSize = 0;

			while (fileScan.hasNextLine()) {
				temp = fileScan.nextLine();
				if (temp.toLowerCase().contains(search.toLowerCase()) || temp.toLowerCase().contains(search2.toLowerCase())) {
					searchedSize++;
				}
			}
			fileScan.close();

			searchArr = new String[searchedSize];
			logColorArray.initSearchArr(searchedSize);

			fileScan = new Scanner(new File(fileName));
			String temp2 = "";
			int searchPos = 0;

			for (int i = 0; i < fileSize; i++) {
				temp2 = fileScan.nextLine() + "\n";

				if (temp2.toLowerCase().contains(search.toLowerCase()) || temp2.toLowerCase().contains(search2.toLowerCase())) {
					searchArr[searchPos] = temp2;
					if (searchArr[searchPos].contains("ADMIN")) {
						logColorArray.setSType(searchPos, "ADMIN");
					} else if (searchArr[searchPos].contains("OOC")) {
						logColorArray.setSType(searchPos, "OOC");
					} else if (searchArr[searchPos].contains("ATTACK")) {
						logColorArray.setSType(searchPos, "ATTACK");
					} else if (searchArr[searchPos].contains("ACCESS")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("DEBUG")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("SS")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("GC")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("mouse")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("TGS")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("TOPIC")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("MASTER")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("VOTE")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else if (searchArr[searchPos].contains("GAME")) {
						logColorArray.setSType(searchPos, "IGNORE");
					} else {
						logColorArray.setSType(searchPos, "BASIC");
					}
					searchPos++;
				}

			}

		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "The logifier text file not found! Make sure you named it correctly! Logs will be bugged!");
		}

	}

	public static String logString() {
		StringBuilder finalString = new StringBuilder("");
		for (int i = 0; i < logArr.length; i++) {
			finalString.append(logArr[i]).append("\n");
		}
		return finalString.toString();
	}

	public static int getFileSize() {
		return fileSize;
	}

	public static int getSearchedSize() {
		return searchedSize;
	}

	public static String getLogString(int arrNum) {
		String adjustedLogArr[] = new String[fileSize];
		adjustedLogArr[arrNum] = logArr[arrNum];
		adjustedLogArr[arrNum] = adjustedLogArr[arrNum].replaceAll("&#34;", "''");
		adjustedLogArr[arrNum] = adjustedLogArr[arrNum].replaceAll("&#39;", "'");
		if (arrNum != 2) adjustedLogArr[arrNum] = adjustedLogArr[arrNum].replaceAll(logID + " ", "");
		return adjustedLogArr[arrNum];
	}

	public static String getSearchedString(int arrNum) {
		String adjustedLogArr[] = new String[searchedSize];
		adjustedLogArr[arrNum] = searchArr[arrNum];
		adjustedLogArr[arrNum] = adjustedLogArr[arrNum].replaceAll("&#34;", "''");
		adjustedLogArr[arrNum] = adjustedLogArr[arrNum].replaceAll("&#39;", "'");
		if (arrNum != 2) adjustedLogArr[arrNum] = adjustedLogArr[arrNum].replaceAll(logID + " ", "");
		return adjustedLogArr[arrNum];
	}
}
