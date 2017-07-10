import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints {
	private LineSegment[] SetLine;
	
    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
	    
	    ArrayList<LineSegment> ListLine = new ArrayList<>();
	    // check if the array is null
	    if (points == null) throw new IllegalArgumentException();
	    // check if some elements in the array are null
	    Point[] pointsArr = new Point[points.length];
	    for (int i = 0; i < points.length; i++)
	    {
	    	pointsArr[i] = points[i];
	    	if (pointsArr[i] == null) throw new IllegalArgumentException();
	    }
	    // check if there are duplicated points in the array
	    for (int i = 0; i < pointsArr.length-1; i++)
	    {
	    	for (int j = i+1; j < pointsArr.length; j++)
	    	{
	    		if (pointsArr[i].compareTo(pointsArr[j]) == 0) throw new IllegalArgumentException();
	    	}
	    }
	    
	    Arrays.sort(pointsArr);
	    
	    for (int i = 0; i < pointsArr.length; i++) 
	    {
		    for (int j = i+1; j < pointsArr.length; j++)
		    {
		    	double slp_1 = pointsArr[i].slopeTo(pointsArr[j]);
		    	for (int k = j+1; k < pointsArr.length; k++)
		    	{
		    		double slp_2 = pointsArr[j].slopeTo(pointsArr[k]);
		    		for (int l = k+1; l < pointsArr.length; l++)
		    		{
		    			double slp_3 = pointsArr[k].slopeTo(pointsArr[l]);
		    			LineSegment newLine = new LineSegment(pointsArr[i], pointsArr[l]);
		    			if (slp_1 == slp_2 && slp_2 == slp_3) 
		    			{
		    				ListLine.add(newLine);
		    			}
		    		}
		    	}
		    }
	    }
	    SetLine = ListLine.toArray(new LineSegment[ListLine.size()]);
    }

	public           int numberOfSegments()        // the number of line segments
    {
	    return SetLine.length;
    }
    public LineSegment[] segments()                // the line segments
    {
    	return Arrays.copyOf(SetLine, numberOfSegments());
    }
}
