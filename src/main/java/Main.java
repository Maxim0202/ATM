import ATMPackage.ATM;
import ATMPackage.Exception.ATMException;
import Uniform.Currency;
import Uniform.Exception.BusinessException;
import Uniform.Plastic.DebitCard;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws ATMException, BusinessException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ContextConfiguration.class);
        ctx.refresh();
        ATM atm = ctx.getBean(ATM.class);
        DebitCard card = new DebitCard("4200685821457075", LocalDate.of(2021, 5, 30), "4045");
        atm.getCash(card, Currency.RUR, 5000);
    }
}