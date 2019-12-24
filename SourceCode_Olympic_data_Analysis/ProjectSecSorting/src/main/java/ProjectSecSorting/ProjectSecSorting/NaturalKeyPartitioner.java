package ProjectSecSorting.ProjectSecSorting;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;



public class NaturalKeyPartitioner extends Partitioner <CompositeKeyWritable,Text>
{
	public int	getPartition(CompositeKeyWritable key, Text value, int numPartitions) 
{
	
	return key.getCity().hashCode()%numPartitions;
}
}