import I_models.Item;
import I_models.Cart;
import I_models.Store;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class I_Main {

//    //Welcome to the Online Java Store!
//    //The java store is divided into aisles (rows).
//    //Each aisle shelves a category of items.
//    //Each item is described by its name and price.
//    //The shopping cart holds the user's items.
//    //The shopping cart lets users add, remove, and check out.

    static Store store = new Store();
    static Cart cart = new Cart();

    public static void main(String[] args){
//        System.out.println(new Item("Pulses",0.59) +" "+ new Item("cereal",0.99));

//        Cart cart = new Cart();
//        cart.add(new Item("Celery",0.99));
//        cart.add(new Item("Celery",0.99));
//        cart.add(new Item("cereal",0.29));
//        cart.add(new Item("Pulses",1.99));
//        System.out.println(cart);

//        Item[][] inventory = new Item[][]{
//                { new Item("Pepsi",1.99), new Item("Cola",0.79), new Item("Blanket",0.99)},
//                { new Item("Honey",1.49), new Item("Milk",0.59), new Item("Eggs",2.99)},
//                { new Item("Sunglasses",1.09), new Item("Perfume",1.99), new Item("Mirror",1.99)},
//                { new Item("Camera",2.99), new Item("LEDs",1.49), new Item("Phone",6.50)},
//                { new Item("Pen",0.99), new Item("Workbook",2.50), new Item("Candles",0.59)},
//                { new Item("Cheese",0.49), new Item("Beans",0.99), new Item("Boots",0.99)},
//                { new Item("Sweater",2.49), new Item("Tie",1.99), new Item("Shirt",3.59)}
//        };
//
//        Store store = new Store();
//        for (int i = 0; i < inventory.length; i++) {
//            for (int j = 0; j < inventory[i].length; j++) {
//                store.setItems(i,j,inventory[i][j]);
//            }
//        }

        //"C:\Users\kamir faruk\IdeaProjects\Bookcamp\src\Iproducts.txt"
        try {
            loadItems("C:\\Users\\kamir faruk\\IdeaProjects\\Bookcamp\\src\\Iproducts.txt");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            manageItems();
        }



    }


//        Function Name: loadltem()
//        @param fileName (String)
//        @throws FileNotFoundException
//
//        Inside the function:
//        1. loads items from <fileName>.txt.
//            while loop runs through every line in <fileName>.txt.
//            scan.nextLine() picks up the entire line.
//                splits each String using the ";" separator.
//                splits both fields in each String using the "=" separator.
//            Parse each price into a Double.
//        2. adds all items to the store object's items field.

    public static void loadItems(String fileName) throws FileNotFoundException {
        FileInputStream fil = new FileInputStream(fileName);
        Scanner scanFile = new Scanner(fil);

        //while(scanFile.hasNextLine()){
        for (int i = 0; scanFile.hasNextLine(); i++) {
            String line = scanFile.nextLine();
            String[] items = line.split(";");
            for (int j = 0; j < items.length; j++) {
                String[] fields = items[j].split("=");
                store.setItems(i,j, new Item(fields[0], Double.parseDouble(fields[1])));
            }
        }
        scanFile.close();
    }

//    Function Nate: manageItems()
//    Inside the function:
//            1. Starts a new instance of Scanner;
//            2. Creates an infinite loop:
//            The user can choose to a) add b) remove c) checkout.
//            case a: asks for the aisle and item number. Then, adds item to the cart.
//            case b: asks for the name. Then, removes item from cart.
//            case c: prints the receipt and closes Scanner.
//            Prints the updated shopping cart.


    public static void manageItems(){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("\n\t*************************\t CART \t***********************\t");
            System.out.println(store +"\n");
            System.out.println("\nOptions: \n\ta) add \n\tb) remove from cart \n\tc) checkout");
            String response = scan.nextLine();
            switch(response){
                case "a":
                    System.out.println("\nChose an aisle number between (1-7) : ");

                    int row = scan.hasNextInt() ? scan.nextInt()-1 : 404;
                    scan.nextLine();
                    System.out.println("\nChose an item number between (1-3) : ");

                    int column = scan.hasNextInt() ? scan.nextInt()-1 : 404;
                    scan.nextLine();

                    if(row ==404 || column ==404){
                        continue;
                    } else if (row < 0 || row > 6 || column < 0 || column > 2) {
                        continue;
                    }

                    Item item = store.getItem(row,column);
                    if(!(cart.add(item))){
                        System.out.println(item.getName()+"Item is already in cart.");
                    } else {
                        System.out.println(item.getName()+"was added to cart.");
                    }
                    break;
                case "b":
                    if(cart.isEmpty()){
                        continue;
                    }
                    System.out.println("Enter the item you would like to remove: ");
                    String name = scan.nextLine();
                    cart.remove(name);
                    break;
                case "c":
                    if(cart.isEmpty()){
                        continue;
                    }
                    System.out.println(cart.checkout());
                    scan.close();
                    //break;
                    return;
            }
            System.out.println("\n\n SHOPPING CART \n\n"+cart);

        }
    }


}
