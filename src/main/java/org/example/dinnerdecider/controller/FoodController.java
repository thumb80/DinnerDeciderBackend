package org.example.dinnerdecider.controller;

import org.bson.types.ObjectId;
import org.example.dinnerdecider.model.Food;
import org.example.dinnerdecider.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodController {

    @Autowired
    private MongoService service;

    @GetMapping("/getvoted")
    public ResponseEntity<?> getVoted() {
        Food food = service.votedFood();
        if (food != null) {
            return ResponseEntity.ok(food);
        } else {
            return ResponseEntity.ok(new Food(ObjectId.get(), "All voted foods are equals... Please revote !!!", 0));
        }
    }

    @PostMapping("/votefood")
    public void voteFood(@RequestBody Food voteFood) {
        service.voteFood(voteFood);
    }

    @GetMapping("/getall")
    public List<Food> getAll() {
        return service.getAll();
    }

    @PostMapping("/insertfood")
    public Food insertFood(@RequestBody Food food) {
        return service.insertFood(food);
    }

    @GetMapping("/reset")
    public void resetVotedFood() {
        service.resetFood();
    }

}
