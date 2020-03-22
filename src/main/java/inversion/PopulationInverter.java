package inversion;

import converter.Converter;
import model.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopulationInverter {

    private InversionOperator inversionOperator;
    private Converter converter;
    private Random random;
    private double inversionProbability;

    public PopulationInverter(InversionOperator inversionOperator, Converter converter, Random random, double inversionProbability) {
        this.inversionOperator = inversionOperator;
        this.converter = converter;
        this.random = random;
        this.inversionProbability = inversionProbability;
    }

    public List<Individual> invertPopulation(List<Individual> population) {
        ArrayList<Individual> newPopulation = new ArrayList<>();
        for (Individual individual : population) {
            if (random.nextDouble() < inversionProbability) {
                byte[] ioneBinary = converter.toBinary(individual.getX1());
                byte[] newTwoIndividuals = inversionOperator.invert(ioneBinary);
                double newX1 = converter.toDecimal(newTwoIndividuals);
                byte[] ioneBinaryX2 = converter.toBinary(individual.getX2());
                byte[] newTwoIndividualsX2 = inversionOperator.invert(ioneBinaryX2);
                double newX2 = converter.toDecimal(newTwoIndividualsX2);
                newPopulation.add(new Individual(newX1, newX2));
            } else {
                newPopulation.add(individual);
            }
        }
        return newPopulation;
    }
}
