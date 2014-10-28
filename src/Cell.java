
public class Cell {
	
	private Food aFood;
	private Feeder aFeeder;
	
	public Cell()
	{
		aFood = null;
		aFeeder = null;
	}
	
	public void addFood(Food pFood)
	{
		aFood = pFood;
	}
	
	public void addFeeder(Feeder pFeeder)
	{
		aFeeder = pFeeder;
	}
	
	public Food getFood()
	{
		return aFood;
	}
	
	public Feeder getFeeder()
	{
		return aFeeder;
	}
	
	public void removeFood()
	{
		aFood = null;
	}
	
	public void removeFeeder()
	{
		aFeeder = null;
	}

	public boolean hasFeeder() 
	{
		return aFeeder != null;
	}

	public boolean hasFood()
	{
		return aFood != null;
	}
	
	@Override
	public String toString()
	{
		if (aFood != null && aFeeder != null)
			return "[o]";
		if (aFood == null && aFeeder != null)
			return " o ";
		if (aFood != null && aFeeder == null)
			return "[ ]";
		if (aFood == null && aFeeder == null)
			return "|_|";
		return "|_|";
	}
	
}
