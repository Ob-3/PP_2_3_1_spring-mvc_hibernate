package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.dao.CarDao;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> carsList(int count) {
        List<Car> cars = carDao.getCarsList();
        if (count > 0 && count < 5) {
            return cars.stream().limit(count).toList();
        } else {
            return cars;
        }
    }
}