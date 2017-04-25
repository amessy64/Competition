import java.util.ArrayList;


public class Client 
{
	public static void main(String[] args)
	{
		int size = 9;
		ArrayList<Animal> animals = new ArrayList<>();
		ArrayList<Plant> plants = new ArrayList<>();
		
		Animal f1 = new Animal();
		Animal fred = new Animal();
		animals.add(f1);
		animals.add(fred);
		
		Plant f2 = new Plant();
		Plant corn = new Plant();
		plants.add(f2);
		plants.add(corn);
		
		Environment e = new Environment(size, plants, animals);
		
		int years = 60;
		e.life(years);
	}
}
