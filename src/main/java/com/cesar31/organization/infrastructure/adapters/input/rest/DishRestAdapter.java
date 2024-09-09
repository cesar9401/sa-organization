package com.cesar31.organization.infrastructure.adapters.input.rest;

import com.cesar31.organization.application.dto.CreateDishReqDto;
import com.cesar31.organization.application.dto.UpdateDishReqDto;
import com.cesar31.organization.application.dto.UpdateDishStockReq;
import com.cesar31.organization.application.ports.input.DishUseCase;
import com.cesar31.organization.domain.Dish;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dishes")
public class DishRestAdapter {

    private final DishUseCase dishUseCase;

    public DishRestAdapter(DishUseCase dishUseCase) {
        this.dishUseCase = dishUseCase;
    }

    @GetMapping
    @Operation(description = "Find all the dishes of the organization the user belongs to.")
    public ResponseEntity<List<Dish>> findAll(
            @RequestParam(name = "dishIds", required = false) String dishIds
    ) throws Exception {
        var dishes = dishUseCase.findAll(dishIds);
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("{dishId}")
    @Operation(description = "Find a specific dish by its id, and of the organization the user belongs to.")
    public ResponseEntity<Dish> findById(@PathVariable("dishId") UUID dishId) throws Exception {
        return dishUseCase.findById(dishId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(description = "Create a new dish, of the organization the user belongs to.")
    public ResponseEntity<Dish> create(@RequestBody CreateDishReqDto reqDto) throws Exception {
        var newDish = dishUseCase.save(reqDto);
        return new ResponseEntity<>(newDish, HttpStatus.CREATED);
    }

    @PutMapping("{dishId}")
    @Operation(description = "Update any dish by its id, of the organization the user belongs to.")
    public ResponseEntity<Dish> update(@PathVariable("dishId") UUID dishId, @RequestBody UpdateDishReqDto reqDto) throws Exception {
        var updatedDish = dishUseCase.update(dishId, reqDto);
        return ResponseEntity.ok(updatedDish);
    }

    @PutMapping("update-stock")
    @Operation(description = "Update the sock of a group of dishes, of the organization the user belongs to.")
    public ResponseEntity<List<UUID>> updateStock(@RequestBody UpdateDishStockReq reqDto) throws Exception {
        var dishesIds = dishUseCase.updateDishStock(reqDto);
        return ResponseEntity.ok(dishesIds);
    }
}
