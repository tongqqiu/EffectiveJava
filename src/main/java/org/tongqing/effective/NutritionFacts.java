package org.tongqing.effective;

/**
 * Created by tongqingqiu on 1/30/14.
 */
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;

    }


    public static class NutritionFactsBuilder {
        private int servingSize;
        private int servings;
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public NutritionFactsBuilder setServingSize(int servingSize) {
            this.servingSize = servingSize;
            return this;
        }

        public NutritionFactsBuilder setServings(int servings) {
            this.servings = servings;
            return this;
        }

        public NutritionFactsBuilder setCalories(int calories) {
            this.calories = calories;
            return this;
        }

        public NutritionFactsBuilder setFat(int fat) {
            this.fat = fat;
            return this;
        }

        public NutritionFactsBuilder setSodium(int sodium) {
            this.sodium = sodium;
            return this;
        }

        public NutritionFactsBuilder setCarbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        public NutritionFacts createNutritionFacts() {
            return new NutritionFacts(servingSize, servings, calories, fat, sodium, carbohydrate);
        }
    }

    private NutritionFacts(NutritionFactsBuilder builder) {
        servingSize  = builder.servingSize;
        servings     = builder.servings;
        calories     = builder.calories;
        fat          = builder.fat;
        sodium       = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}