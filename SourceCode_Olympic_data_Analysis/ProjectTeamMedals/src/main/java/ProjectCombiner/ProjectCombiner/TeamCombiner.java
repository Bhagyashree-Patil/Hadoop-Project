package ProjectCombiner.ProjectCombiner;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class TeamCombiner extends Reducer<Text,Text,Text,Text>
{
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		 int sum=0;
	        Text result = new Text();
   	for(Text val:values)
   	{
   		String medal=val.toString();
   		if(medal.equals("Gold"))
   		{
   			sum+=1;
   		}
   	}
   	
   String s=Integer.toString(sum);
   result.set(s);
   context.write(key, result);
   	
   	
   }

}
