package ProjectJoin.ProjectJoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class JoinMapper1 extends Mapper<LongWritable,Text,Text,Text>{
	
	Text name = new Text();
    Text region = new Text();
    
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String [] tokens = line.split(",");
        
        //value----->tokens[1]--->Name of player
        //key------>tokens[7]--->Region
        
        if(!tokens[14].equals("NA"))
        {
        	region.set(tokens[7]);
        	name.set("A"+tokens[1]);
        context.write(region, name);
        }
    }

}
