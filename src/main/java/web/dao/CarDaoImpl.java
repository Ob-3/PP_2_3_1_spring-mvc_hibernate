package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    private static final List<Car> cars = new ArrayList<>();

    static {
        cars.add(new Car(1, "ЗИЛ-130", 1));
        cars.add(new Car(2, "ГАЗ 3110 Волга", 2));
        cars.add(new Car(3, "Ford Bronco", 3));
        cars.add(new Car(4, "ВАЗ 2112", 4));
        cars.add(new Car(5, "Lincoln Continental", 5));
    }

    @Override
    public List<Car> getCarsList() {
        return cars;
    }
}