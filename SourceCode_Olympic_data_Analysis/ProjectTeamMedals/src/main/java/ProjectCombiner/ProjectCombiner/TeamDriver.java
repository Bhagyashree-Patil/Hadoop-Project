package ProjectCombiner.ProjectCombiner;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



/**
 * Hello world!
 *
 */
public class TeamDriver 
{
	 public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException{
	        if (args.length != 2) {
	            System.err.println("Usage: MaxSubmittedCharge <input path> <output path>");
	            System.exit(-1);
	        }

	       
	      
	        Job job = Job.getInstance();

	        job.setJarByClass(TeamDriver.class);

	        job.setMapperClass(TeamMapper.class);
	        job.setReducerClass(TeamReducer.class);
	        //job.setCombinerClass(TeamCombiner.class);
	        job.setNumReduceTasks(1);

	        TextInputFormat.addInputPath(job, new Path(args[0]));
	        job.setInputFormatClass(TextInputFormat.class);

	        job.setMapOutputKeyClass(Text.class);
	        job.setMapOutputValueClass(Text.class);

	        FileOutputFormat.setOutputPath(job, new Path(args[1]));
	      
	        job.setOutputKeyClass(Text.class);
	        job.setOutputValueClass(Text.class);


	        int code = job.waitForCompletion(true) ? 0 : 1;
	        System.exit(code);
	    }
}
