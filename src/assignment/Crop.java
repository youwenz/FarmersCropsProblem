package assignment;

public class Crop 
{
	String cropName;
	int areaRequirement;
	int profit;
	
	public Crop(String crops, int areaRequirement, int profit) 
	{
        this.cropName = crops;
        this.areaRequirement = areaRequirement;
        this.profit = profit;
    }
	
	public String getCropName()
	{
		return cropName;
	}
	
	public int getProfit()
	{
		return profit;
	}
	
	public int getAreaRequirement()
	{
		return areaRequirement;
	}
}
