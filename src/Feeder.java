import java.util.Random;


public class Feeder {
	
	private int aBMI;
	private int rNeed;
	private double findChance;
	
	public Feeder(){
		aBMI = 4;
		findChance = 0.5;
		rNeed = 6;
	}
	
	public Feeder(int a, double b, int c){
		aBMI = a;
		findChance = b;
		rNeed = c;
	}
	
	public int getBmi(){
		return aBMI;
	}
	
	public boolean doesEat(){
		double chance = Math.random();
		if(chance < findChance){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean doesSurvive(){
		return aBMI > 0;
	}

	public boolean doesReproduce(){
		return aBMI>rNeed;
	}
	
	public int move() 
	{
		Random r = new Random();
		return r.nextInt(4);
	}

	public void feed(Cell pCell) {
		if (pCell.hasFood())
		{
			if (doesEat()) 
			{
				eatFood(pCell.getFood());
				if (!pCell.getFood().respawn())
				{
					pCell.removeFood();
				}
			}
		}
		else
		{
			aBMI--;
		}
		
	}

	private void eatFood(Food pFood) 
	{
		aBMI += pFood.getNValue();
	}
}
