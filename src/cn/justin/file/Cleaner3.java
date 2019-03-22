package cn.justin.file;

import static java.util.stream.Collectors.groupingBy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cleaner3 {

	public static void main(String[] args) throws IOException {
		String filePath = "E:\\ftp\\cpq\\64";

		File originalFodler = new File(filePath);
		File[] fileNames = originalFodler.listFiles();
		Arrays.sort(fileNames,(a, b) -> b.compareTo(a));
		List<String> toDelete = new ArrayList<String>();
		String start = "null";
		boolean stop = false;
		String end = "_ALLBPS.pdf";
		Stream<File> stream = Stream.of(fileNames);
		 Map<Object, List<File>>  map=stream.collect(groupingBy(f->f.getName().substring(0, 34)));

		System.out.println(fileNames.length);
		System.out.println(toDelete.size());

//		toDelete.parallelStream().forEach(s -> {
//			try {
//
//				Files.delete(Paths.get(filePath + "\\" + s));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
	}

}
