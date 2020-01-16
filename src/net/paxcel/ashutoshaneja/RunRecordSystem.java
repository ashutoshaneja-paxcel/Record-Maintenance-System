package net.paxcel.ashutoshaneja;

import java.io.IOException;
import java.util.*;

/**This class executes main method for running Record Maintenance System.
 * @author Ashutosh
 *
 */
public class RunRecordSystem {

	private static final Scanner scanner=new Scanner(System.in);
	public static void main(String[] args) {

		try(BackendWorking backend=new BackendWorking();) {

			LoadResources.areValid();
			//Load Resources and validate their information

			System.out.println("\n******************************* WELCOME to Record Maintenance System ********************************\n");
			System.out.println("Press 1: To use custom CSV file \nPress 2: To use Pre-defined CSV file");

			System.out.print("Enter your option: ");
			int option=scanner.nextInt();

			switch(option) {
			case 1:{
				backend.implementSystem(LoadResources.configProperties.getProperty("inputFilePath"));
				//get inputFilePath from config file
				break;
			}
			case 2:{
				System.out.println("Enter Path of Input CSV file: ");
				String customInputFilePath=scanner.next();
				backend.implementSystem(customInputFilePath);
				break;
			}
			default:{
				System.out.println("Bad Choice! :(");
				throw new Exception();
			}
			}
		}
		catch(IOException ioexception) {
			System.out.println("There seems to be an issue with Inputfile. Try again :(");
			LoadResources.logger.error("Description: "+ioexception.getMessage()); 
		}
		catch(Exception exception) {
			System.out.println("Error Encountered. Try again :(");
			LoadResources.logger.info("Exception: "+exception.getMessage(), exception);
		}
		finally {
			System.out.println("\n-- System Shutdown --");
			LoadResources.logger.info("Application Shutting Down!");
		}

	}

}
