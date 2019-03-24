package cn.justin.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cleaner6 {

	public static void main(String[] args) throws IOException, InterruptedException {
		String filePath = args[0];

		File originalFodler = new File(filePath);
		String[] fileNames = originalFodler.list();

		Object[] keep = Stream.of(fileNames)
				.collect(Collectors.groupingBy(t -> t.split("_")[0], Collectors.groupingBy(t -> t.split("_")[2])))
				.entrySet().stream().flatMap(p -> {
					Map<String, List<String>> map = p.getValue();
					String maxKey = Collections.max(map.keySet());
					return map.get(maxKey).stream();
				}).toArray();

		List<String> deleteList = new ArrayList<String>();
		Collections.addAll(deleteList, fileNames);
		deleteList.removeAll(Arrays.asList(keep));
		
		  
		  deleteList.parallelStream() .forEach(a->{
		  
		  try { Files.delete(Paths.get(filePath + "\\" + a));
		  
		  } catch (IOException e) { e.printStackTrace(); } });
		  
		 

		System.out.println(fileNames.length);
		System.out.println(keep.length);
		System.out.println(deleteList.size());
	}

}
