package processor;

public class Main {


    public static void main(String[] args) {


        Menu menu = new Menu();
        while(true) {
            menu.optionSelection(menu.printIniMenu());
        }

    }
}
