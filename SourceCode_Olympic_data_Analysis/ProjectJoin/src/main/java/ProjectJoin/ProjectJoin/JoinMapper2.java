package ProjectJoin.ProjectJoin;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class JoinMapper2 extends Mapper<LongWritable,Text,Text,Text>{
	
	
	Text fullform = new Text();
    Text region = new Text();
    
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String [] tokens = line.split(",");
        
        //value----->tokens[1]--->full form of region
        //key------>tokens[0]--->Region
        
       
        
        	region.set(tokens[0]);
        	fullform.set("B"+tokens[1]);
        context.write(region, fullform);
        
    }

}
