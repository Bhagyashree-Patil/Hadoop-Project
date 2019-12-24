package ProjectPartition.ProjectPartition;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class YearMapper extends Mapper<LongWritable,Text,Text,Text>  {

	IntWritable one = new IntWritable(1);
    Text season = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String [] tokens = line.split(",");
        
        if(!tokens[10].equals("Season"))
        {
        
        	if(!tokens[14].equals("NA"))
        	{
        
        	season.set(tokens[10]);
        	context.write(season, value);
        	}
        }
    }
}



