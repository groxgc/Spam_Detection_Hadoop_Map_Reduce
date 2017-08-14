package Manu.Hadoop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class WordCount3
{
   public static void main(String[] args) throws Exception
    {
	   
	   WordCount.main();
	   WordCount2.main();
	   WordCount_list.main();
	   String t1="";
	   String t2="";
	   String t3="";
	   int flag=0;
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
			br = new BufferedReader(new FileReader(FILENAME));
			String sCurrentLine="";
			while ((sCurrentLine = br.readLine()) != null)
			{				
				String[] parts = sCurrentLine.split("	");
				String part1 = parts[0]; // IP
				String part2 = parts[1];//Date
				//System.out.println(sCurrentLine);
				//System.out.println("1");
			    if(part2.length()>20)
			    {
			    fr2 = new FileReader(FILENAME2);
			 	br2 = new BufferedReader(fr2);
			 	br2 = new BufferedReader(new FileReader(FILENAME2));
			 	String sCurrentLine2="";
			 	while ((sCurrentLine2 = br2.readLine()) != null)
			 		{
			 			String[] pts = sCurrentLine2.split("	");
			 			String pt1 = pts[0]; //Hash 
			 			String pt2 = pts[1]; //IP
			 			//System.out.println("2");
			            if(pt2.equals(part1))
			            {
			            	fr3 = new FileReader(FILENAME3);
			 			 	br3 = new BufferedReader(fr3);
			 			 	br3 = new BufferedReader(new FileReader(FILENAME3));
			 			 	String sCurrentLine3="";
			 			 	while ((sCurrentLine3 = br3.readLine()) != null)
			 			 		{
			 			 			String[] prts = sCurrentLine3.split("	");
			 			 			String prt1 = prts[0]; //Hash 
			 			 			String prt2 = prts[1]; //Date
			 			 			//System.out.println("3");
			 			 			if(prt2.length()==part2.length())
			 			 			{	
			 			 				System.out.println("SPAM Detected");
			 			 				t1=prt1;
			 			 				t2=prt2;
			 			 				t3=pt2;
			 			 				System.out.println(t1+"  "+t2+"  "+t3+"  ");
			 			 				flag=1;			 			 				
			 			 				break;
			 			 			}			 			 		
			 			 		}					 		
			 			 	if(flag==1)
			 					break;
			            }
			       }
			    }
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