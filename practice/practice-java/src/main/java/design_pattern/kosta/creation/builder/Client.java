package design_pattern.kosta.creation.builder;

public class Client {

	public static void main(String[] args) {
		
		// TODO

		NutritionFacts.Builder builder = new NutritionFacts.Builder(240, 8);

		builder.calories(10).sodium(251).fat(20);
		NutritionFacts nutritionFacts = builder.build();

		System.out.println("nutritionFacts = " + nutritionFacts);

		NutritionFacts nutritionFacts1 = new NutritionFacts.Builder(240, 8).sodium(10).fat(2).calories(120).build();

		System.out.println("nutritionFacts1 = " + nutritionFacts1);

	}

}
