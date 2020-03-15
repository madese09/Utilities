package com.db.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SwitchBoardFileProcessor {
	
	public static void main(String[] args)  {
		File loggerFile = null;
		InputStream propertyFile = null;
		PrintStream out = null;
		try {
			propertyFile = new FileInputStream(new File(""));
			Properties prop = new Properties();
			prop.load(propertyFile);
			
			loggerFile = new File(prop.getProperty("sb.log.file"));
			out = new PrintStream(loggerFile);
			System.setOut(out);
			File source = new File(prop.getProperty("sb.source.folder"));
			File destination = new File(prop.getProperty("sb.source.destination"));
			
			File latest = getLatestFile(source);
			System.out.println(new Date() + destination.getAbsolutePath()+"\\"+latest.getName());
			Files.copy(latest.toPath(), new File(destination.getAbsolutePath()+"\\"+latest.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
			executePhoneMerge(destination, prop);
		}catch(Exception e) {
			System.err.println("Unexpected exception thrown"+e.getStackTrace());
		}finally {
			out.close();
		}
	}

	private static void executePhoneMerge(File fileToBeProcessed, Properties prop) throws IOException {
		System.out.println(new Date()+" Inside executePhoneMerge method" );
		String latestFile = replaceFileName(fileToBeProcessed.getName(), prop);
		System.out.println(new Date()+" Get the latest filename with current timestamp ::"+latestFile);
		/*ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("hrfeedupdate.exe","arg1","arg2","arg3");
		Process process = processBuilder.start();*/
	}
	/**
	 * Make it more generic
	 * @param name
	 * @return
	 */
	private static String replaceFileName(String name, Properties prop) {
		System.out.println(new Date()+" Inside replaceFileName method");
		return name.replaceAll(name, prop.getProperty("sb.file.name")+new SimpleDateFormat("yyyyMMdd").format(new Date()));
	}

	private static File getLatestFile(File source) {
		System.out.println(new Date()+" Inside the getLatestFile method");
		File latestFile = null;
		try {
			File[] files = source.listFiles(File::isFile);
			if(files == null || files.length == 0) {
				throw new Exception("No Files");
			}
			
			long lastModified = Long.MIN_VALUE;
			for(File file: files) {
				if(file.lastModified() >lastModified) {
					latestFile = file;
					lastModified = file.lastModified();
				}
			}
			System.out.println(new Date()+" Get Latest File::"+latestFile.getName());
		}catch(Exception e) {
			System.out.println(new Date()+" System threw exception while traversing throught the directory ::"+e.getMessage());
		}
		return latestFile;
	}

}
