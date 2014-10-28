
public class Food {
	
	public int nValue;
	public double spreadChance;
	public double respawnChance;
	
	public Food(){
		nValue = 3;
		spreadChance = 0.5;
		respawnChance = 0.1;
	}
	
	public Food(int a, double b, double c){
		nValue = a;
		spreadChance = b;
		respawnChance = c;
	}
	
	public boolean spread(){
		double chance = Math.random();
		return chance < spreadChance;
	}
	
	public boolean respawn(){
		double chance = Math.random();
		return chance < respawnChance;
	}
	
	public int getNValue(){
		return nValue;
	}
	
}
