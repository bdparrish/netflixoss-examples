import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultCalculatorService implements CalculatorService {

    public double add(double first, double second) {
        return first + second;
    }

    public double subtract(double first, double second) {
        return first - second;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Post-construct called!");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Pre-destroy called!");
    }
}
