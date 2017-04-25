
public class Cell {
	
	private Plant aPlant;
	private Animal aAnimal;
	
	public Cell()
	{
		aPlant = null;
		aAnimal = null;
	}
	
	public void addPlant(Plant pPlant)
	{
		aPlant = pPlant;
	}
	
	public void addAnimal(Animal pAnimal)
	{
		aAnimal = pAnimal;
	}
	
	public Plant getPlant()
	{
		return aPlant;
	}
	
	public Animal getAnimal()
	{
		return aAnimal;
	}
	
	public void removePlant()
	{
		aPlant = null;
	}
	
	public void removeAnimal()
	{
		aAnimal = null;
	}

	public boolean hasAnimal() 
	{
		return aAnimal != null;
	}

	public boolean hasPlant()
	{
		return aPlant != null;
	}
	
	@Override
	public String toString()
	{
		if (aPlant != null && aAnimal != null)
			return "{*}";
		if (aPlant == null && aAnimal != null)
			return "{ }";
		if (aPlant != null && aAnimal == null)
			return " * ";
		if (aPlant == null && aAnimal == null)
			return "   ";
		return "   ";
	}
	
}
