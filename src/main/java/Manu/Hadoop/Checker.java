package Manu.Hadoop;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Checker {
	 static String Val1="";
	 static int flag=0;
	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable>
	 {
	    private final static IntWritable one = new IntWritable(1);
	    private Text word = new Text();
	    public void map(Object key, Text value, Context context) throws IOException, InterruptedException 
	    {
	    	String type="";
	    	StringTokenizer itr = new StringTokenizer(value.toString());
	      System.out.println(itr.countTokens());  
	      while (itr.hasMoreTokens()) 
	      {
	          type=itr.nextToken();
	          System.out.println(type);
	    	  word.set(type);
	          context.write(word, one);
	      }
	      System.out.println("here");
	    }
	  }

	  public static class IntSumReducer extends Reducer<Text,IntWritable,Text,IntWritable> 
	  {
		  private Text result = new Text();
		  private final static IntWritable one = new IntWritable(1);
	    public void reduce(Text key, Iterable<IntWritable> values,Context context) throws IOException, InterruptedException 
	    {
	    	result.set(Val1);
	    	if(key.equals(result))
	    	{
	    		context.write(key,one);
	    		flag=1;
	    		System.out.println(flag+"--------------------------------endle");
	    	}
	    }
	   }

	  public static void main(String[] args) throws Exception 
	  {
	    System.out.println("Output: " + args[0]);
	    Val1=args[0];
		Configuration conf = new Configuration();
	    Job job = Job.getInstance(conf, "word count");
	    job.setJarByClass(WordCount.class);
	    job.setMapperClass(TokenizerMapper.class);
	    job.setCombinerClass(IntSumReducer.class);
	    job.setReducerClass(IntSumReducer.class);
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);
	    FileInputFormat.addInputPath(job, new Path("password.txt"));
	    FileOutputFormat.setOutputPath(job, new Path("output"));
	    job.waitForCompletion(true);
	    if(flag==1)
	    {
	        String [] args2 = new String[3];
	        args2[0] = "YOUR PASSWORD EXIST IN DICTIONARY... \n PLEASE RETRY WITH NEW PASSWORD";
	    	pass_display dp= new pass_display();
	    	dp.main(args2);
	    }
	    else
	    {
	        String [] args2 = new String[3];
	        args2[0] = "PERFECT PASSWORD FOR USE... \n GOOD LUCK...!!!";		    	
	        pass_display dp= new pass_display();
	    	dp.main(args2);
	    }
	  }
}
