package ProjectTopMedalWinner.ProjectTopMedalWinner;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class TopMedalWinReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
	
	 protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
	        int sum=0;
	        IntWritable result = new IntWritable();
	        for(IntWritable i: values){
	            sum+=i.get();
	        }
	        result.set(sum);
	        context.write(key, result);
	    }
	

}
