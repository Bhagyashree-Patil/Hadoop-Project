package ProjectTopMedalWinner.ProjectTopMedalWinner;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class TopMedalWinMapper extends Mapper<LongWritable,Text,Text,IntWritable>
{
	
	
	IntWritable one = new IntWritable(1);
    Text name = new Text();
    
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String [] tokens = line.split(",");
        
        //tokens[14]=Medal
        //tokens[1]=name
        
        if(!tokens[14].equals("NA"))
        {
        
        	name.set(tokens[1]);
        context.write(name, one);
        }
    }

}
