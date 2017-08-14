package Manu.Hadoop;
import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount2
{
    public static class WordMapper extends Mapper<Text, Text, Text, Text>
    {
        private Text word = new Text();
        private Text word2 = new Text();
        public void map(Text key, Text value, Context context) throws IOException, InterruptedException
        {
        	String Date="";
            String Time="";
            String IP="";
            String email="";
            
            
            
        	StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens())
            {
            	 Date=itr.nextToken();
                 System.out.println(Date);
                 
                 Time=itr.nextToken();
                 System.out.println(Time);
                 
                
                 IP=itr.nextToken();
                 System.out.println(IP);
                 
                 
           	  	 
                 email=itr.nextToken();    
                 System.out.println(email);
                
                
                
                 word.set(Time);
                 word2.set(Date);
                 context.write(word,word2);
            }
            System.out.println("im here");
        }
    }

    public static class AllTranslationsReducer extends Reducer<Text,Text,Text,Text>
    {
    	private Text result = new Text();
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException
        {
            System.out.println("It comes here");
            String translations="";
            int count=0;
            for (Text val : values)
            {
            	count++;
            	translations +="|"+val.toString();
            	
            	System.out.println(count+"\n");
            	System.out.println(result.toString());
            	result.set(translations);
            }
            	context.write(key, result);
            
        }   
        }
        

    public static void main() throws Exception
    {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "WordCount2");
        job.setJarByClass(WordCount2.class);
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(AllTranslationsReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        FileInputFormat.addInputPath(job, new Path("Log"));
        FileOutputFormat.setOutputPath(job, new Path("output"));
        //System.exit(job.waitForCompletion(true)?0:1);
        job.waitForCompletion(true);
    }
}