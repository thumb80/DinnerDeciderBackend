package org.example.dinnerdecider.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FoodList")
public class Food {

    @Id
    private ObjectId id;

    private String name;

    private Integer count;


    public Food(ObjectId id, String name, Integer count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
