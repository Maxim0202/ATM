package ru.FinalProject;

import CardPackage.Card;
import ATMPackage.ATM;


public class Main {
    public static void main(String[] args) {
            Card card1 = new Card(1234, "01/21");
            ATM a = new ATM();
            System.out.println(a.getMoney(card1));
        }

    }
