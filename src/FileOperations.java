import java.io.*;
import java.util.*;

public class createFile {
	
	public void OutputFile(String file, List<String> OutputAL, List<String> DuplicateAL) {
		try {
		
		OutputAL.stream().sorted();
		//Sort the contents of Output File
		
		String outputFileName=file;	
		FileWriter writer=new FileWriter(outputFileName+".dat");
		
		int size=OutputAL.size();
		for(int i=0;i<size;i++) {
			String record=OutputAL.get(i).toString();
			writer.write(record);
			if(i<size-1)					//This prevent creating a blank like at the end of the file
            writer.write("\n");						//Write the content to Output file
		}
		
		long finalCount=OutputAL.stream().count();
		long overriddenCount=DuplicateAL.stream().count();
		writer.write("\nTotal Records: "+finalCount+"\nOverridden Records: "+overriddenCount);
		
		writer.close();
		
		System.out.println("\nOutput File Created: 'OutputEmployee.dat'");

	}
	catch(Exception ex) {
		System.out.println(ex);
	}
		
	}
	
	public void DuplicateFile(String file, List<String> DuplicateAL) {
		try {
			
		String duplicateFileName=file;	
		FileWriter writer=new FileWriter(duplicateFileName+".dat");
		int size=DuplicateAL.size();
		for(int i=0;i<size;i++) {
			String record=DuplicateAL.get(i).toString();
			writer.write(record);
			writer.write("\n");										//Write the content to Duplicate file
		}
		writer.close();
		
		System.out.println("\nOutput File Created: 'DuplicateEmployee.dat'");

	}
	catch(Exception ex) {
		System.out.println(ex);
	}
		
	}
}
