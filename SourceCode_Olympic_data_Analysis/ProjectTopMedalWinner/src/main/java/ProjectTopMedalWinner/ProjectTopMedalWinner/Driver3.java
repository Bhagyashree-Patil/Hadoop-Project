package ProjectTopMedalWinner.ProjectTopMedalWinner;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.chain.ChainReducer;



/**
 * Hello world!
 *
 */
public class Driver3
{
	public static void main(String[] args) throws Exception 
	{ 
		System.out.print("Execution started");
		
		Configuration conf = new Configuration(); 
		Job job = Job.getInstance(conf, "top 10 participants"); 
		job.setJarByClass(Driver3.class); 
		
		 
		 
		   Configuration mapConf1 = new Configuration(false);
	       ChainMapper.addMapper(job, TopMedalWinMapper.class,LongWritable.class,Text.class,Text.class,IntWritable.class,  mapConf1);
	       
	       Configuration reduceConf1 = new Configuration(false);
	       ChainReducer.setReducer(job, TopMedalWinReducer.class, Text.class,IntWritable.class,Text.class,IntWritable.class, reduceConf1);
	       
	       Configuration mapConf2 = new Configuration(false);
	       ChainReducer.addMapper(job, M3.class, Text.class, IntWritable.class, Text.class, IntWritable.class,  mapConf2);
	       
	     
		
		  
	        
	        job.setInputFormatClass(TextInputFormat.class);
	        job.setOutputFormatClass(TextOutputFormat.class);
	        
	        job.setOutputKeyClass(LongWritable.class); 
			job.setOutputValueClass(Text.class); 
			
			FileInputFormat.addInputPath(job, new Path(args[0]));
	        FileOutputFormat.setOutputPath(job, new Path(args[1]));

	        FileSystem fs = FileSystem.get(conf);
	        fs.delete(new Path(args[1]),true);

		
		System.exit(job.waitForCompletion(true) ? 0 : 1); 
	} 
}
