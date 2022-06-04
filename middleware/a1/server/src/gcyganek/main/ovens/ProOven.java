package gcyganek.main.ovens;

import Devices.*;
import com.zeroc.Ice.Current;

import java.util.Arrays;

public class ProOven extends BasicOven implements IProOven {

    private final Recipe[] recipes = Recipe.values();

    public ProOven(String name) {
        super(name);

        this.type = this.getClass().getSimpleName();

        this.minutesRange.min = 0;
        this.minutesRange.max = 500;

        this.temperatureRange.min = 0;
        this.temperatureRange.max = 500;

        this.modes = OvenMode.values();
    }

    @Override
    public String getDeviceInfo(Current current) {
        return super.getDeviceInfo(current) + "\nAvailable recipes: " + Arrays.toString(recipes);
    }

    @Override
    public String displayRecipe(Recipe recipe, Current current) throws UnknownRecipeException {
        if (Arrays.stream(Recipe.values()).noneMatch(r -> r == recipe)) {
            String reason = "Recipe for " + recipe + " is not available";
            throw new UnknownRecipeException(reason);
        }

        return switch(recipe) {
            case LEMONCAKE -> """
                    225g unsalted butter, softened
                    225g caster sugar
                    4 eggs
                    225g self-raising flour
                    1 lemon, zested
                    """;
            case CARROTCAKE -> """
                    175g light muscovado sugar
                    175ml sunflower oil
                    3 large eggs, lightly beaten
                    140g grated carrot (about 3 medium)
                    100g raisins
                    1 large orange, zested
                    175g self-raising flour
                    1 tsp bicarbonate of soda
                    1 tsp ground cinnamon
                    ½ tsp grated nutmeg (freshly grated will give you the best flavour)
                    """;
            case CHOCOLATECAKE -> """
                    200g dark chocolate (about 60% cocoa solids), chopped
                    200g butter, cubed
                    1 tbsp instant coffee granules
                    85g self-raising flour
                    85g plain flour
                    ¼ tsp bicarbonate of soda
                    200g light muscovado sugar
                    200g golden caster sugar
                    25g cocoa powder
                    3 medium eggs
                    75ml buttermilk
                    50g grated chocolate or 100g curls, to decorate
                    """;
        };
    }
}
