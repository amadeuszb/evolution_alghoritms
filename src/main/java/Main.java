import java.util.*;

public class Main {


    private static double beginOfSquare = -5.12;
    private static double endOfSquare = 5.12;
    private static double sizeOfPopulation = 20;
    private static double amountOfEras = 200;
    private static LinkedList<Individual> population;

    private static int convertFromBinary(byte[] chromosome) {
        int initializeValue = 0;
        int position = 0;
        for (byte b : chromosome) {
            initializeValue |= b << position;
            position++;
        }
        return initializeValue;
    }

    private static byte[] convertToBinary(int chromosome, int sizeOfChromosome) {
        byte[] byteChromosome = new byte[sizeOfChromosome];
        for (int i = 0; i < sizeOfChromosome; i++) {
            byteChromosome[i] = (byte) ((chromosome & (1 << i)) >> i);
        }
        return byteChromosome;
    }

    private static double convertFromBinaryDecimal(byte[] chromosome, int sizeOfChromosome) {
        int numeralChromosome = convertFromBinary(chromosome);
        return beginOfSquare + numeralChromosome * (endOfSquare - beginOfSquare) / ((Math.pow(2, sizeOfChromosome) - 1));
    }

    private static byte[] convertFromDecimalToBinary(double chromosome, int sizeOfChromosome) {
        int finishChromosome = (int) ((chromosome - beginOfSquare) / (endOfSquare - beginOfSquare) * ((Math.pow(2, sizeOfChromosome) - 1)));
        return convertToBinary(finishChromosome, sizeOfChromosome);
    }

    private static byte[][] crossoverOnePoint(byte[] individualOne, byte[] individualTwo) {
        int sizeOfChromosome = individualOne.length;
        byte[][] outArray = new byte[2][];
        outArray[0] = Arrays.copyOf(individualOne, sizeOfChromosome);
        outArray[1] = Arrays.copyOf(individualTwo, sizeOfChromosome);
        Random random = new Random();
        int pointOfCrossover = random.nextInt(sizeOfChromosome);
        for (int i = pointOfCrossover; i < sizeOfChromosome; i++) {
            outArray[0][i] = individualTwo[i];
            outArray[1][i] = individualOne[i];
        }
        return outArray;
    }

    private static byte[] boundaryMutation(byte[] chromosome) {
        byte boundaryValue = chromosome[chromosome.length - 1];
        if (boundaryValue == 0) chromosome[chromosome.length - 1] = 1;
        else chromosome[chromosome.length - 1] = 0;
        return chromosome;
    }

    private static LinkedList<Individual> getInitialPopulation() {
        Random random = new Random();
        LinkedList<Individual> population = new LinkedList<Individual>();
        for (int i = 0; i < sizeOfPopulation; i++) {
            Individual individual = new Individual();
            double x1 = beginOfSquare + (endOfSquare - beginOfSquare) * random.nextDouble();
            double x2 = beginOfSquare + (endOfSquare - beginOfSquare) * random.nextDouble();
            individual.setX1(x1);
            individual.setX2(x2);
            population.add(individual);
        }
        return population;
    }

    private static LinkedList<Individual> evaluation(LinkedList<Individual> population) {
        for (Individual individual : population) {
            individual.setY(fun(individual.getX1(), individual.getX2()));
        }
        return population;
    }

    private static LinkedList<Individual> rouletteMinimum(LinkedList<Individual> population) {
        double sumOfResults = 0;
        for (Individual individual : population) {
            sumOfResults += Math.abs(individual.getY());
        }

        LinkedList<RouletteIndividual> propabilityPopulation = new LinkedList<RouletteIndividual>();
        for (Individual x : population) {
            propabilityPopulation.add(new RouletteIndividual(x, 0, (Math.abs(x.getY()) / sumOfResults)));
        }
        Collections.sort(propabilityPopulation); //TODO check if it is correct sort
        double previousSum = 0;
        for (RouletteIndividual individual : propabilityPopulation) {
            individual.setDistribuant(previousSum + individual.getPropability());
            previousSum += individual.getPropability();
        }
        Random random = new Random();
        LinkedList<Individual> newPopulation = new LinkedList<Individual>();
        for (int i = 0; i < sizeOfPopulation; i++) {
            double randomNumber = random.nextDouble();
            for (int j = 0; j < sizeOfPopulation; j++) {
                if (propabilityPopulation.get(j).getDistribuant() > randomNumber) {
                    newPopulation.add(propabilityPopulation.get(j).getIndividual());
                    break;
                }
            }
        }
        return newPopulation;
    }

    private static LinkedList<Individual> crossover(LinkedList<Individual> population) {
        LinkedList<Individual> newPopulation = new LinkedList<Individual>();
        Random random = new Random();
        for (int i = 0; i < sizeOfPopulation; i += 2) {
            if (random.nextDouble() > 0.2) {
                Individual ione = population.get(i);
                Individual itwo = population.get(i + 1);
                byte[] ioneBinary = convertFromDecimalToBinary(ione.getX1(), DropwaveFunction.sizeOfBinaryString());
                byte[] itwoBinary = convertFromDecimalToBinary(itwo.getX1(), DropwaveFunction.sizeOfBinaryString());
                byte[][] newTwoIndividuals = crossoverOnePoint(ioneBinary, itwoBinary);
                ione.setX1(convertFromBinaryDecimal(newTwoIndividuals[0], DropwaveFunction.sizeOfBinaryString()));
                itwo.setX1(convertFromBinaryDecimal(newTwoIndividuals[1], DropwaveFunction.sizeOfBinaryString()));
                byte[] ioneBinaryX2 = convertFromDecimalToBinary(ione.getX2(), DropwaveFunction.sizeOfBinaryString());
                byte[] itwoBinaryX2 = convertFromDecimalToBinary(itwo.getX2(), DropwaveFunction.sizeOfBinaryString());
                byte[][] newTwoIndividualsX2 = crossoverOnePoint(ioneBinaryX2, itwoBinaryX2);
                ione.setX2(convertFromBinaryDecimal(newTwoIndividualsX2[0], DropwaveFunction.sizeOfBinaryString()));
                itwo.setX2(convertFromBinaryDecimal(newTwoIndividualsX2[1], DropwaveFunction.sizeOfBinaryString()));
            }
        }
        return population;
    }

    private static LinkedList<Individual> mutate(LinkedList<Individual> population) {
        LinkedList<Individual> newPopulation = new LinkedList<Individual>();
        Random random = new Random();
        for (int i = 0; i < sizeOfPopulation; i++) {
            if (random.nextDouble() < 0.2) {
                Individual ione = population.get(i);
                byte[] ioneBinary = convertFromDecimalToBinary(ione.getX1(), DropwaveFunction.sizeOfBinaryString());
                byte[] newTwoIndividuals = boundaryMutation(ioneBinary);
                ione.setX1(convertFromBinaryDecimal(newTwoIndividuals, DropwaveFunction.sizeOfBinaryString()));
                byte[] ioneBinaryX2 = convertFromDecimalToBinary(ione.getX2(), DropwaveFunction.sizeOfBinaryString());
                byte[] newTwoIndividualsX2 = boundaryMutation(ioneBinaryX2);
                ione.setX2(convertFromBinaryDecimal(newTwoIndividualsX2, DropwaveFunction.sizeOfBinaryString()));
            }
        }
        return population;
    }

    public static void main(String[] args) {
        LinkedList<Individual> initialPopulation = getInitialPopulation();
        for (int i = 0; i < amountOfEras; i++) {
            initialPopulation = evaluation(initialPopulation);
            initialPopulation = rouletteMinimum(initialPopulation);
            initialPopulation = crossover(initialPopulation);
            initialPopulation = mutate(initialPopulation);
        }
        for (Individual i : evaluation(initialPopulation)) {
            System.out.println(i.getY());
        }
    }

    public static double fun(double x1, double x2) {
        double sumSquares = x1 * x1 + x2 * x2;
        return -(1 + Math.cos(12 * Math.sqrt(sumSquares))) / (0.5 * sumSquares + 2);
    }
}
