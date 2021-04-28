import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.function.IntToDoubleFunction;

public class main {

	public static void main(String[] args) {

		Random randomNumberGenerator = new Random();
		Algoritimo2 algorithm = new Algoritimo2();

		Set<Integer> tempPopulation = new HashSet<>();

		while (tempPopulation.size() < 8) {
			tempPopulation.add(randomNumberGenerator.nextInt(1000));
		}

		Iterator<Integer> tempPopulationIterator = tempPopulation.iterator();

		List<Integer> population = new ArrayList<Integer>();

		while (tempPopulationIterator.hasNext()) {
			population.add(tempPopulationIterator.next());
		}

		Iterator<Integer> populationIterator = null;

		populationIterator = population.iterator();

		Integer fit = algorithm.getFit(population.get(0));
		int i = 0;

		while (populationIterator.hasNext()) {

			Integer temporaryFit = algorithm.getFit(populationIterator.next());
			if (fit.compareTo(temporaryFit) > 0) {
				fit = temporaryFit;
			}
		}

		int generation = 0;
		boolean fitFound = false;
		while (generation < 500) {

			System.out.println("Geração de número: " + i);

			System.out.println("Tamanho da população inicial: " + population.size());

			population = algorithm.getIndividuosAptos(population);

			System.out.println("Tamanho da população apta: " + population.size());

			population = algorithm.twoPointCrossover(population);

			System.out.println("Tamanho da população reproduzida: " + population.size());

			population = algorithm.mutation(population, 0.01);

			Iterator<Integer> newPopulationIterator = population.iterator();

			while (newPopulationIterator.hasNext()) {

				Integer individual = newPopulationIterator.next();
				Integer tempFit = algorithm.getFit(individual);

				System.out.println("Individuo " + individual + " de fit " + tempFit);

				if (fit.compareTo(tempFit) > 0) {
					fit = tempFit;
					fitFound = true;
				}
			}

			System.out.println("Tamanho da população é: " + population.size());
			System.out.println("O fit é de: " + fit);
			System.out.println("Fit found is:" + fitFound);
			System.out.println("\n\n\n");

			generation++;
			i++;

		}
	}
}
