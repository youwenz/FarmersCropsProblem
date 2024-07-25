package assignment;
import java.util.*;


public class Greedy extends Algorithm
{
    public Greedy(ArrayList<Crop> crops, int farmlandArea) 
    {
        super(crops, farmlandArea);
    }

    public int findMaxProfit() {
        crops.sort((a, b) -> Integer.compare(b.getProfit(), a.getProfit())); // Sort crops by profit (highest first)
        
        int maxProfit = 0;
        int remainingArea = farmlandArea;

        for (Crop crop : crops) {
            if (crop.getAreaRequirement() <= remainingArea) {
                maxProfit += crop.getProfit();
                remainingArea -= crop.getAreaRequirement();
            }
        }

        return maxProfit;
    }

    public ArrayList<Crop> cropSelection() 
    {
        crops.sort((a, b) -> Integer.compare(b.getProfit(), a.getProfit())); // Sort crops by profit (highest first)
        
        ArrayList<Crop> selectedCrops = new ArrayList<>();
        int remainingArea = farmlandArea;

        for (Crop crop : crops) {
            if (crop.getAreaRequirement() <= remainingArea) {
                selectedCrops.add(crop);
                remainingArea -= crop.getAreaRequirement();
            }
        }

        return selectedCrops;
    }
}