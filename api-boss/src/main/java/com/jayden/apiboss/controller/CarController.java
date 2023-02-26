package com.jayden.apiboss.controller;


import com.jayden.apiboss.service.CarService;
import com.jayden.internalcommon.dto.Car;
import com.jayden.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jayden
 * @since 2023-02-26
 */
@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){
        System.out.println(car.toString());
        return carService.addCar(car);
    }

}
