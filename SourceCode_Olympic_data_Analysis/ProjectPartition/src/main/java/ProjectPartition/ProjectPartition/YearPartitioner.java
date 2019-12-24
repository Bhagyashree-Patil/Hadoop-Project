package ProjectPartition.ProjectPartition;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class YearPartitioner extends Partitioner <Text,Text>{

	@Override
	public int getPartition(Text key, Text value, int setNumReduce) {
		
		String season=key.toString();
		if(season.equals("Summer"))
		{
			return 0;
		}
		else
		{
			return 1;
		}
		
	}

}

