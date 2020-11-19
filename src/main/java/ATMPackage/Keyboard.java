package ATMPackage;

import java.util.Scanner;
import CardPackage.Card;

// наброски пока не знаю как можно их связать с остальными классами классы для проверки ATM CARD MAIN

public class Keyboard {
    static Card card;

    public void getPin(){

        Scanner sc1 = new Scanner(System.in);
        System.out.println("Введите пин-код: ");

        String check=sc1.nextLine();
        System.out.println("Пин код принят");

    }
    public void getSum () {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите сумму: ");

        int sum=sc.nextInt();
        System.out.println("Выдача средств: "+sum + card.getCurrency());
    }
}
