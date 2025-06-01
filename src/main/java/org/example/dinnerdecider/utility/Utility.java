package org.example.dinnerdecider.utility;

import org.example.dinnerdecider.model.Food;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utility {

    public static Food findVotedFood(List<Food> foodList) {
        if (foodList == null || foodList.isEmpty()) {
            return null;
        }

        // Trova il valore massimo di count
        int maxCount = foodList.stream()
                .mapToInt(Food::getCount)
                .max()
                .orElse(Integer.MIN_VALUE);

        // Filtra tutti i cibi con quel valore massimo
        List<Food> maxFoods = foodList.stream()
                .filter(f -> f.getCount() == maxCount)
                .collect(Collectors.toList());

        // Se ce n'è solo uno, lo restituisco, altrimenti ritorno null
        return maxFoods.size() == 1 ? maxFoods.get(0) : null;
    }

}

