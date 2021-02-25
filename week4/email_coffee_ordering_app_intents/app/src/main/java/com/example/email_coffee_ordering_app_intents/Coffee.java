package com.example.email_coffee_ordering_app_intents;

public class Coffee {

    public Integer quantity;
    public Boolean hasWhippedCream;
    public Boolean hasChocolate;

    private final int MAX = 25;
    private final int MIN = 0;

    // CONSTRUCTOR
    public Coffee() {
        quantity = 0;
        hasWhippedCream = false;
        hasChocolate = false;
    }

    // INCREMENT THE COUNT
    public void incrementQuantity (){
        if (quantity < MAX){
            quantity++;
        }
    }

    // DECREMENT THE COUNT
    public void decrementQuantity (){
        if (quantity > MIN){
            quantity--;
        }
    }

    // GETTERS

    // RETURNING HOW MANY
    public String getQuantityString (){
        return quantity.toString();
    }

    // SETTINGS

    // SETTING THAT THERE IS CHOCOLATE
    public void setChocolate(boolean isChecked){
        hasChocolate = isChecked;
    }

    // SETTING THAT THERE IS WHIPPED CREAM
    public void setWhippedCream(boolean isChecked){
        hasWhippedCream = isChecked;
    }

    // RETURNING THE ORDER
    public String getOrderSummary (){
        Double price = quantity*3.50;
        if(hasChocolate) {
            price += quantity * 1.00;
        } if (hasWhippedCream){
            price += quantity*.50;
        }

        String order = "\nORDER SUMMARY\n\n";
        order+= "Add whipped cream? " + (hasWhippedCream?"yes":"no") + "\n";
        order+= "Add chocolate? " + (hasChocolate? "yes":"no") + "\n";
        order+= "Quantity: " + quantity.toString() + "\n\n";
        order+= "Price: $" + String.format("%.2f",price) + "\nTHANK YOU!";
        return order;
    }
}

