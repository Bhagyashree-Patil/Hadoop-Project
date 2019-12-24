package ProjectPartition.ProjectPartition;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;



/**
 * Hello world!
 *
 */
public class YearDriver 
{
	public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );
        
        
        Configuration conf = new Configuration();
        // Create a new Job
        Job job = Job.getInstance(conf,"partition");
        job.setJarByClass(YearDriver.class);

        // Specify various job-specific parameters     
        job.setJobName("myJob");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setMapperClass(YearMapper.class);
        job.setPartitionerClass(YearPartitioner.class);
        job.setReducerClass(YearReducer.class);
        
        job.setNumReduceTasks(2);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        
        FileSystem fs = FileSystem.get(conf);
		fs.delete(new Path(args[1]),true);
        
        // Submit the job, then poll for progress until the job is complete
        try {
			System.exit(job.waitForCompletion(true)?0:1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
