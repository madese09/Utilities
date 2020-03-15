package com.db.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class HotelProfiCopyFolder {
	/**
	 * stop server copy files from folder A copy files from folder B start server
	 */

	public static void main(String args[]) throws IOException {
		File a = new File("D:\\madese\\SB\\hs_inbox");
		File b = new File("D:\\madese\\SB\\sb_inbox");
		
		//compare between two folders and copy files 
	
	}
	
	static void copyFilesToBase(File source, File destination)throws IOException {
		File[] files = source.listFiles();
		Arrays.sort(files, new Comparator<File>() {

			@Override
			public int compare(File o1, File o2) {
				return new Long(o2.lastModified()).compareTo(o1.lastModified());
			}
			
		} );
		//get the last 2 modified files
		for(int i=0;i<2;i++) {
			//System.out.println(file.getName()+":"+file.lastModified());
			File latest = new File(files[i].getAbsolutePath());
			File dest = new File(destination.getAbsoluteFile()+File.separator+files[i].getName());
			Files.copy(latest.toPath(), dest.toPath() , StandardCopyOption.REPLACE_EXISTING);
		}
	}
	
	
	

}
