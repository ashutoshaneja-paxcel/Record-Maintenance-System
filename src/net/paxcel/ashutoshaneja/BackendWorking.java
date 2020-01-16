package net.paxcel.ashutoshaneja;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**This class implements logic for creating latest and duplicate records from input csv file
 * <br>Method:<br>
 * - implementSystem(String inputFilePath)
 * Parameter 1: Gets Path to fetch the input CSV file
 * 
 * @author Ashutosh
 *
 */
class BackendWorking implements AutoCloseable {

	@Override
	public void close() {
		LoadResources.logger.info("Allocated Resources freed -- Files Closed --");
	}

	static {
		LoadResources.logger.info("Backend Active!");
	}


	public void implementSystem(String inputFilePath)throws IOException, Exception {

		try(	FileReader filereader=new FileReader(new File(inputFilePath));
				BufferedReader bufferedreader=new BufferedReader(filereader);) {

			FileOperations fileoperations=new FileOperations();			//class to create final output, duplicate files

			TreeMap<String, String>treemap = new TreeMap<String, String>();
			List<String> recordList=new ArrayList<String>();
			List<String> treemapList=new ArrayList<String>();

			String line="";
			int recordCount=0;
			LoadResources.logger.info("File Received \""+inputFilePath+"\" : \n");

			while((line=bufferedreader.readLine())!=null) {		//Read line by line till the eof

				String[] lineArray=line.split(",");
				String key = lineArray[0];						//using id as key for TreeMap
				String value = lineArray[1]+","+lineArray[2]+","+lineArray[3];
																//using rest of input record as Value
				recordList.add(line);
				treemap.put(key, value);
				recordCount++;
			}
			
			treemap.forEach((key, value)->{				
				String treemapElement=(key+","+value); 
				treemapList.add(treemapElement);
			});
			//convert TreeMap to List
			
			recordList.removeAll(treemapList);
			//remove treemaplist from recordslist


			System.out.println(recordCount+" Records Found from \""+inputFilePath+"\" : \n");				

			fileoperations.createOutputFile(treemapList, recordList);
			fileoperations.createDuplicateFile(recordList);

		}
		catch(FileNotFoundException fileexception) {
			LoadResources.logger.error("\nFile not found! \nDescription: "+fileexception.getMessage());
			throw new IOException();
		}
		catch(Exception exception) {
			LoadResources.logger.warn("Exception Found!!");
			LoadResources.logger.error("Description: "+exception.getMessage()); 
			throw new Exception();
		}

	}
}
