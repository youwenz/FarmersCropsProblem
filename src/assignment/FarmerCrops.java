package assignment;
import java.util.*;
import java.io.*;

public class FarmerCrops 
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Farmer Crops Selection Problem!");
        int choice;
        
        ArrayList<Crop> crops = new ArrayList<>();
        int farmlandArea = 0;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Read input from file");
            System.out.println("2. Read input from console");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    crops = handleInputFromFile();
                    farmlandArea = handleFarmlandArea();
                    break;
                case 2:
                    crops = handleInputFromConsole();
                    farmlandArea = handleFarmlandArea();
                    break;
                case 3:
                    System.out.println("Exiting the Farm Knapsack application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
       
            if (choice != 3 && !crops.isEmpty()) {
            	System.out.println();
            	displayData(crops); 
            	
            	long dpStartTime = System.nanoTime();
            	DynamicProgramming dp = new DynamicProgramming(crops, farmlandArea);
            	int dpMaxProfit = dp.findMaxProfit();
		        ArrayList<Crop> dpSelectedCrops = dp.cropSelection();
		        long dpEndTime = System.nanoTime();
		        
		        System.out.println("\nThe optimal crop selection according to dynamic programming:");
		        for (Crop c: dpSelectedCrops)
		        	System.out.println(c.getCropName() + " ");	      
		        System.out.println("Profit yield from dynamic programming: $" + dpMaxProfit);
		        System.out.println("This algorithm took " + (dpEndTime-dpStartTime) + " nanoseconds");
		        
		        long grStartTime = System.nanoTime();
		        Greedy gr = new Greedy(crops, farmlandArea);
		        int grMaxProfit = gr.findMaxProfit();
		        ArrayList<Crop> grSelectedCrops = gr.cropSelection();
		        long grEndTime = System.nanoTime();
		       
		        System.out.println("\nThe optimal crop selection according to greedy algorithm:");
		        for (Crop c: grSelectedCrops)
		        	System.out.println(c.getCropName() + " ");	      
		        System.out.println("Profit yield from greedy algorithm: $" + grMaxProfit);
		        System.out.println("This algorithm took " + (grEndTime-grStartTime) + " nanoseconds");
		        choice = 3;
            }
        }while (choice != 3);
    }
   
	//input from file
	public static ArrayList<Crop> handleInputFromFile() {
    	ArrayList<Crop> crops = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter input file name: ");
        String fileName = scanner.nextLine();
        
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            int n = fileScanner.nextInt();
            crops = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                String cropName = fileScanner.next();
                int areaRequirement = fileScanner.nextInt();
                int profit = fileScanner.nextInt();
                crops.add(new Crop(cropName, areaRequirement, profit));
            }
            
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        
        return crops;
    }

	//input from console
    public static ArrayList<Crop> handleInputFromConsole() {
    	 ArrayList<Crop> crops = new ArrayList<>();
         Scanner scanner = new Scanner(System.in);
         boolean validInput= false;
         
         System.out.print("Enter the number of crops: ");
         int n = scanner.nextInt();
         
         for (int i = 0; i < n; i++) {
             System.out.print("Enter crop name: ");
             String cropName = scanner.next();
             int areaRequirement=0;
             while (areaRequirement <= 0) {
                 try {
                     System.out.print("Enter area requirement for " + cropName + ": ");
                     areaRequirement = scanner.nextInt();

                     if (areaRequirement <= 0) {
                         System.out.println("Area requirement must be greater than 0.");
                     }
                 } catch (InputMismatchException e) {
                     System.out.println("Input must be an integer.");
                     scanner.nextLine(); // Consume the invalid input
                 }
             }

             int profit = 0;

             while (profit <= 0) {
                 try {
                     System.out.print("Enter profit for " + cropName + ": ");
                     profit = scanner.nextInt();

                     if (profit <= 0) {
                         System.out.println("Profit must be greater than 0.");
                     }
                 } catch (InputMismatchException e) {
                     System.out.println("Input must be an integer.");
                     scanner.nextLine(); // Consume the invalid input
                 }
             }

             crops.add(new Crop(cropName, areaRequirement, profit));
         }

         return crops;
     }
    
    //Input farmland area
    public static int handleFarmlandArea() {
    	Scanner scanner = new Scanner(System.in);
        int farmlandArea=0;
        boolean validInput = false; //check if the input is a positive integer
        while(!validInput)
        {
	        try {
		    	System.out.print("Enter farmland area: ");
		    	farmlandArea = scanner.nextInt();
		    	if (farmlandArea<=0)
		    		System.out.println("Farmland area has to be at least 1 acre.");
		    	else
		    		validInput=true;
	        }catch(InputMismatchException e)
	        {
	        	System.out.println("The farmland area must be an integer.");
	        	scanner.nextLine(); //consume the invalid input
	        } 
        }
        return farmlandArea;
    }
    
    //display the data entered in tabular format
    public static void displayData(ArrayList<Crop> crops)
    {
    	System.out.println("The data entered is:");
        System.out.printf("%-15s %-8s %-8s%n", "Crop Name", "Profit", "Area");
        for (Crop c: crops)
        {
       	 System.out.printf("%-15s %-8d %-8d%n" , c.getCropName(), c.getProfit(), c.getAreaRequirement());
        }
        
    }
}
