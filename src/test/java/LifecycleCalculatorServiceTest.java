import com.google.inject.Injector;
import com.netflix.governator.guice.LifecycleInjector;
import com.netflix.governator.lifecycle.LifecycleManager;
import org.junit.Assert;
import org.junit.Test;

public class LifecycleCalculatorServiceTest {

    @Test
    public void canCreateLifecycleCalculatorService() {
        // arrange / act
        LifecycleCalculatorService calculatorService = new LifecycleCalculatorService();

        // assert
        Assert.assertNotNull(calculatorService);
    }

    @Test
    public void canAddTwoValues() {
        // arrange
        LifecycleCalculatorService calculatorService = new LifecycleCalculatorService();

        // act
        double sum = calculatorService.add(1, 1);

        // assert
        Assert.assertEquals(2.0, sum, .001);
    }

    @Test
    public void canSubtractTwoValues() {
        // arrange
        LifecycleCalculatorService calculatorService = new LifecycleCalculatorService();

        // act
        double sum = calculatorService.subtract(1, 1);

        // assert
        Assert.assertEquals(0.0, sum, .001);
    }

    @Test
    public void canAddTwoValuesPostConstruct() {
        // arrange
        Injector injector = LifecycleInjector
                .builder()
                .withModuleClass(CalculatorModule.class)
                .usingBasePackages("")
                .build()
                .createInjector();

        CalculatorService calculatorService = injector.getInstance(LifecycleCalculatorService.class);

        // act
        double sum = calculatorService.add(1.0, 1.0);

        // assert
        Assert.assertEquals(6.0, sum, .001);
    }

    @Test
    public void canSubtractTwoValuesPostConstruct() {
        // arrange
        Injector injector = LifecycleInjector
                .builder()
                .withModuleClass(CalculatorModule.class)
                .usingBasePackages("")
                .build()
                .createInjector();

        CalculatorService calculatorService = injector.getInstance(LifecycleCalculatorService.class);

        // act
        double sum = calculatorService.subtract(2.0, 1.0);

        // assert
        Assert.assertEquals(3.0, sum, .001);
    }

    @Test
    public void canAddTwoValuesPreDestroy() throws Exception {
        // arrange
        Injector injector = LifecycleInjector
                .builder()
                .withModuleClass(CalculatorModule.class)
                .usingBasePackages("")
                .build()
                .createInjector();

        LifecycleManager manager = injector.getInstance(LifecycleManager.class);

        manager.start();

        CalculatorService calculatorService = injector.getInstance(LifecycleCalculatorService.class);

        // act
        double sum = calculatorService.add(1.0, 1.0);

        // assert
        Assert.assertEquals(6.0, sum, .001);

        manager.close();
    }

    @Test
    public void canSubtractTwoValuesPreDestroy() throws Exception {
        // arrange
        Injector injector = LifecycleInjector
                .builder()
                .withModuleClass(CalculatorModule.class)
                .usingBasePackages("")
                .build()
                .createInjector();

        LifecycleManager manager = injector.getInstance(LifecycleManager.class);

        manager.start();

        CalculatorService calculatorService = injector.getInstance(LifecycleCalculatorService.class);

        // act
        double sum = calculatorService.subtract(2.0, 1.0);

        // assert
        Assert.assertEquals(3.0, sum, .001);

        manager.close();
    }

}
