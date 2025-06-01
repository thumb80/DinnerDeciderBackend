package org.example.dinnerdecider.service;

import org.example.dinnerdecider.model.Food;
import org.example.dinnerdecider.repository.FoodRepository;
import org.example.dinnerdecider.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MongoService {

    @Autowired
    private FoodRepository foodRepository;

    public Food insertFood(Food food) {
        return foodRepository.insertFood(food);
    }

    public Food votedFood() {
        List<Food> foodList = foodRepository.getAll();
        return Utility.findVotedFood(foodList);
    }

    public void voteFood(Food votedFood) {
        foodRepository.foodUpdate(votedFood);
    }

    public void resetFood() {
        List<Food> foodList = foodRepository.getAll();
        for (Food food : foodList) {
            foodRepository.foodReset(food);
        }
    }

    public List<Food> getAll() {
        return foodRepository.getAll();
    }

}

