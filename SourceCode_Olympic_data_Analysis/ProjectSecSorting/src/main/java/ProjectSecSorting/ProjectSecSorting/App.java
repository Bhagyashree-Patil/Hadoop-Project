package ProjectSecSorting.ProjectSecSorting;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException
    {
        System.out.println( "Hello World!" );
        
        Configuration conf = new Configuration(); 

		Job job = Job.getInstance(conf, "Secondary Sorting"); 
		job.setJarByClass(App.class); 

		job.setMapperClass(SecSortingMapper.class); 
		job.setPartitionerClass(NaturalKeyPartitioner.class);
		job.setGroupingComparatorClass(GroupKeyComparator.class);
		job.setSortComparatorClass(sortComparator.class);
		job.setReducerClass(SecSortingReducer.class); 

		job.setMapOutputKeyClass(CompositeKeyWritable.class); 
		job.setMapOutputValueClass(Text.class); 

		job.setOutputKeyClass(CompositeKeyWritable.class); 
		job.setOutputValueClass(Text.class); 

		FileInputFormat.addInputPath(job, new Path(args[0])); 
		FileOutputFormat.setOutputPath(job, new Path(args[1])); 

		FileSystem fs = FileSystem.get(conf);
		fs.delete(new Path(args[1]),true);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
        
    }
}
