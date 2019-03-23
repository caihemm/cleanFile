package cn.justin.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Cleaner3 {

	public static void main(String[] args) throws IOException {
		String filePath = "E:\\ftp\\68";

		File originalFodler = new File(filePath);
		String[] fileNames = originalFodler.list();			
		Stream<String> stream3 = Stream.of(fileNames);		
		List<String> keepList=stream3.sorted((a,b)->b.compareTo(a)).collect(ArrayList::new, (r,t)->{
			String[] ta = t.split("_");
			if(r.size()==0) {
				r.add(t);
			}else {
				String[] la=r.get(r.size()-1).split("_");
				int i=0;
				while(ta[i].equals(la[i])) {
					i++;
				}
				if(i!=2) {
					r.add(t);
				}
				
			}
		
		},ArrayList::addAll);
		
	
		
		
		System.out.println(fileNames.length);

		System.out.println(keepList.size());
//
//		Stream.of(fileNames).parallel().forEach(s -> {
//			try {
//				if(!keepList.contains(s)) {
//					Files.delete(Paths.get(filePath + "\\" + s));
//				}				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		});
	}

}
