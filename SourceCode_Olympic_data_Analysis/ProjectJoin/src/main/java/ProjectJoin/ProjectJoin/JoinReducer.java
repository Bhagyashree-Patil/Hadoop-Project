package ProjectJoin.ProjectJoin;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JoinReducer extends Reducer<Text,Text,Text,Text>{
	
	 public ArrayList<Text> listA = new ArrayList<Text>();       
	 public ArrayList<Text> listB = new ArrayList<Text>(); 
	 
	 Text tmp=new Text();
	 String jointype=null;

	 public void setup(Context context) {
		 //get type of join from configuration
		 
		 jointype=context.getConfiguration().get("join.type");
	 }
	
	  @Override     
	  protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException 
	  { 
		 listA.clear();
		 listB.clear();
		 
		 for(Text t:values)
		 {
			 if(t.charAt(0)=='A')
			 {
				 listA.add(new Text(t.toString().substring(1)));
			 }
			 else if(t.charAt(0)=='B')
			 {
				 listB.add(new Text(t.toString().substring(1)));
			 }
		 }
		 
	//now our listA and listB are ready
		 
		 if(jointype.equalsIgnoreCase("inner"))
		 {
			 if(!listA.isEmpty() && !listB.isEmpty()) 
			 {          
				 for (Text A: listA) 
				 {              
					 for (Text B: listB) 
					 	{                  
						 context.write(A, B);              
					 	}         
				  }
			 }
		 }
		 

	  }
}

