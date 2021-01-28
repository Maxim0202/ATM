package Uniform.Plastic;

import Uniform.Exception.BusinessException;
import Uniform.Exception.ErrorCodes;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@NonNull
public class Card {

    private final LocalDate expiryDate;
    private final String number;
    private final String pin;

    public Card(String number, LocalDate expiryDate, String pin) throws BusinessException {
        if (number.matches("^[0-9]{16}$"))
            this.number = number;
        else
            throw new BusinessException(ErrorCodes.ERR_CARD_FORMAT, number);

        if (pin.matches("^[0-9]{4}$"))
            this.pin = pin;
        else
            throw new BusinessException(ErrorCodes.ERR_PIN_FORMAT);

        this.expiryDate = expiryDate;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(number, card.number) &&
                Objects.equals(expiryDate, card.expiryDate) &&
                Objects.equals(pin, card.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, expiryDate, pin);
    }
}