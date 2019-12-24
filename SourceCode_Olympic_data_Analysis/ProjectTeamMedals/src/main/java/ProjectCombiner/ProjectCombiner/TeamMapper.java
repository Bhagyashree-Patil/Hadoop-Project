package ProjectCombiner.ProjectCombiner;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class TeamMapper extends  Mapper<LongWritable,Text,Text,Text> {
	
	
	
    Text team = new Text();
    Text medal = new Text();
    
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String [] tokens = line.split(",");
       
        
        //tokens[14]=Medal
        //tokens[6]=Team
        
        
        
        if(!tokens[14].equals("NA"))
        {
        
        	team.set(tokens[6]);
        	medal.set(tokens[14]);
        context.write(team,medal);
        }
    }
	

}
