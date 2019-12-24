package ProjectSecSorting.ProjectSecSorting;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;



public class CompositeKeyWritable implements WritableComparable {

	
	String city;
	String year;
	
	public CompositeKeyWritable() {
		
		
	}

	
	public CompositeKeyWritable(String city, String year) {
		super();
		this.city = city;
		this.year = year;
	}

	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		city=in.readUTF();
		year=in.readUTF();
		
	}

	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(city);
		out.writeUTF(year);
		
	}

	public int compareTo(Object o) {
		CompositeKeyWritable ck=(CompositeKeyWritable)o;
		String thisValue = this.getYear();
        String otherValue = ck.getYear();
        
        int result=thisValue.compareTo(otherValue);
        return (result< 0 ? -1 : (result == 0 ? 0 : 1));
	}

	
	@Override
	public String toString() {
		return "CompositeKeyWritable [city=" + city + ", year=" + year + "]";
	}

	
	
	
	

}
