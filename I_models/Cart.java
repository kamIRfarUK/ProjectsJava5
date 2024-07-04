package I_models;

import java.util.ArrayList;

public class Cart {

    ArrayList<Item> items;

    public Cart(){
        this.items = new ArrayList<Item>();
    }

    public Item getItem(int index){
        return new Item(this.items.get(index));
    }

    public void setItem(int index, Item item) {
        this.items.set(index, new Item(item));
    }

    //Function Name: add()
    //@param item
    //@return boolean
    //Inside the function:
    //I. Adds an item to the cart if it wasn't already added.

    public boolean add(Item item){
        if(this.items.contains(item)){
            return false;
        }
        this.items.add( new Item(item));
        return true;
    }

    public boolean isEmpty(){
        return this.items.isEmpty();
    }

    //Function Name: remove()
    //@param name
    //Inside the function:
    //1. Removes the item that matches the name passed in.

    public void remove(String name){
        if (items.isEmpty()){
            throw new IllegalStateException("Cannot remove items from empty Cart");
        }
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getName().equals(name)){
                this.items.remove(i);
            }
        }
    }

    //Function Name: checkout()
    //@return (String)
    //Inside the function:
    //1.Calculates the subtotal ( price before tax) .==>measures[0]
    //2.Calculates the tax ( assume tax is 13%) .    ==>measures[1]
    //3.Calculates total: subtotal + tax .           ==>measures[2]
    //4.Returns a String that resembles a receipt. See below.

    public String checkout(){
        if (items.isEmpty()){
            throw new IllegalStateException("Cannot Checkout an empty Cart");
        }
        double[] measures = new double[3];
        for (int i = 0; i < this.items.size(); i++) {
            measures[0] += this.items.get(i).getPrice();
        }
        measures[1]=measures[0]*0.13;
        measures[2]=measures[0]+measures[1];

        return "\tRECEIPT\n\n" +
                "\tSubtotal: $" + measures[0] + "\n" +
                "\tTax: $" + measures[1] + "\n" +
                "\tTotal: $" + measures[2]+ "\n" ;
    }

    public String toString() {
        String temp= "";
        for (int i = 0; i < this.items.size(); i++) {
            temp += this.items.get(i).toString();
            temp += "\n";
        }
        return temp;
    }
}
