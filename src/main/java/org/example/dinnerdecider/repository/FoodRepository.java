package org.example.dinnerdecider.repository;

import org.example.dinnerdecider.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;

@Repository
public class FoodRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Food insertFood(Food food) {
        return mongoTemplate.insert(food);
    }
    public List<Food> getAll() {
        return mongoTemplate.findAll(Food.class);
    }

    public void foodUpdate(Food food) {
        Query query = buildQueryFromObject(food);
        Food getFood = mongoTemplate.findOne(query, Food.class);
        int count = getFood.getCount();
        Update update = new Update().set("count", count + 1);
        mongoTemplate.upsert(query, update, Food.class);
    }

    public void foodReset(Food food) {
        Query query = buildQueryFromObject(food);
        Update update = new Update().set("count", 0);
        mongoTemplate.upsert(query, update, Food.class);
    }

    public Query buildQueryFromObject(Object obj) {
        Query query = new Query();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (value != null) {
                    if (field.getName().equals("name")) {
                        query.addCriteria(Criteria.where(field.getName()).is(value));
                        System.out.println("field : " +field.getName()+ " value : " +value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return query;
    }

}

