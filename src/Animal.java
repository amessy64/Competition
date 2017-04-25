import java.util.Random;


public class Animal {
	
	private int aBMI;
	private int rNeed;
	private double findChance;
	private Location aLocation;
	
	public Animal(){
		aBMI = 6;
		findChance = 1;
		rNeed = 7;
		aLocation = new Location(0,0);
	}
	
	public Animal(int a, double b, int c){
		aBMI = a;
		findChance = b;
		rNeed = c;
	}

	public Animal(Animal pFeeder){
		aBMI = pFeeder.getBmi();
		findChance = pFeeder.getFindChance();
		rNeed = pFeeder.getNeed();
	}
	
	public int getBmi(){
		return aBMI;
	}

	private int getFindChance(){
		return aBMI;
	}

	private int getNeed(){
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
		return --aBMI > 0;
	}

	public boolean doesReproduce(){
		if (aBMI > rNeed)
		{
			halveBMI();
			return true;
		}
		return false;
	}
	
	private void halveBMI() {
		aBMI /= 2;		
	}

	public int move() 
	{
		Random r = new Random();
		return r.nextInt(4);
	}
	
	public Location altMove() 
	{
		//up
		Location dest = chooseLocation();
		return dest;
	}

	private Location chooseLocation() {
		Random r = new Random();
		int dest = r.nextInt(4);
		
		if (dest == 0)
			return new Location(aLocation.getI()+1, aLocation.getJ());
		//left
		if (dest == 1)
			return new Location(aLocation.getI(), aLocation.getJ()+1);
		//down
		if (dest == 2)
			return new Location(aLocation.getI()-1, aLocation.getJ());
		else //right
			return new Location(aLocation.getI(), aLocation.getJ()-1);
	}

	public void feed(Plant pFood) {
		eatFood(pFood);
	}

	private void eatFood(Plant pFood) 
	{
		aBMI += pFood.getNValue();
	}

	public void setLocation(Location pLocation) {
		this.aLocation = pLocation;
	}
	
	public int getI() 
	{
		return aLocation.getI();
	}
	
	public int getJ() 
	{
		return aLocation.getJ();
	}
}
