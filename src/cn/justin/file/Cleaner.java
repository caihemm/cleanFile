package cn.justin.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cleaner {

	public static void main(String[] args) throws IOException {
		String filePath = "E:\\ftp\\63";

		File originalFodler = new File(filePath);
		String[] fileNames = originalFodler.list();
		Arrays.parallelSort(fileNames, (a, b) -> b.compareTo(a));
		List<String> toDelete = new ArrayList<String>();
		String start = "null";
		boolean stop = false;
		String end = "_ALLBPS.pdf";
		for (String s : fileNames) {

			if (!s.startsWith(start)) {
				int idx = s.indexOf("_", s.indexOf("_") + 1);
				start = s.substring(0, idx);
				stop = false;
			} else {
				if (!stop && s.endsWith(end)) {
					stop = true;
				}
			}

			if (stop) {
				toDelete.add(s);

			}

		}

		System.out.println(fileNames.length);
		System.out.println(toDelete.size());

		toDelete.parallelStream().forEach(s -> {
			try {

				Files.delete(Paths.get(filePath + "\\" + s));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

}
