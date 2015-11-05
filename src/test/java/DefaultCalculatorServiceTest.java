import com.google.inject.Injector;
import com.netflix.governator.lifecycle.LifecycleManager;
import com.netflix.governator.guice.LifecycleInjector;
import org.junit.Assert;
import org.junit.Test;

public class DefaultCalculatorServiceTest {

    @Test
    public void canCreateDefaultCalculatorService() {
        // arrange / act
        DefaultCalculatorService calculatorService = new DefaultCalculatorService();

        // assert
        Assert.assertNotNull(calculatorService);
    }

    @Test
    public void canAddTwoValues() {
        // arrange
        DefaultCalculatorService calculatorService = new DefaultCalculatorService();

        // act
        double sum = calculatorService.add(1, 1);

        // assert
        Assert.assertEquals(2.0, sum, .001);
    }

    @Test
    public void canSubtractTwoValues() {
        // arrange
        DefaultCalculatorService calculatorService = new DefaultCalculatorService();

        // act
        double sum = calculatorService.subtract(1, 1);

        // assert
        Assert.assertEquals(0.0, sum, .001);
    }

    @Test
    public void canInjectDependencies() {
        // arrange
        Injector injector = LifecycleInjector
                .builder()
                .withModuleClass(CalculatorModule.class)
                .usingBasePackages("")
                .build()
                .createInjector();

        // act
        CalculatorService service = injector.getInstance(CalculatorService.class);

        // assert
        Assert.assertNotNull(service);
    }

    @Test
    public void canInjectDependenciesUsingLifecycleManager() throws Exception {
        // arrange
        Injector injector = LifecycleInjector
                .builder()
                .withModuleClass(CalculatorModule.class)
                .usingBasePackages("")
                .build()
                .createInjector();

        LifecycleManager manager = injector.getInstance(LifecycleManager.class);

        manager.start();

        // act
        CalculatorService service = injector.getInstance(CalculatorService.class);

        // assert
        Assert.assertNotNull(service);

        manager.close();
    }

}
