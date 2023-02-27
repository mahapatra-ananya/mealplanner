package ui;

import model.*;

import java.util.Scanner;

// Structure of UI class and runWeeklyPlanner method referenced from TellerApp
public class WeeklyPlanner {
    private Day sunday;
    private Day monday;
    private Day tuesday;
    private Day wednesday;
    private Day thursday;
    private Day friday;
    private Day saturday;
    private Pantry pantry;
    private ShoppingList shoppingList;

    private MealList mealsForTheWeek;

    private Scanner input;

    // EFFECTS: creates a new WeeklyPlanner with all the days of the week, a pantry, a shopping list, an empty list of
    //          meals for the week, and a scanner
    //          then runs the primary UI method
    public WeeklyPlanner() {
        sunday = new Day("Sunday");
        monday = new Day("Monday");
        tuesday = new Day("Tuesday");
        wednesday = new Day("Wednesday");
        thursday = new Day("Thursday");
        friday = new Day("Friday");
        saturday = new Day("Saturday");
        pantry = new Pantry();
        shoppingList = new ShoppingList();
        mealsForTheWeek = new MealList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        runWeeklyPlanner();
    }

    // EFFECTS: takes user input and keeps running the program
    //          if the user inputs "7", ends the program
    private void runWeeklyPlanner() {
        boolean loop = true;

        while (loop) {
            String whatToDo;
            displayMenu();
            whatToDo = input.nextLine();
            whatToDo = whatToDo.toLowerCase();

            if (whatToDo.equals("7")) {
                loop = false;
            } else {
                doThing(whatToDo);
            }
        }
        System.out.println("Have a healthy, nutritious day!");
    }

    // EFFECTS: displays a menu of options that the user can choose from
    private void displayMenu() {
        System.out.println("\nWhat would you like to do? Please note that your pantry refers to the ingredients "
                + "you already have at home");
        System.out.println("\t1: Add a meal to a day of the week");
        System.out.println("\t2: Add an ingredient to your pantry");
        System.out.println("\t3: Remove an ingredient from your pantry");
        System.out.println("\t4: View your pantry");
        System.out.println("\t5: View your shopping list");
        System.out.println("\t6: View your weekly meal planner");
        System.out.println("\t7: Quit application");
    }


    // EFFECTS: only runs if user inputs anything other than "7"
    //          if the user inputs a number from 1-6, executes the respective required function
    //          otherwise, requests the user to only enter valid inputs
    private void doThing(String whatToDo) {
        if (whatToDo.equals("1")) {
            doAddingMealToDay();
        } else if (whatToDo.equals("2")) {
            doAddingIngredientToPantry();
        } else if (whatToDo.equals("3")) {
            doRemovingIngredientFromPantry();
        } else if (whatToDo.equals("4")) {
            doViewingPantry();
        } else if (whatToDo.equals("5")) {
            doViewingShoppingList();
        } else if (whatToDo.equals("6")) {
            doViewingWeeklyPlanner();
        } else {
            System.out.println("Please only type a number between 1-7");
        }
    }


    // MODIFIES: Day, mealsForTheWeek, shoppingList
    // EFFECTS: if input is valid and not "q":
    //              begins the process of adding a meal to the day desired by the user,
    //              adds the meal the list of all meals for the week,
    //              and adds required ingredients to the shopping list
    //          if user input is "q", quits the method
    //          otherwise, asks the user to enter valid input
    private void doAddingMealToDay() {
        Boolean loop = true;
        while (loop) {
            System.out.println("Please type the first 3 letters of the day you would like to add to, or 'q' to quit.");
            String day = input.nextLine();
            day = day.toLowerCase();
            if (day.equals("sun") || day.equals("mon") || day.equals("tue") || day.equals("wed") || day.equals("thu")
                    || day.equals("fri") || day.equals("sat")) {
                Meal m = whichMealToAdd();
                addMealToSpecificDay(day, m);
                mealsForTheWeek.addMeal(m);
                for (Ingredient i : m.getIngredients()) {
                    addToShoppingList(i);
                }
                loop = false;
            } else if (day.equals("q")) {
                loop = false;
            }
        }
    }

    // MODIFIES: Day
    // EFFECTS: adds the given meal to the list of meals for the day with the given name
    private void addMealToSpecificDay(String day, Meal meal) {
        if (day.equals("sun")) {
            sunday.addMeal(meal);
        } else if (day.equals("sat")) {
            saturday.addMeal(meal);
        } else if (day.equals("mon")) {
            monday.addMeal(meal);
        } else if (day.equals("tue")) {
            tuesday.addMeal(meal);
        } else if (day.equals("wed")) {
            wednesday.addMeal(meal);
        } else if (day.equals("thu")) {
            thursday.addMeal(meal);
        } else if (day.equals("fri")) {
            friday.addMeal(meal);
        }
    }

    // MODIFIES: Meal
    // EFFECTS: creates and returns a meal according to the name, meal type and ingredient list inputted by user
    private Meal whichMealToAdd() {
        System.out.println("Please enter the name of the meal you would like to add.");
        String mealName = input.nextLine();
        mealName.toLowerCase();
        Meal meal = new Meal(mealName);
        System.out.println("Please enter the type of meal this is.");
        System.out.println("b: Breakfast");
        System.out.println("l: Lunch");
        System.out.println("d: Dinner");
        System.out.println("s: Snack");
        String mealType = input.nextLine();
        mealType = mealType.toLowerCase();
        doSettingType(meal, mealType);
        addIngredientsToMeal(meal);
        return meal;
    }

    // MODIFIES: Meal
    // EFFECTS: sets the type of the meal according to the input if valid
    //          otherwise, requests user to enter valid input
    private void doSettingType(Meal m, String mealType) {
        /*Boolean loop = true;
        String returnValue = "";
        while (loop) {
            if (s.equals("b")) {
                returnValue = "Breakfast";
                loop = false;
            } else if (s.equals("l")) {
                returnValue = "Lunch";
                loop = false;
            } else if (s.equals("d")) {
                returnValue = "Dinner";
                loop = false;
            } else if (s.equals("s")) {
                returnValue = "Snack";
                loop = false;
            } */
        while (!m.setValidType(mealType)) {
            System.out.println("Please type either b, l, d or s.");
            mealType = input.nextLine();
            mealType = mealType.toLowerCase();
        }
    }

    // MODIFIES: Meal
    // EFFECTS: adds one or more user-created ingredients to the given meal
    private void addIngredientsToMeal(Meal m) {
        Ingredient i = createIngredient();
        m.addIngredient(i);
        //addToShoppingList(i);

        Boolean loop = true;

        while (loop) {
            System.out.println("Do you want to add more ingredients? Type 'y' for yes or anything else for no.");
            String addMore = input.nextLine();
            addMore = addMore.toLowerCase();
            if (addMore.equals("y")) {
                Ingredient moreIngredient = createIngredient();
                m.addIngredient(moreIngredient);
                // addToShoppingList(moreIngredient);
            } else {
                loop = false;
            }
        }
    }

    // EFFECTS: creates an Ingredient object according to the user inputted name and quantity
    private Ingredient createIngredient() {
        System.out.println("Please enter the name of the ingredient you want to add or remove");
        String ingredientName = input.nextLine();
        System.out.println("Please enter the quantity of this ingredient you want to add or remove");
        double ingredientQuantity = input.nextDouble();
        input.nextLine();
        Ingredient ingredient = new Ingredient(ingredientName, ingredientQuantity);
        return ingredient;
    }

    // MODIFIES: Pantry
    // EFFECTS: adds user created ingredient to pantry
    private void doAddingIngredientToPantry() {
        addToPantry(createIngredient());

    }

    // MODIFIES: Pantry
    // EFFECTS: removes user desired ingredient to pantry
    private void doRemovingIngredientFromPantry() {
        removeFromPantry(createIngredient());
    }

    // EFFECTS: displays all the ingredients in the pantry
    private void doViewingPantry() {
        System.out.println(pantry.printableIngredientList("The ingredients you already have are: "));
    }

    // EFFECTS: displays all the ingredients in the shopping list
    private void doViewingShoppingList() {
        System.out.println(shoppingList.printableIngredientList("Your shopping list is for the week is: "));
    }

    // EFFECTS: displays all the meals planned for each day of the week and displays their ingredients if user desires
    private void doViewingWeeklyPlanner() {
        System.out.println("Sunday: " + sunday.printableMealList());
        System.out.println("Monday: " + monday.printableMealList());
        System.out.println("Tuesday: " + tuesday.printableMealList());
        System.out.println("Wednesday: " + wednesday.printableMealList());
        System.out.println("Thursday: " + thursday.printableMealList());
        System.out.println("Friday: " + friday.printableMealList());
        System.out.println("Saturday: " + saturday.printableMealList());

        viewIngredientsForMeal();

    }

    // EFFECTS: if user desires to view the ingredients for a meal, displays them
    //          otherwise, quits method
    private void viewIngredientsForMeal() {
        Boolean loop = true;
        while (loop) {
            System.out.println("If you would like to view the ingredients for a particular meal, type its name "
                    + "exactly as it appears in the planner, or type anything else to return to the main menu.");
            String choice = input.nextLine();
            choice.toLowerCase();
            for (Meal m : mealsForTheWeek.getMeals()) {
                if (m.getName().equals(choice)) {
                    System.out.println(m.getName());
                    System.out.println(m.printableIngredientList("The ingredients for this meal are: "));
                }
            }
            loop = false;
        }
    }

    // EFFECTS: returns the total quantity of the given ingredient that is required for that week's planned meals
    /*private double quantityOfIngredientRequiredForWeek(Ingredient inputIngredient) {
        double quantity = 0;
        for (Meal m : mealsForTheWeek) {
            for (Ingredient mealIngredient : m.getIngredients()) {
                if (mealIngredient.getName().equals(inputIngredient.getName())) {
                    quantity += mealIngredient.getQuantity();
                }
            }
        }
        return quantity;
    } */

    private void addToShoppingList(Ingredient i) {
        Ingredient pantryIngredient = pantry.getSpecificIngredient(i);
        Ingredient shoppingListIngredient = shoppingList.getSpecificIngredient(i);
        double quantityOfIngredientRequiredForWeek = mealsForTheWeek.quantityOfIngredientRequiredForWeek(i);
        if (!pantry.contains(i)) {
            Ingredient ingredientToAdd = new Ingredient(i.getName(), quantityOfIngredientRequiredForWeek);
            shoppingList.addIngredient(ingredientToAdd);
        } else if (pantryIngredient.getQuantity() < quantityOfIngredientRequiredForWeek) {
            if (!shoppingList.contains(i)) {
                i.decreaseQuantity(pantryIngredient.getQuantity());
                shoppingList.addIngredient(i);
            } else {
                shoppingListIngredient.increaseQuantity(
                        quantityOfIngredientRequiredForWeek
                                - pantryIngredient.getQuantity()
                                - shoppingListIngredient.getQuantity());
            }
        }
    }

    private void addToPantry(Ingredient i) {
        pantry.addIngredient(i);
        removeFromShoppingList(i);
    }

    // REQUIRES: the given ingredient must exist in the pantry
    private void removeFromPantry(Ingredient i) {
        Ingredient pantryIngredient = pantry.getSpecificIngredient(i);
        double quantityOfIngredientRequiredForWeek = mealsForTheWeek.quantityOfIngredientRequiredForWeek(i);
        if (pantryIngredient.getQuantity() > i.getQuantity()) {
            pantryIngredient.decreaseQuantity(i.getQuantity());
            if (pantryIngredient.getQuantity() < quantityOfIngredientRequiredForWeek) {
                addToShoppingList(i);
            }
        } else {
            pantry.removeIngredient(pantryIngredient);
            if (quantityOfIngredientRequiredForWeek > 0) {
                addToShoppingList(i);
            }
        }
    }

    private void removeFromShoppingList(Ingredient i) {
        Ingredient shoppingListIngredient = shoppingList.getSpecificIngredient(i);
        if (shoppingList.contains(i)) {
            if (shoppingListIngredient.getQuantity() > i.getQuantity()) {
                shoppingListIngredient.decreaseQuantity(i.getQuantity());
            } else {
                shoppingList.removeIngredient(shoppingListIngredient);
            }
        }
    }


}
