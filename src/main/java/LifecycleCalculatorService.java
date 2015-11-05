import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class LifecycleCalculatorService implements CalculatorService {

    private int multiplier = 1;

    public double add(double first, double second) {
        first = multiply(first);
        second = multiply(second);
        return first + second;
    }

    public double subtract(double first, double second) {
        first = multiply(first);
        second = multiply(second);
        return first - second;
    }

    private double multiply(double value) {
        return value * multiplier;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Pre-construct called");
        this.multiplier = 3;
        System.out.println("Multiplier = " + this.multiplier);
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Post-destroy called");
        this.multiplier = 1;
        System.out.println("Multiplier = " + this.multiplier);
    }
}
