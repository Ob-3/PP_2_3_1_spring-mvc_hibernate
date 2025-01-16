package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    private static final List<Car> cars = new ArrayList<>();

    static {
        cars.add(new Car(1, "Model1", 1));
        cars.add(new Car(2, "Model2", 2));
        cars.add(new Car(3, "Model3", 3));
        cars.add(new Car(4, "Model4", 4));
        cars.add(new Car(5, "Model5", 5));
    }

    @Override
    public List<Car> getCarsList() {
        return cars;
    }
}