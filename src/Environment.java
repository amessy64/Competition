
import java.util.ArrayList;
import java.util.Random;

public class Environment {
	
	private ArrayList<Animal> aAnimals;
	private ArrayList<Plant> aPlants;
	private Cell [][] aCell;
	
	public Environment(int pSize, ArrayList<Plant> pFood, ArrayList<Animal> pAnimals)
	{
		aAnimals = pAnimals;
		aPlants = pFood;
		aCell = new Cell[pSize][pSize];
		for (int i = 0; i < aCell.length; i++)
			for (int j = 0; j < aCell.length; j++)
				aCell[i][j] = new Cell();
		
		Random r = new Random();
		
		for(Plant f : pFood)
		{
			aCell[r.nextInt(pSize)][r.nextInt(pSize)].addPlant(f);
		}
		
		for(Animal f : pAnimals)
		{
			int i = r.nextInt(pSize);
			int j = r.nextInt(pSize);
			while (aCell[i][j].hasAnimal())
			{
				i = r.nextInt(pSize);
				j = r.nextInt(pSize);
			}
			Location l = new Location(i,j);
			f.setLocation(l);
			aCell[i][j].addAnimal(f);
		}
		
	}

	public void life(int years) {
		while (years > 0)
		{
			moveOrganisms();
			years--;
		}
		
	}
	
	private void printGrid() {
		String nums = " ";
		for (int i = 0; i < aCell.length; i++)
		{
			nums += " "+i+" ";
		}
		System.out.println(nums);

		for (int i = 0; i < aCell.length; i++)
		{
			String s = i+"";
			for (int j = 0; j < aCell.length; j++)
			{
				s += aCell[i][j].toString();
			}
			System.out.println(s);
		}
		System.out.println("");
	}

	//deals with moving feeders, but also calls a method to see if they eat 
	private void moveOrganisms() {
		Object[] feeders = aAnimals.toArray();
		
		//debugging
		//for (Feeder f : aFeeders)
		//{
		//	System.out.println("Feeder: "+f.getI()+","+f.getJ()+" has BMI: "+f.getBmi());
		//}

		for (Object lFeeder : feeders)
		{
			int i = ((Animal) lFeeder).getI();
			int j = ((Animal) lFeeder).getJ();
			handleFeeder(i, j);
		}
		

		for (int i = 0; i < aCell.length; i++)
		{
			for (int j = 0; j < aCell.length; j++)
			{
				if (aCell[i][j].hasPlant())
				{
					handleFood(i, j);
				}
			}
		}

		printGrid();
		System.out.println("");
	}
	
	private void handleFeeder(int i, int j) {
		Animal moving = aCell[i][j].getAnimal();
		if (moving.doesSurvive())
		{
			if (aCell[i][j].hasPlant() && moving.doesEat())
			{
				Plant lFood = aCell[i][j].getPlant();
				moving.feed(lFood);
				if (!lFood.respawn())
				{
					foodDies(lFood, new Location(i,j));
				}
			}
			Location lLocation = chosenSpot(moving);
			if (lLocation.getI() != -1)
			{
				moving.setLocation(lLocation);
				aCell[i][j].removeAnimal();
				aCell[lLocation.getI()][lLocation.getJ()].addAnimal(moving);
			}
			
			if (moving.doesReproduce())
			{
				Animal f = new Animal(moving);
				f.setLocation(new Location(moving.getI(), moving.getJ()));
				lLocation = chosenSpot(f);
				if (lLocation.getI() != -1)
				{
					f.setLocation(lLocation);
					aCell[lLocation.getI()][lLocation.getJ()].addAnimal(f);
					aAnimals.add(f);
				}
			}
		} 
		else
		{
			aCell[i][j].removeAnimal();
			aAnimals.remove(moving);
		}
		
	}
	
	private Location chosenSpot(Animal pFeeder) {
		Location dest = pFeeder.altMove();
		int y = dest.getI();
		int x = dest.getJ();
		
		if (x >= 0 && y >= 0 && x < aCell.length && y < aCell.length && !aCell[y][x].hasAnimal())
		{
			return new Location(y,x);
		}
		else
		{
			//only one of these can be true
			if (x < 0)
			{
				x += 2;
			}
			if (y < 0)
			{
				y += 2;
			}
			if (x > aCell.length-1)
			{
				x -= 2;
			}
			if (y > aCell.length-1)
			{
				y -= 2;
			}
			if (!aCell[y][x].hasAnimal())
			{
				return new Location(y,x);
			}
		}
		//if -1,-1, then either dont move or dont add child to aCell
		return new Location(-1,-1);
	}

	private void foodDies(Plant pFood, Location l) {
		aPlants.remove(pFood);
		aCell[l.getI()][l.getJ()].removePlant();
	}

	private void handleFood(int i, int j) {
		Plant f = aCell[i][j].getPlant();
		if (f.spread())
		{
			Plant f1 = new Plant();
			Plant f2 = new Plant();
			Plant f3 = new Plant();
			Plant f4 = new Plant();
			
			if (i+1 < aCell.length && f.spread())
			{
				aCell[i+1][j].addPlant(f1);
				aPlants.add(f1);
			}
			if (j+1 < aCell.length && f.spread())
			{
				aCell[i][j+1].addPlant(f1);
				aPlants.add(f2);
			}
			if (i > 0 && f.spread())
			{
				aCell[i-1][j].addPlant(f1);
				aPlants.add(f3);
			}
			if (j > 0 && f.spread())
			{
				aCell[i][j-1].addPlant(f1);
				aPlants.add(f4);
			}
		}
		
	}
	
	
}
