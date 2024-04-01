package com.Cadastro.cadastro.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.Cadastro.cadastro.Dtos.CarDto;
import com.Cadastro.cadastro.models.Car;
import com.Cadastro.cadastro.services.CarService;

@CrossOrigin("*")
@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/editarcar")
    public ModelAndView editarcar(){
        ModelAndView login = new ModelAndView("car/editarcar");
        return login;
    }

    @GetMapping("/cadastracar")
    public ModelAndView login(){
        ModelAndView login = new ModelAndView("car/cadastracar");
        return login;
    }

    @GetMapping("/listcar")
    public ModelAndView cadastro(){
        ModelAndView cadastro = new ModelAndView("car/listcar");
        return cadastro;
    }

    @GetMapping("/car")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> listarCar() {
        return carService.listarCar();
    }

    @PostMapping("/car")
    public ResponseEntity<?> salvarCarro(@RequestBody CarDto carDto) {
        try {
            Car savedCar = carService.salvarCarro(carDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar o carro.");
        }
    }
   
    @DeleteMapping("/car/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerUser(@PathVariable("id")  Long id) {
        carService.removerCar(id);
    }

    @GetMapping("/car/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Car> buscarCarPorId(@PathVariable("id") Long id) {
        Optional<Car> carro = carService.buscarCarPorId(id);
        return carro.map(c -> ResponseEntity.ok().body(c))
                    .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/car/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> atualizarCar(@PathVariable("id") Long id, @RequestBody CarDto carDTO) {
        try {
            Car updatedCar = carService.atualizarCar(id, carDTO);
            if (updatedCar != null) {
                return ResponseEntity.ok().body(updatedCar);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o carro.");
        }
    }
}

    


