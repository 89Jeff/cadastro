package com.Cadastro.cadastro.services;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Cadastro.cadastro.Dtos.CarDto;
import com.Cadastro.cadastro.models.Car;
import com.Cadastro.cadastro.repositorys.CarRepository;

@Service
public class CarService {
    
    @Autowired
    private CarRepository carRepository;

    public Car salvarCarro(CarDto carDto) {      
        try {
            if (carDto == null || carDto.getModelo() == null || carDto.getFabricante() == null || carDto.getAnofabricacao() == null) {
                throw new IllegalArgumentException("Dados incompletos ou inválidos.");
            }

            Car car = new Car();
            car.setModelo(carDto.getModelo());
            car.setFabricante(carDto.getFabricante());
            car.setAnofabricacao(carDto.getAnofabricacao());
         
            return carRepository.save(car);
        } catch (Exception e) {
            e.printStackTrace(); // Imprime a exceção no console
            throw e; // Relança a exceção para o controlador lidar com ela
        }
    }    

    public List<Car> listarCar(){
        return carRepository.findAll();
    }

    public Optional<Car> buscarCarPorId(Long id) {
        return carRepository.findById(id);
    }

    public void removerCar(Long id) {
        carRepository.deleteById(id);
    }

    public Car atualizarCar(Long id , CarDto carDTO) {
        Optional<Car> carExistenteObj = carRepository.findById(id);

        if(carExistenteObj.isPresent()) {
            Car carExistente = carExistenteObj.get();

            carExistente.setModelo(carDTO.getModelo());
            carExistente.setFabricante(carDTO.getFabricante());
            carExistente.setAnofabricacao(carDTO.getAnofabricacao());

            return carRepository.save(carExistente);
        } else {
            return null;
        }
    }
}
