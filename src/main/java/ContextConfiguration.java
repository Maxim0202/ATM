
import ATMPackage.ATM;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfiguration {

    @Bean
    public ATM atm() {
        return new ATM(1, 1);
    }
}
