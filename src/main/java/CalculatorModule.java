import com.google.inject.AbstractModule;

public class CalculatorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CalculatorService.class).to(LifecycleCalculatorService.class);
    }
}
