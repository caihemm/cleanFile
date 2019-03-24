package cn.justin.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cleaner2 {

	public static void main(String[] args) throws IOException {
		String filePath = args[0];

		File originalFodler = new File(filePath);
		String[] fileNames = originalFodler.list();
		Arrays.parallelSort(fileNames, (a, b) -> b.compareTo(a));
		List<String> toDelete = new ArrayList<String>();
		String _flag1 = null;
		String _flag2 = null;
		for (int i = 0; i < fileNames.length; i++) {
			String fileName = fileNames[i];
			int idx = fileName.indexOf("_");
			int idx1 = fileName.indexOf("_", idx + 1);
			int idx2 = fileName.indexOf("_", idx1 + 1);
			String flag1=fileName.substring(0, idx1);
			String flag2=fileName.substring(0, idx2);
			//System.out.println(fileNames.length);
			if(flag1.equals(_flag1) && !flag2.equals(_flag2)) {
				toDelete.add(fileName);
			}else {
				_flag1=flag1;
				_flag2=flag2;
			}
		}

		System.out.println(fileNames.length);
		System.out.println(toDelete.size());


//		toDelete.parallelStream().forEach(s -> {
//			try {
//
//				Files.delete(Paths.get(filePath + "\\" + s));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		});
	}

}
