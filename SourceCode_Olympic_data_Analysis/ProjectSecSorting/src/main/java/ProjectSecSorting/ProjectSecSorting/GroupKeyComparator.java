package ProjectSecSorting.ProjectSecSorting;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupKeyComparator extends WritableComparator{

	public GroupKeyComparator() 
	{
		super(CompositeKeyWritable.class,true);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int compare(WritableComparable a,WritableComparable b)
	{
		CompositeKeyWritable k1=(CompositeKeyWritable)a;
		CompositeKeyWritable k2=(CompositeKeyWritable)b;
		String city1=k1.getCity();
		String city2=k2.getCity();
		
		int result=city1.compareTo(city2);
		return result;
	}

}

