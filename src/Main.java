import java.io.*;
import java.util.*;
import java.util.stream.*;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class Main {
	
	/**
	 * @param args
	 * @throws IOException
	 */
	
	private static Scanner sc=new Scanner(System.in);	
	private static Logger logger = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) {
		
		DOMConfigurator.configure("Properties/log4j.xml");  
		
		try {
		createFile createfile=new createFile();			//class to create final output, duplicate files
			
		List<String> EmployeeAL=new ArrayList<String>();			//Stores data from .csv file
		List<Character> IdAL=new ArrayList<Character>();			//Stores Unique IDs from .csv file
		List<String> DuplicateEmployeesAL=new ArrayList<String>();	//Stores earlier duplicate entries
		List<String>OutputEmployeesAL= new ArrayList<String>();		//Stores latest entries
		
		String line="";
		
		System.out.println("Enter the file location: ");
		
		
		String fileLocation=sc.next();								//Inputs absolute path for .csv file
		
		File f=new File(fileLocation);
		FileReader fr=new FileReader(f);
		BufferedReader br=new BufferedReader(fr);
		logger.info("Info message - File Received: " +f.getName()); 
		
		while((line=br.readLine())!=null) {							//Reads till the eof
			String employeeData = line;								
			EmployeeAL.add(employeeData);							
			IdAL.add(employeeData.charAt(0));						//Add unique IDs
		}
		
		System.out.println("Records Found from "+f.getName());
		EmployeeAL.forEach(System.out::println);					
		//Traverse data from input .csv file
		
		List<Character> IDWithoutDuplicates = IdAL.stream().distinct().collect(Collectors.toList());
		//finds distinct Unique IDs
		
		System.out.println();
		for(Character c: IDWithoutDuplicates)
		 logger.debug("\nIDs are: "+c);
		
		
		for(int i=0;i<IDWithoutDuplicates.size();i++) {
		String UID=""+IDWithoutDuplicates.get(i);
		List<String> UniqueEmployeesAL=EmployeeAL.stream().filter(e-> e.startsWith(UID)).collect(Collectors.toList());
		//Temporary ArrayList to find entries for specific Unique ID
		
			for(int j=0;j<UniqueEmployeesAL.size()-1;j++) {
				DuplicateEmployeesAL.add(UniqueEmployeesAL.get(j));
				//Add earlier entries to duplicate .dat file
			}
		
		OutputEmployeesAL.add(UniqueEmployeesAL.get(UniqueEmployeesAL.size()-1));
		//Insert only latest entry to Output File
		
		
		UniqueEmployeesAL.clear();
		//Clear contents of Temporary ArrayList
		}
		
		
		/*
		Prints only the duplicate entries to console
		DuplicateEmployeesAL.forEach(System.out::println);
		*/
		
		/*Prints only the latest entries to console
		OutputEmployeesAL.forEach(System.out::println);
		*/
		
		String outputFileName = "OutputEmployee";
		String duplicateFileName = "DuplicateEmployee";
		createfile.OutputFile(outputFileName, OutputEmployeesAL, DuplicateEmployeesAL);
		createfile.DuplicateFile(duplicateFileName, DuplicateEmployeesAL);	

			br.close();
		}
		catch(FileNotFoundException fex) {
			logger.error("File not found!!");
		}
		catch(Exception ex) {
	        logger.warn("Error message"+ex); 
		}
		
		finally {
		sc.close();
		}
			
	}

}
