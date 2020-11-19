package ATMPackage;

import CardPackage.Card;

//хотел ещё с датой что то придумать больше меньше, но пока не получается

public class ATM {
    public String getMoney(Card card) {
        if ((card.getPinCode() == 1234) && (card.getValidity().equals("01/21"))) {
            return "Выдача средств";
        }
        else if ((card.getPinCode() != 1234)) {
            return "Неверный пин-код";
        }
        else {
            return "Error";
        }
    }
}
