package ProjectSecSorting.ProjectSecSorting;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;





public class SecSortingReducer extends Reducer<CompositeKeyWritable,Text,CompositeKeyWritable,Text>{
	

	//IntWritable sum=new IntWritable();
	Text season=new Text();
	
	
	
	public void reduce(CompositeKeyWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException 
	{
		String temp="";
		
	
		for(Text t:values)
		{
			if(temp.isEmpty())
			{
				temp=t.toString();
			}
			else if(!temp.contains(t.toString()))
			{
				temp=temp+','+t.toString();
			}
			
		}
		
		season.set(temp);
		context.write(key,season);
	}

	

}
