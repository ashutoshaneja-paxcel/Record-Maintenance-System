package net.paxcel.ashutoshaneja;
import java.io.*;
import java.util.*;


/**This class involves CSV File Operations for creating Output and Duplicate Files.
 * 
 * <br> <font size="2" color="red">Methods are</font>
 * <ol>
 * <li><font size="2" color="blue">createOutputFile</font></li>		
 * <li><font size="2" color="blue">createDuplicateFile</font></li>		
 * </ol>
 * 
 * @author Ashutosh
 *
 */
class FileOperations {
	
	String outputFilePath=LoadResources.configProperties.getProperty("outputFilePath");
	public void createOutputFile(List<String> outputRecords, List<String> duplicateRecords) {

		try(FileWriter writer=new FileWriter(outputFilePath);) {

			int size=outputRecords.size();
			for(int i=0;i<size;i++) {
				String record=outputRecords.get(i).toString();
				writer.write(record);
				if(i<size-1)					//This prevent creating a blank like at the end of the file
					writer.write("\n");						//Write the content to Output file
			}

			long outputCount=outputRecords.stream().count();
			long duplicateCount=duplicateRecords.stream().count();

			writer.write("\nTotal Records Added: "+outputCount+"\n Overwritten Records: "+duplicateCount);

			System.out.println("Output File Created: "+LoadResources.configProperties.getProperty("outputFilePath"));

		}
		catch(Exception ex) {
			System.out.println(ex);
		}

	}
	
	String duplicateFilePath=LoadResources.configProperties.getProperty("duplicateFilePath");
	public void createDuplicateFile(List<String> duplicateRecords) {

		try(FileWriter writer=new FileWriter(duplicateFilePath);) {

			int fileSize = duplicateRecords.size();
			for(int i=0;i<fileSize;i++) {
				String record=duplicateRecords.get(i).toString();
				writer.write(record);
				if(i<fileSize-1)
					writer.write("\n");						//Write the content to Duplicate file
			}

			System.out.println("\nOutput File Created: "+LoadResources.configProperties.getProperty("duplicateFilePath"));

		}
		catch(Exception exception) {
			System.out.println(exception);
		}

	}
}
