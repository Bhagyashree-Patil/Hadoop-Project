package ProjectTopMedalWinner.ProjectTopMedalWinner;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class M3 extends Mapper<Text, IntWritable,IntWritable,Text> { 
	


private TreeMap<Integer, String> tmap; 

//Called once in the beginning before the method
@Override
public void setup(Context context) throws IOException, InterruptedException 
{ 
tmap = new TreeMap<Integer, String>(Collections.reverseOrder()); 
} 

//Called once for each key/value pair in the input split
@Override
public void map(Text key, IntWritable value, Context context) throws IOException, InterruptedException 
{ 
	Integer mcount=Integer.parseInt(value.toString());
	
 
String name=key.toString();

tmap.put(mcount,name); 

if (tmap.size() > 10) 
{ 
tmap.remove(tmap.lastKey()); 
} 
} 

//Called once at the end of the task
@Override
public void cleanup(Context context) throws IOException, InterruptedException 
{ 
for (Map.Entry<Integer, String> entry : tmap.entrySet()) 
{ 

Integer count = entry.getKey(); 
String name = entry.getValue(); 

context.write(new IntWritable(count),new Text(name)); 
} 
} 
} 
