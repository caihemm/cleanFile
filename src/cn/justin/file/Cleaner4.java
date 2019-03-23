package cn.justin.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cleaner4 {
	static String max=null;

	public static void main(String[] args) throws IOException, InterruptedException {
		String filePath = "E:\\ftp\\68";

		File originalFodler = new File(filePath);
		String[] fileNames = originalFodler.list();			

		
		Object[] toDeletes=Stream.of(fileNames)
		.sorted((a,b)->b.compareTo(a))
		.filter(t->{
			String[] ta = t.split("_");
			if(max==null) {
				max=t;
				return false;
			}else {
				String[] la=max.split("_");
				int i=0;
				while(ta[i].equals(la[i])) {
					i++;
				}
				if(i!=2) {
					if(i!=3) {
						max=t;
					}					
					return false;
				}
				return true;
		}}).toArray();
		
		Stream.of(toDeletes)
		.parallel().forEach(a->{

			try {
				Files.delete(Paths.get(filePath + "\\" + a));
				
			} catch (IOException e) {				
				e.printStackTrace();
			}
		});
	
		
		System.out.println(fileNames.length);
		System.out.println(toDeletes.length);



	}

}
