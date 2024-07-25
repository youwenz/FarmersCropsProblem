package assignment;
import java.util.*;

public abstract class Algorithm 
{
	
	ArrayList<Crop> crops;
	int farmlandArea;
	public Algorithm(ArrayList<Crop> crops, int farmlandArea)
	{
		this.crops = crops;
		this.farmlandArea = farmlandArea;
	}
	public abstract int findMaxProfit();
	
	public abstract ArrayList<Crop> cropSelection();
	
	
	
}
