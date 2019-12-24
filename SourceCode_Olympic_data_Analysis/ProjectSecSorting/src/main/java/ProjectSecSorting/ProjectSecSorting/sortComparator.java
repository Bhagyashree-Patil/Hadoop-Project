package ProjectSecSorting.ProjectSecSorting;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class sortComparator extends WritableComparator {

	
	


	public sortComparator() {
		super(CompositeKeyWritable.class,true);
		// TODO Auto-generated constructor stub
	}
@Override
	public int compare(WritableComparable a,WritableComparable b)
	{
		CompositeKeyWritable k1=(CompositeKeyWritable)a;
		CompositeKeyWritable k2=(CompositeKeyWritable)b;
		String year1=k1.getYear();
		String year2=k2.getYear();
		
		int result=year1.compareTo(year2);
		return -1*result;
	}

}
