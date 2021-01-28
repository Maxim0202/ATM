package ATM;

import ATMPackage.Banknote;
import ATMPackage.Cassets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

class Cassetstest {

    private static Validator validator;
    Cassets cassets = new Cassets(1, Banknote.USD_20, 400);

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testPopBanknotes() {
        cassets.setCache(300);
        cassets.popBanknotes();
        Assertions.assertEquals(cassets.getCurrentAmount(), 400);
    }

    @Test
    void testMaxCurrentAmount() {
        cassets.setCurrentAmount(2001);
        Set<ConstraintViolation<Cassets>> constraintViolations = validator.validate(cassets);
        Assertions.assertEquals(1, constraintViolations.size());
        Assertions.assertEquals("должно быть не больше 2000", constraintViolations.iterator().next().getMessage());
    }

    @Test
    void testMinCurrentAmount() {
        cassets.setCurrentAmount(-1);
        Set<ConstraintViolation<Cassets>> constraintViolations = validator.validate(cassets);
        Assertions.assertEquals(1, constraintViolations.size());
        Assertions.assertEquals("должно быть не меньше 0", constraintViolations.iterator().next().getMessage());
    }

    @Test
    void testMaxCache() {
        cassets.setCache(2001);
        Set<ConstraintViolation<Cassets>> constraintViolations = validator.validate(cassets);
        Assertions.assertEquals(1, constraintViolations.size());
        Assertions.assertEquals("должно быть не больше 2000", constraintViolations.iterator().next().getMessage());
    }

    @Test
    void testMinCache() {
        cassets.setCache(-1);
        Set<ConstraintViolation<Cassets>> constraintViolations = validator.validate(cassets);
        Assertions.assertEquals(1, constraintViolations.size());
        Assertions.assertEquals("должно быть не меньше 0", constraintViolations.iterator().next().getMessage());
    }

}