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
                byte[] ioneBinary = converter.toBinary(individual);
                byte[] inverted = inversionOperator.invert(ioneBinary);
                newPopulation.add(converter.toIndividual(inverted));
            } else {
                newPopulation.add(individual);
            }
        }
        return newPopulation;
    }
}
