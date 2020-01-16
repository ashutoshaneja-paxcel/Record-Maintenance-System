package net.paxcel.ashutoshaneja;
import java.io.*;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * @author Ashutosh
 *
 */
class LoadResources {

	protected static Logger logger;
	static Properties configProperties;
	static Properties log4jProperties;

	static {


		try (InputStream configPropertyPath = new FileInputStream("Properties/config.properties");
				InputStream log4jPropertyPath = new FileInputStream("Properties/log4j.properties");) {
			logger=Logger.getLogger("GLOBAL");

			configProperties=new Properties();
			log4jProperties=new Properties();

			configProperties.load(configPropertyPath);
			log4jProperties.load(log4jPropertyPath);

			logger.setLevel(Level.INFO);

			PropertyConfigurator.configure(log4jProperties);    


			logger.info("\n\nResources Loaded....");
		} 
		catch (Exception exception){
			System.out.println("Resources couldn't be loaded. Try again :(");
			logger.info("Unsuccessful Resources Load. Find Stack Trace on below line");
			logger.error(exception.getStackTrace(),exception);
			System.exit(0);
		}

	}

	static void areValid() {}
	//static method to execute static block

}
