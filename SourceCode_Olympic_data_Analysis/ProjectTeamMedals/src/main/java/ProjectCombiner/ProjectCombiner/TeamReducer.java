package ProjectCombiner.ProjectCombiner;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class TeamReducer extends Reducer<Text,Text,Text,Text>{
	
	
	
	
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException
	{
		int goldcount=0;
		int silvercount=0;
		int bronzecount=0;
		int totalmedals=0;
        Text result = new Text();
        for(Text i: values)
        {
        	String s=i.toString();
        	if(s.contains("Gold"))
        	{
        	
            goldcount+=1;
        	}
        	else if(s.contains("Silver"))
        	{
        		silvercount+=1;
        	}
        	else
        	{
        		bronzecount+=1;
        	}
        }
        
        
        totalmedals=goldcount+silvercount+bronzecount;
        
        String total=Integer.toString(totalmedals);
        String s1=Integer.toString(goldcount);
        String s2=Integer.toString(silvercount);
        String s3=Integer.toString(bronzecount);
        
        String s4="GoldCount="+s1+'\t'+"SilverCount="+s2+'\t'+"BronzeCount="+s3+'\t'+"Total="+totalmedals;
        
        
        result.set(s4);
        
        
        
        context.write(key, result);
    }

}
