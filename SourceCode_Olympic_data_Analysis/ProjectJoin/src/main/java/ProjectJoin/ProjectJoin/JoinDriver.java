package ProjectJoin.ProjectJoin;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;



/**
 * Hello world!
 *
 */
public class JoinDriver 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );

        
        
        Configuration conf = new Configuration();
        // Create a new Job
        Job job = Job.getInstance(conf,"Join");
        job.setJarByClass(JoinDriver.class);

        // Specify various job-specific parameters     
        job.setJobName("myjob");

        MultipleInputs.addInputPath(job, new Path(args[0]),TextInputFormat.class,JoinMapper1.class);
        MultipleInputs.addInputPath(job, new Path(args[1]),TextInputFormat.class,JoinMapper2.class);
        job.getConfiguration().set("join.type",args[2]);
        
       // FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[3]));
        
        //job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        //job.setMapperClass(M1.class);
        job.setReducerClass(JoinReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        
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
