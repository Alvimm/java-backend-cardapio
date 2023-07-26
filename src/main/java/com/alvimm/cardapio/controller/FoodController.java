package com.alvimm.cardapio.controller;

import com.alvimm.cardapio.entities.Food;
import com.alvimm.cardapio.entities.FoodRequestDTO;
import com.alvimm.cardapio.entities.FoodResponseDTO;
import com.alvimm.cardapio.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {
    @Autowired
    private FoodRepository foodRepository;

    @CrossOrigin(origins="*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data){
        Food foodData = new Food(data);
        foodRepository.save(foodData);
        return;
    }
    @CrossOrigin(origins="*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAll(){
        List<FoodResponseDTO> foodList = foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();
        return foodList;
    }

    @CrossOrigin(origins="*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public void updateFood(@PathVariable Long id, @RequestBody FoodResponseDTO data){
        Food foodData = foodRepository.findById(id).orElseThrow(() -> new RuntimeException("Food not found"));
        foodData.setTitle(data.title());
        foodData.setImage(data.image());
        foodData.setPrice(data.price());
        foodRepository.save(foodData);
    }


}
