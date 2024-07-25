package assignment;
import java.util.*;

public class DynamicProgramming extends Algorithm
{
	int[][] sumProfit;
	int numCrops;
	
	
	public DynamicProgramming(ArrayList<Crop> crops, int farmlandArea)
	{
		super(crops, farmlandArea);
		numCrops = crops.size();
	}
	
	public int findMaxProfit()
	{
		sumProfit = new int[farmlandArea+1][numCrops+1];
		for(int i=1; i<= farmlandArea; i++)
			for (int j=1; j<=numCrops; j++)
			{
				if(crops.get(j-1).areaRequirement <= i)
				{
					sumProfit[i][j] = Math.max(sumProfit[i][j-1], 
							crops.get(j-1).profit + sumProfit[i - crops.get(j-1).areaRequirement][j - 1]);
				}
				else
				{
					sumProfit[i][j] = sumProfit[i][j-1];
				}
			}
		
	    
	    return sumProfit[farmlandArea][numCrops];
	}
	
	public ArrayList<Crop> cropSelection()
	{
		ArrayList<Crop> selectedCrops = new ArrayList<>();
	    int currentArea = farmlandArea;
	    int currentCrop = numCrops;

	    while (currentArea > 0 && currentCrop > 0) {
	        if (sumProfit[currentArea][currentCrop] != sumProfit[currentArea][currentCrop - 1]) {
	            selectedCrops.add(crops.get(currentCrop-1)); 
	            currentArea -= crops.get(currentCrop-1).areaRequirement;
	        }
	        currentCrop--;
	    }

	    Collections.reverse(selectedCrops); // Reverse the list to display in correct order

	    return selectedCrops;
	}
	
	
}
