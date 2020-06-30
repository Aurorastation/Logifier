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

	public static void populateLog() {
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

			while (fileScan.hasNextLine()) {
				fileSize++;
				fileScan.nextLine();
			}
			fileScan.close();

			logArr = new String[fileSize];

			fileScan = new Scanner(logFile);

			for (int i = 0; i < fileSize; i++) {
				logArr[i] = fileScan.nextLine() + "\n";
				if (logArr[i].contains("ADMIN")) {
					logColorArray.setColor(i, "#B85B56", false);
				} else if (logArr[i].contains("OOC")) {
					logColorArray.setColor(i, "#5575A6", false);
				} else if (logArr[i].contains("ATTACK")) {
					logColorArray.setColor(i, "#BF569A", false);
				} else if (logArr[i].contains("ACCESS")) {
					logColorArray.setColor(i, "#223620", false);
				} else if (logArr[i].contains("DEBUG")) {
					logColorArray.setColor(i, "#223620", false);
				} else if (logArr[i].contains("SS")) {
					logColorArray.setColor(i, "#223620", false);
				} else if (logArr[i].contains("GC")) {
					logColorArray.setColor(i, "#223620", false);
				} else if (logArr[i].contains("Mouse")) {
					logColorArray.setColor(i, "#223620", false);
				} else if (logArr[i].contains("TGS")) {
					logColorArray.setColor(i, "#223620", false);
				} else if (logArr[i].contains("TOPIC")) {
					logColorArray.setColor(i, "#223620", false);
				} else if (logArr[i].contains("MASTER")) {
					logColorArray.setColor(i, "#223620", false);
				} else if (logArr[i].contains("VOTE")) {
					logColorArray.setColor(i, "#223620", false);
				} else if (logArr[i].contains("GAME")) {
					logColorArray.setColor(i, "#223620", false);
				} else {
					logColorArray.setColor(i, "#A8D1A5", false);
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
			fileScan = new Scanner(logFile);

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

			fileScan = new Scanner(logFile);
			String temp2 = "";
			int searchPos = 0;

			for (int i = 0; i < fileSize; i++) {
				temp2 = fileScan.nextLine() + "\n";

				if (temp2.toLowerCase().contains(search.toLowerCase())) {
					searchArr[searchPos] = temp2;
					if (searchArr[searchPos].contains("ADMIN")) {
						logColorArray.setColor(searchPos, "#B85B56", true);
					} else if (searchArr[searchPos].contains("OOC")) {
						logColorArray.setColor(searchPos, "#5575A6", true);
					} else if (searchArr[searchPos].contains("ATTACK")) {
						logColorArray.setColor(searchPos, "#BF569A", true);
					} else if (searchArr[searchPos].contains("ACCESS")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("DEBUG")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("SS")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("GC")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("mouse")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("TGS")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("TOPIC")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("MASTER")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("VOTE")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("GAME")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else {
						logColorArray.setColor(searchPos, "#A8D1A5", true);
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
			fileScan = new Scanner(logFile);

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

			fileScan = new Scanner(logFile);
			String temp2 = "";
			int searchPos = 0;

			for (int i = 0; i < fileSize; i++) {
				temp2 = fileScan.nextLine() + "\n";

				if (temp2.toLowerCase().contains(search.toLowerCase()) || temp2.toLowerCase().contains(search2.toLowerCase())) {
					searchArr[searchPos] = temp2;
					if (searchArr[searchPos].contains("ADMIN")) {
						logColorArray.setColor(searchPos, "#B85B56", true);
					} else if (searchArr[searchPos].contains("OOC")) {
						logColorArray.setColor(searchPos, "#5575A6", true);
					} else if (searchArr[searchPos].contains("ATTACK")) {
						logColorArray.setColor(searchPos, "#BF569A", true);
					} else if (searchArr[searchPos].contains("ACCESS")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("DEBUG")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("SS")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("GC")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("mouse")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("TGS")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("TOPIC")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("MASTER")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("VOTE")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else if (searchArr[searchPos].contains("GAME")) {
						logColorArray.setColor(searchPos, "#223620", true);
					} else {
						logColorArray.setColor(searchPos, "#A8D1A5", true);
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

	public static int getFileSize(boolean search) {
		if(search == true) {
			return searchedSize;
		}
		else {
			return fileSize;
		}
	}

	public static String getLogString(int arrNum, boolean searched) {
		String returnString = new String();
		if(searched == true) {
			returnString = searchArr[arrNum];
		} else {
			returnString = logArr[arrNum];
		}
		returnString = returnString.replaceAll("&#34;", "''");
		returnString = returnString.replaceAll("&#39;", "'");
		if (arrNum != 2) returnString = returnString.replaceAll(logID + " ", "");
		return returnString;
	}
}