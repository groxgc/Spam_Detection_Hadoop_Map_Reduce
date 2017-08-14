package Manu.Hadoop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      
    	String FILENAME = "output/part-r-00000";	  
   	    String FILENAME2 = "output3/part-r-00000";			  	  
 	    String FILENAME3 = "output2/part-r-00000";			  	  	   

    		BufferedReader br = null;
    		FileReader fr = null;
    		BufferedReader br2 = null;
    		FileReader fr2 = null;
    		BufferedReader br3 = null;
    		FileReader fr3 = null;
    		try {
    			fr = new FileReader(FILENAME);
    			br = new BufferedReader(fr);

    			String sCurrentLine;

    			br = new BufferedReader(new FileReader(FILENAME));

    			while ((sCurrentLine = br.readLine()) != null) 
    			{
    				System.out.println(sCurrentLine);
    				
    			}

    		} catch (IOException e) {

    			e.printStackTrace();

    		} finally {

    			try {

    				if (br != null)
    					br.close();

    				if (fr != null)
    					fr.close();

    			} catch (IOException ex) {

    				ex.printStackTrace();

    			}

    		}    	
    	
    }
}
