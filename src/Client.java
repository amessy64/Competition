import java.util.ArrayList;


public class Client 
{
	public static void main(String[] args)
	{
		int size = 6;
		ArrayList<Feeder> feeders = new ArrayList<>();
		ArrayList<Food> foods = new ArrayList<>();
		
		Feeder f1 = new Feeder();
		Feeder fred = new Feeder();
		feeders.add(f1);
		feeders.add(fred);
		
		Food f2 = new Food();
		Food corn = new Food();
		foods.add(f2);
		foods.add(corn);
		
		Environment e = new Environment(size, foods, feeders);
		
		int years = 500;
		e.life(years);
	}
}
