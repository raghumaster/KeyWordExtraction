package org.csulb.edu.raghu.keyword.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class KeyWordCleanseMapperUtil {
	
	private Set<String> stopWordsSet;

	/*
	 * Method that extracts stop words from text files and adds them to the stop
	 * words set
	 */
	public Set<String> generateStopWords(ArrayList<String> cacheFileList) {
		stopWordsSet = new HashSet<>();
		try {
			for (Object file : cacheFileList.toArray()) {
				BufferedReader br = new BufferedReader(new FileReader( (String) file));
				String str;
				// Extract each line from the file
				while ((str = br.readLine()) != null) {
					stopWordsSet.add(str.toLowerCase());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return stopWordsSet;
	}
public ArrayList<String> getCacheFiles(Context context) throws IOException{
	ArrayList<String> cacheFileList = new ArrayList<String>();
	Path[] cacheFiles = DistributedCache.getLocalCacheFiles(context.getConfiguration());
	for (Path cacheFilePath : cacheFiles) {
		cacheFileList.add(cacheFilePath.toString());
	}
	return cacheFileList;
	}

}
