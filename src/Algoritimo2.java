import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Algoritimo2 {

	public Integer getFit(Integer individuo) {

		return ((individuo * 2) - (18 * individuo)) + 24;
	}

	public List<Integer> getIndividuosAptos(List<Integer> population) {

		List<Integer> fitList = new ArrayList<Integer>();
		List<Integer> newPopulation = new ArrayList<Integer>();
		Iterator<Integer> populationIterator = population.iterator();

		for (Integer integer : population) {
			fitList.add(this.getFit(integer));
		}

		Collections.sort(fitList);

		while (populationIterator.hasNext()) {
			Integer individual = populationIterator.next();
			Integer individualFit = this.getFit(individual);

			for (int i = 0; i < 4; i++) {
				Integer fit = fitList.get(i);
				if (individualFit.compareTo(fit) == 0) {
					if (newPopulation.size() < 4) {
						newPopulation.add(individual);
					}
				}
			}
		}

		return newPopulation;
	}

	public List<Integer> onePointCrossover(List<Integer> population) {

		List<Integer> newPopulation = new ArrayList<Integer>();

		Iterator<Integer> populationIterator = population.iterator();

		while (populationIterator.hasNext()) {
			newPopulation.add(populationIterator.next());
		}

		List<String> binaryPopulationList = new ArrayList<String>();

		for (Integer individual : population) {

			String firstIndividual = Integer.toBinaryString(individual);

			if ((Integer.toBinaryString(individual)).length() < 8) {
				int preencher = 8 - (Integer.toBinaryString(individual)).length();
				for (int i = 0; i < preencher; i++) {
					firstIndividual = "0" + firstIndividual;
				}
			}
			binaryPopulationList.add(firstIndividual.substring(0, 4));
			binaryPopulationList.add(firstIndividual.substring(4));
		}

		while (!binaryPopulationList.isEmpty()) {

			String firstPartParentOne = binaryPopulationList.get(0);
			String secondPartParentOne = binaryPopulationList.get(1);
			String firstPartParentTwo = binaryPopulationList.get(2);
			String secondPartParentTwo = binaryPopulationList.get(3);
			for (int j = 0; j < 4; j++) {
				binaryPopulationList.remove(0);
			}

			newPopulation.add(Integer.parseInt((firstPartParentOne + secondPartParentTwo), 2));
			newPopulation.add(Integer.parseInt((firstPartParentTwo + secondPartParentOne), 2));

		}

		return newPopulation;
	}

	public List<Integer> mutation(List<Integer> population, Double taxaMutacao) {

		List<String> binaryPopulationList = new ArrayList<String>();
		List<String> newBinaryPopulationList = new ArrayList<String>();
		Random randomNumberGenerator = new Random();
		String zero = "0";
		String one = "1";

		for (Integer individual : population) {

			String firstIndividual = Integer.toBinaryString(individual);

			if ((Integer.toBinaryString(individual)).length() < 8) {
				int preencher = 8 - (Integer.toBinaryString(individual)).length();
				for (int i = 0; i < preencher; i++) {
					firstIndividual = "0" + firstIndividual;
				}
			}
			binaryPopulationList.add(firstIndividual);
		}

		Iterator<String> binaryPopulationListIterator = binaryPopulationList.iterator();

		while (binaryPopulationListIterator.hasNext()) {

			boolean register = true;

			String binary = binaryPopulationListIterator.next();

			for (int i = 1; i < binary.length(); i++) {

				if (taxaMutacao.compareTo(randomNumberGenerator.nextDouble()) > 0) {

					String str1 = binary.substring(0, i - 1);
					String str2 = String.valueOf(binary.charAt(i - 1));
					String str3 = binary.substring(i, binary.length());

					if (str2.equals(zero)) {
						str2 = one;
					} else if (str2.equals(one)) {
						str2 = zero;
					}

					String result = str1 + str2 + str3;

					newBinaryPopulationList.add(result);
					register = false;
				}
			}

			if (register == true) {
				newBinaryPopulationList.add(binary);
			}
		}

		return population;
	}

	public List<Integer> twoPointCrossover(List<Integer> population) {

		List<Integer> newPopulation = new ArrayList<Integer>();

		Iterator<Integer> populationIterator = population.iterator();

		while (populationIterator.hasNext()) {
			newPopulation.add(populationIterator.next());
		}

		List<String> binaryPopulationList = new ArrayList<String>();

		for (Integer individual : population) {

			String firstIndividual = Integer.toBinaryString(individual);

			if ((Integer.toBinaryString(individual)).length() < 8) {
				int preencher = 8 - (Integer.toBinaryString(individual)).length();
				for (int i = 0; i < preencher; i++) {
					firstIndividual = "0" + firstIndividual;
				}
			}
			binaryPopulationList.add(firstIndividual.substring(0, 1));
			binaryPopulationList.add(firstIndividual.substring(1, 7));
			binaryPopulationList.add(firstIndividual.substring(7));
		}

		while (!binaryPopulationList.isEmpty()) {

			String firstPartParentOne = binaryPopulationList.get(0);
			String secondPartParentOne = binaryPopulationList.get(1);
			String thirdPartParentOne = binaryPopulationList.get(2);
			String firstPartParentTwo = binaryPopulationList.get(3);
			String secondPartParentTwo = binaryPopulationList.get(4);
			String thirdPartParentTwo = binaryPopulationList.get(5);

			for (int j = 0; j < 6; j++) {
				binaryPopulationList.remove(0);
			}

			newPopulation.add(Integer.parseInt((firstPartParentOne + secondPartParentTwo + thirdPartParentOne), 2));
			newPopulation.add(Integer.parseInt((firstPartParentTwo + secondPartParentOne + thirdPartParentTwo), 2));

		}

		return newPopulation;
	}

}