
import java.util.ArrayList;
import java.util.Random;

public class Environment {
	
	private ArrayList<Feeder> aFeeders;
	private ArrayList<Food> aFood;
	private Cell [][] aCell;
	
	public Environment(int pSize, ArrayList<Food> pFood, ArrayList<Feeder> pFeeders)
	{
		aFeeders = pFeeders;
		aFood = pFood;
		aCell = new Cell[pSize][pSize];
		for (int i = 0; i < aCell.length; i++)
			for (int j = 0; j < aCell.length; j++)
				aCell[i][j] = new Cell();
		
		Random r = new Random();
		
		for(Food f : pFood)
		{
			aCell[r.nextInt(pSize)][r.nextInt(pSize)].addFood(f);
		}
		
		for(Feeder f : pFeeders)
		{
			aCell[r.nextInt(pSize)][r.nextInt(pSize)].addFeeder(f);
		}
		
	}

	public void life(int years) {
		while (years > 0)
		{
			moveFeeders();
			//development method, will be replaced by an observer class for extra utilities
			years--;
		}
		
	}
	
	private void printGrid() {
		for (int i = 0; i < aCell.length; i++)
		{
			String s = "";
			for (int j = 0; j < aCell.length; j++)
			{
				s += aCell[i][j].toString();
			}
			System.out.println(s);
		}
		System.out.println("");
	}

	//deals with moving feeders, but also calls a method to see if they eat 
	private void moveFeeders() {
		for (int i = 0; i < aCell.length; i++)
		{
			for (int j = 0; j < aCell.length; j++)
			{
				if (aCell[i][j].hasFeeder())
				{
					handleFeeder(i, j);
					printGrid();
				}
				if (aCell[i][j].hasFood())
				{
					handleFood(i, j);
				}
			}
		}

		
	}

	private void handleFeeder(int i, int j) {
		//0 is up, 1 is left, 2 is down, 3 is right
		Feeder moving = aCell[i][j].getFeeder();
		aCell[i][j].removeFeeder();
		
		if (moving.doesSurvive())
		{
			moving.feed(aCell[i][j]);
			int dest = moving.move();

			if (dest == 0)
			{
				if (i > 0)
				{
					aCell[i-1][j].addFeeder(moving);
				} 
				else
				{
					aCell[i+1][j].addFeeder(moving);
				}
				
			}
			if (dest == 1)
			{
				if (j > 0)
				{
					aCell[i][j-1].addFeeder(moving);
				} 
				else
				{
					aCell[i][j+1].addFeeder(moving);
				}		
			}
			if (dest == 2)
			{
				if (i < aCell.length-1)
				{
					aCell[i+1][j].addFeeder(moving);
				} 
				else
				{
					aCell[i-1][j].addFeeder(moving);
				}
				
			}
			if (dest == 3)
			{
				if (j < aCell.length-1)
				{
					aCell[i][j+1].addFeeder(moving);
				} 
				else
				{
					aCell[i][j-1].addFeeder(moving);
				}
				
			}
		}
		if (moving.doesReproduce())
		{
			Feeder f = new Feeder();
			aFeeders.add(f);
			if (i+1 < aCell.length)
				aCell[i+1][j].addFeeder(f);
			else if (j+1 < aCell.length)
				aCell[i][j+1].addFeeder(f);
		}
	}
	
	private void handleFood(int i, int j) {
		Food f = aCell[i][j].getFood();
		if (f.spread())
		{
			Food f1 = new Food();
			Food f2 = new Food();
			Food f3 = new Food();
			Food f4 = new Food();
			
			if (i+1 < aCell.length)
			{
				aCell[i+1][j].addFood(f1);
				aFood.add(f1);
			}
			if (j+1 < aCell.length)
			{
				aCell[i][j+1].addFood(f1);
				aFood.add(f2);
			}
			if (i > 0)
			{
				aCell[i-1][j].addFood(f1);
				aFood.add(f3);
			}
			if (j > 0)
			{
				aCell[i][j-1].addFood(f1);
				aFood.add(f4);
			}
		}
		
	}
	
	
}
