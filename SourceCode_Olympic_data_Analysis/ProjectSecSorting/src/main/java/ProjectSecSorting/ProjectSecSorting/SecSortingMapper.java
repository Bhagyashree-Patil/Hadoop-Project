package ProjectSecSorting.ProjectSecSorting;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class SecSortingMapper extends Mapper<LongWritable,Text,CompositeKeyWritable,Text>
{
	
Text season1=new Text();
	
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
		String line=value.toString();
		String[] tokens=line.split(",");
		
		String city=tokens[11];
		String year=tokens[9];
		String season=tokens[10];
		
	
		
		season1.set(season);
		String y="1999";
		if(1==year.compareTo(y))
		{
		
		CompositeKeyWritable obj= new CompositeKeyWritable(city,year);
		context.write(obj,season1);
		
		}
		
	}

}
