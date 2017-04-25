
public class Plant {
	
	private int nValue;
	private double spreadChance;
	private double respawnChance;
	private Location aLocation;
	
	public Plant(){
		nValue = 2;
		spreadChance = 0.45;
		respawnChance = 0.0;
		aLocation = new Location(0,0);
	}
	
	public Plant(int a, double b, double c){
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

	public Location getLocation() {
		return new Location(aLocation.getI(), aLocation.getJ());
	}
}
