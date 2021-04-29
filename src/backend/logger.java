package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
            for (File f : filesList) {
                if (f.isFile()) {
                    if (f.getName().equals("logifier.log") || f.getName().equals("logifier.txt")) {
                        logFile = f;
                        break;
                    }
                }
            }

            fileScan = new Scanner(logFile);

            boolean searching = false;
            int searchWordsLength = 0;
            if (searchWords != null && searchWords.length > 0) {
                searching = true;
                searchedSize = 0;
                for (String searchWord : searchWords) {
                    if (searchWord != null && searchWord.length() > 0) {
                        searchWordsLength++;
                    }
                }
            } else {
                fileSize = 0;
            }

            String lineHolder;

            Pattern ID_pattern = Pattern.compile("Starting up. \\(ID: (.*?)\\)");

            while (fileScan.hasNextLine()) {
                lineHolder = fileScan.nextLine();
                if (date == null) {
                    Pattern date_pattern = Pattern.compile("\\[(.*?)\\s.*\\]");
                    Matcher date_matcher = date_pattern.matcher(lineHolder);
                    if (date_matcher.find()) {
                        date = date_matcher.group(1);
                    }
                }
                if (logID == null) {
                    Matcher ID_matcher = ID_pattern.matcher(lineHolder);
                    if (ID_matcher.find()) {
                        logID = ID_matcher.group(1);
                    }
                }
                if (searching == true) {
                    for (int i = 0; i < searchWordsLength; i++) {
                        if (searchWords[i].length() > 0 && lineHolder.toLowerCase().contains(searchWords[i].toLowerCase())) {
                            searchedSize++;
                            break;
                        }
                    }
                } else {
                    fileSize++;
                }
            }
            fileScan.close();

            if (searching == true) {
                logColorArray.initSearchArr(searchedSize);
                searchArr = new String[searchedSize];
            } else {
                logArr = new String[fileSize];
            }

            fileScan = new Scanner(logFile);

            HashMap<String, String> map = new HashMap<>();
            map.put("ADMIN", "#B85B56");
            map.put("OOC", "#5575A6");
            map.put("ATTACK", "#BF569A");
            map.put("ACCESS", "#506152");
            map.put("DEBUG", "#506152");
            map.put("SS", "#506152");
            map.put("GC", "#506152");
            map.put("TGS", "#506152");
            map.put("TOPIC", "#506152");
            map.put("MASTER", "#506152");
            map.put("VOTE", "#506152");
            map.put("GAME", "#506152");

            Pattern pattern = Pattern.compile(logID + " (.*?):");

            boolean hasWord;
            int searchArrCount = 0;
            int numToUse;
            for (int i = 0; i < logger.getFileSize(false); i++) {
                lineHolder = fileScan.nextLine() + "\n";
                if (searching == true) {
                    hasWord = false;
                    for (int j = 0; j < searchWordsLength; j++) {
                        if (searchWords[j].length() > 0 && lineHolder.toLowerCase().contains(searchWords[j].toLowerCase())) {
                            hasWord = true;
                            searchArr[searchArrCount] = lineHolder;
                            break;
                        }
                    }
                    if (hasWord == false) {
                        continue;
                    }
                    numToUse = searchArrCount;
                    searchArrCount++;
                } else {
                    logArr[i] = lineHolder;
                    numToUse = i;
                }

                Matcher matcher = pattern.matcher(lineHolder);

                if (matcher.find()) {
                    logColorArray.setColor(numToUse, map.getOrDefault(matcher.group(1), "#A8D1A5"), searching);
                } else {
                    logColorArray.setColor(numToUse, "#A8D1A5", searching);
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "The logifier text file not found! Make sure you named it correctly! Logs will be bugged!");
        }
    }

    public static int getFileSize(boolean search) {
        if (search == true) {
            return searchedSize;
        } else {
            return fileSize;
        }
    }

    public static String getLogString(int arrNum, boolean searched) {
        String returnString;
        if (searched == true) {
            returnString = searchArr[arrNum];
        } else {
            returnString = logArr[arrNum];
        }
        returnString = returnString.replaceAll("&#34;", "\"");
        returnString = returnString.replaceAll("&#39;", "'");
        if (arrNum != 0) {
            returnString = returnString.replaceAll(date + " ", "");
        }
        if (arrNum != 2) {
            returnString = returnString.replaceAll(logID + " ", "");
        }
        return returnString;
    }
}