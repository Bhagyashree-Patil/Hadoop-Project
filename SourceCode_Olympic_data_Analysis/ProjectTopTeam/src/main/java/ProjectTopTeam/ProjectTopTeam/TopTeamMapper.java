package ProjectTopTeam.ProjectTopTeam;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class TopTeamMapper extends Mapper<LongWritable, Text, Text, LongWritable>{
	
	
	
	private TreeMap<Long, String> tmap; 

	//Called once in the beginning before the method
	@Override
	public void setup(Context context) throws IOException, 
			InterruptedException 
	{ 
	tmap = new TreeMap<Long, String>(); 
	} 

	//Called once for each key/value pair in the input split
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
	{ 

	String[] tokens = value.toString().split("\t"); 
	String [] temp2=tokens[4].split("=");
	String v=temp2[1];

	//String team = tokens[0]; 
	String team = value.toString();
	long no_of_medals = Long.parseLong(v); 
	

	tmap.put(no_of_medals, team); 

	if (tmap.size() > 10) 
	{ 
	tmap.remove(tmap.firstKey()); 
	} 
	} 

	//Called once at the end of the task
	@Override
	public void cleanup(Context context) throws IOException, 
			InterruptedException 
	{ 
	for (Map.Entry<Long, String> entry : tmap.entrySet()) 
	{ 

	long count = entry.getKey(); 
	String team = entry.getValue(); 

	context.write(new Text(team), new LongWritable(count)); 
	} 
	} 
	

}
