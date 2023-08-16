package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


// Structure of UI class and runWeeklyPlanner method referenced from TellerApp
// JSONSerializationDemo application used as a reference for persistence

public class ImprovedConsoleWeeklyPlanner {

    private static final String JSON_PANTRY_FILE = "./data/pantry.json";
    private static final String JSON_SHOPPING_LIST_FILE = "./data/shopping_list.json";
   // private static final String JSON_SUNDAY_FILE = "./data/sunday.json";
    //private static final String JSON_MONDAY_FILE = "./data/monday.json";
    //private static final String JSON_TUESDAY_FILE = "./data/tuesday.json";
    //private static final String JSON_WEDNESDAY_FILE = "./data/wednesday.json";
    //private static final String JSON_THURSDAY_FILE = "./data/thursday.json";
    //private static final String JSON_FRIDAY_FILE = "./data/friday.json";
    //private static final String JSON_SATURDAY_FILE = "./data/saturday.json";
    private static final String JSON_DAYSOFTHEWEEK_FILE = "./data/days_of_the_week.json";
    private Day sunday;
    private Day monday;
    private Day tuesday;
    private Day wednesday;
    private Day thursday;
    private Day friday;
    private Day saturday;
    private DayList daysOfTheWeek;
    private Pantry pantry;
    private ShoppingList shoppingList;
    private MealList mealsForTheWeek;
    private Scanner input;
    private JsonWriter jsonPantryWriter;
    private JsonReader jsonPantryReader;
    private JsonWriter jsonShoppingListWriter;
    private JsonReader jsonShoppingListReader;
    private JsonWriter jsonSundayWriter;
    private JsonReader jsonSundayReader;
    private JsonWriter jsonMondayWriter;
    private JsonReader jsonMondayReader;
    private JsonWriter jsonTuesdayWriter;
    private JsonReader jsonTuesdayReader;
    private JsonWriter jsonWednesdayWriter;
    private JsonReader jsonWednesdayReader;
    private JsonWriter jsonThursdayWriter;
    private JsonReader jsonThursdayReader;
    private JsonWriter jsonFridayWriter;
    private JsonReader jsonFridayReader;
    private JsonWriter jsonSaturdayWriter;
    private JsonReader jsonSaturdayReader;
    private JsonWriter jsonDaysOfTheWeekWriter;
    private JsonReader jsonDaysOfTheWeekReader;

    // EFFECTS: creates a new WeeklyPlanner with all the days of the week, a pantry, a shopping list, an empty list of
    //          meals for the week, and a scanner
    //          then runs the primary UI method
    public ImprovedConsoleWeeklyPlanner() throws FileNotFoundException {
        sunday = new Day("Sunday");
        monday = new Day("Monday");
        tuesday = new Day("Tuesday");
        wednesday = new Day("Wednesday");
        thursday = new Day("Thursday");
        friday = new Day("Friday");
        saturday = new Day("Saturday");
        daysOfTheWeek = new DayList();
        daysOfTheWeek.addDay(sunday);
        daysOfTheWeek.addDay(monday);
        daysOfTheWeek.addDay(tuesday);
        daysOfTheWeek.addDay(wednesday);
        daysOfTheWeek.addDay(thursday);
        daysOfTheWeek.addDay(friday);
        daysOfTheWeek.addDay(saturday);
        pantry = new Pantry();
        shoppingList = new ShoppingList();
        mealsForTheWeek = new MealList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        instantiateReadersAndWriters();
        runWeeklyPlanner();
    }

    // EFFECTS: instantiates all the JSON readers and writers
    private void instantiateReadersAndWriters() {
        jsonPantryWriter = new JsonWriter(JSON_PANTRY_FILE);
        jsonPantryReader = new JsonReader(JSON_PANTRY_FILE);
        jsonShoppingListWriter = new JsonWriter(JSON_SHOPPING_LIST_FILE);
        jsonShoppingListReader = new JsonReader(JSON_SHOPPING_LIST_FILE);
       // jsonSundayWriter = new JsonWriter(JSON_SUNDAY_FILE);
        //jsonSundayReader = new JsonReader(JSON_SUNDAY_FILE);
        //jsonMondayWriter = new JsonWriter(JSON_MONDAY_FILE);
        //jsonMondayReader = new JsonReader(JSON_MONDAY_FILE);
        //jsonTuesdayWriter = new JsonWriter(JSON_TUESDAY_FILE);
        //jsonTuesdayReader = new JsonReader(JSON_TUESDAY_FILE);
        //jsonWednesdayWriter = new JsonWriter(JSON_WEDNESDAY_FILE);
        //jsonWednesdayReader = new JsonReader(JSON_WEDNESDAY_FILE);
        //jsonThursdayWriter = new JsonWriter(JSON_THURSDAY_FILE);
        //jsonThursdayReader = new JsonReader(JSON_THURSDAY_FILE);
        //jsonFridayWriter = new JsonWriter(JSON_FRIDAY_FILE);
        //jsonFridayReader = new JsonReader(JSON_FRIDAY_FILE);
        //jsonSaturdayWriter = new JsonWriter(JSON_SATURDAY_FILE);
        //jsonSaturdayReader = new JsonReader(JSON_SATURDAY_FILE);
        jsonDaysOfTheWeekWriter = new JsonWriter(JSON_DAYSOFTHEWEEK_FILE);
        jsonDaysOfTheWeekReader = new JsonReader(JSON_DAYSOFTHEWEEK_FILE);
    }

    // EFFECTS: takes user input and keeps running the program
    //          if the user inputs "9", ends the program
    private void runWeeklyPlanner() {
        boolean loop = true;

        while (loop) {
            String whatToDo;
            displayMenu();
            whatToDo = input.nextLine();
            whatToDo = whatToDo.toLowerCase();

            if (whatToDo.equals("9")) {
                loop = false;
            } else {
                doThing(whatToDo);
            }
        }
        System.out.println("Have a healthy, nutritious day!");
        printLog(EventLog.getInstance());
    }

    private void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.getDate());
            System.out.println("    " + next.getDescription());
            System.out.println("");
        }
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
        System.out.println("\t7: Save your weekly planner, pantry and shopping list to file");
        System.out.println("\t8: Load your weekly planner, pantry and shopping list from file");
        System.out.println("\t9: Quit application");
    }


    // REQUIRES: user must input anything other than "9"
    // EFFECTS: if the user inputs a number from 1-6, executes the respective required function
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
        } else if (whatToDo.equals("7")) {
            doSavingToFile();
        } else if (whatToDo.equals("8")) {
            doLoadingFromFile();
        } else {
            System.out.println("Please only type a number between 1-9");
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

    // REQUIRES: the quantity entered must be a positive number
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
        for (Day day: daysOfTheWeek.getDays()) {
            System.out.println(day.getName() + ": " + day.printableMealList());
        }

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

    // MODIFIES: pantry
    // EFFECTS: adds the given ingredient to the pantry, or increases its quantity if it already exists in pantry
    //          and removes the ingredient from the shopping list
    private void addToPantry(Ingredient i) {
        pantry.addIngredient(i);
        removeFromShoppingList(i);
    }

    // REQUIRES: the given ingredient must exist in the pantry
    // MODIFIES: pantry, shoppingList
    // EFFECTS: if the quantity of the ingredient in the pantry is more than the quantity to be removed:
    //              decreases the quantity of the ingredient in the pantry accordingly
    //              then, if the quantity of the ingredient in the pantry is less than the required amount for the week:
    //                  adds as much of the ingredient to the shopping list so that the week's
    //                  requirement is met by shopping list and pantry
    //          otherwise, removes the entire ingredient from the pantry, and adds required amount of it for the week
    //          to the shopping list
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
            addToShoppingList(i);
        }
    }

    // MODIFIES: shoppingList
    // EFFECTS: if the given ingredient is not in the pantry:
    //              if it is not in the shopping list:
    //                  adds as much of the ingredient to the shopping list as required for the week
    //              otherwise, increases the quantity of the ingredient in the shopping list so that the
    //              week's requirement is met
    //          if the quantity of the ingredient in the pantry is less than the required amount for the week:
    //              if the ingredient is not in the shopping list:
    //                  adds as much of the ingredient to the shopping list so that the week's
    //                  requirement is met by shopping list and pantry
    //              otherwise, increases the quantity of the ingredient in the shopping list so that the
    //              week's requirement is met by shopping list and pantry
    private void addToShoppingList(Ingredient i) {
        Ingredient pantryIngredient = pantry.getSpecificIngredient(i);
        Ingredient shoppingListIngredient = shoppingList.getSpecificIngredient(i);
        double quantityOfIngredientRequiredForWeek = mealsForTheWeek.quantityOfIngredientRequiredForWeek(i);
        if (!pantry.contains(i)) {
            if (!shoppingList.contains(i)) {
                Ingredient ingredientToAdd = new Ingredient(i.getName(), quantityOfIngredientRequiredForWeek);
                shoppingList.addIngredient(ingredientToAdd);
            } else {
                shoppingListIngredient.increaseQuantity(
                        quantityOfIngredientRequiredForWeek - shoppingListIngredient.getQuantity());
            }
        } else if (pantryIngredient.getQuantity() < quantityOfIngredientRequiredForWeek) {
            if (!shoppingList.contains(i)) {
                Ingredient ingredientToAdd = new Ingredient(i.getName(),
                        (quantityOfIngredientRequiredForWeek - pantryIngredient.getQuantity()));
                shoppingList.addIngredient(ingredientToAdd);
            } else {
                shoppingListIngredient.increaseQuantity(
                        quantityOfIngredientRequiredForWeek
                                - pantryIngredient.getQuantity()
                                - shoppingListIngredient.getQuantity());
            }
        }
    }

    // MODIFIES: shoppingList
    // EFFECTS: if the ingredient is in the shopping list:
    //              if its quantity in the shopping list is greater than the given quantity:
    //                  decreases the quantity of the ingredient in the shopping list accordingly
    //              otherwise, removes the ingredient from the shopping list entirely
    //          otherwise, does nothing
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

    // EFFECTS: allows user to choose to save pantry, shopping list and weekly planner to file
    private void doSavingToFile() {
        Boolean loop = true;
        while (loop) {
            System.out.println("What would you like to save? Type 'p' for pantry, 's' for the shopping list, and 'w'"
                    + " for the full weekly planner.");
            String choice = input.nextLine();
            choice.toLowerCase();
            if (choice.equals("s")) {
                saveShoppingListToFile();
                loop = false;
            } else if (choice.equals("p")) {
                savePantryToFile();
                loop = false;
            } else if (choice.equals("w")) {
                saveDaysOfTheWeekToFile();
                loop = false;
            }
        }
    }

    // EFFECTS: saves pantry to file
    private void savePantryToFile() {
        try {
            jsonPantryWriter.open();
            jsonPantryWriter.write(pantry);
            jsonPantryWriter.close();
            System.out.println("Saved pantry to " + JSON_PANTRY_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_PANTRY_FILE);
        }
    }

    // EFFECTS: saves shopping list to file
    private void saveShoppingListToFile() {
        try {
            jsonShoppingListWriter.open();
            jsonShoppingListWriter.write(shoppingList);
            jsonShoppingListWriter.close();
            System.out.println("Saved shopping list to " + JSON_SHOPPING_LIST_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_SHOPPING_LIST_FILE);
        }
    }

    // EFFECTS: saves weekly planner to file
    //private void saveWeeklyPlannerToFile() {
    //  openDayWriters();
    //  writeDaysToFile();
    //  closeDayWriters();
    //  System.out.println("Saved weekly planner to file.");
    //}

    // EFFECTS: saves days of the week to file
    private void saveDaysOfTheWeekToFile() {
        try {
            jsonDaysOfTheWeekWriter.open();
            jsonDaysOfTheWeekWriter.write(daysOfTheWeek);
            jsonDaysOfTheWeekWriter.close();
            System.out.println("Saved weekly planner to file.");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file.");
        }
    }

    // EFFECTS: opens the JSON writers for each day
    // private void openDayWriters() {
    //  try {
    //      jsonSundayWriter.open();
    //      jsonMondayWriter.open();
    //      jsonTuesdayWriter.open();
    //      jsonWednesdayWriter.open();
    //      jsonThursdayWriter.open();
    //      jsonFridayWriter.open();
    //      jsonSaturdayWriter.open();
    //  } catch (FileNotFoundException e) {
    //      System.out.println("Unable to write to file.");
    //  }
    //}

    // EFFECTS: writes to the JSON writers for each day
    //private void writeDaysToFile() {
    //  jsonSundayWriter.write(sunday);
    //  jsonMondayWriter.write(monday);
    //  jsonTuesdayWriter.write(tuesday);
    //  jsonWednesdayWriter.write(wednesday);
    ////  jsonThursdayWriter.write(thursday);
    //jsonFridayWriter.write(friday);
    //  jsonSaturdayWriter.write(saturday);
    //}

    // EFFECTS: closes the JSON writers for each day
    //private void closeDayWriters() {
    //  jsonSundayWriter.close();
    //  jsonMondayWriter.close();
    //  jsonTuesdayWriter.close();
    //  jsonWednesdayWriter.close();
    //  jsonThursdayWriter.close();
    //  jsonFridayWriter.close();
    //  jsonSaturdayWriter.close();
    // }

    // EFFECTS: allows users to choose to load pantry, shopping list and weekly planner from file
    private void doLoadingFromFile() {
        Boolean loop = true;
        while (loop) {
            System.out.println("What would you like to load? Type 'p' for pantry, 's' for the shopping list, and 'w' "
                    + "for the full weekly planner.");
            String choice = input.nextLine();
            choice.toLowerCase();
            if (choice.equals("s")) {
                loadShoppingListFromFile();
                loop = false;
            } else if (choice.equals("p")) {
                loadPantryFromFile();
                loop = false;
            } else if (choice.equals("w")) {
                loadDaysOfTheWeekFromFile();
                loop = false;
            }
        }
    }

    // EFFECTS: loads pantry from file
    private void loadPantryFromFile() {
        try {
            pantry = jsonPantryReader.readPantry();
            System.out.println("Loaded pantry from " + JSON_PANTRY_FILE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_PANTRY_FILE);
        }
    }

    // EFFECTS: loads shopping list from file
    private void loadShoppingListFromFile() {
        try {
            shoppingList = jsonShoppingListReader.readShoppingList();
            System.out.println("Loaded shopping list from " + JSON_SHOPPING_LIST_FILE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_SHOPPING_LIST_FILE);
        }
    }

    // EFFECTS: loads days of the week from file
    private void loadDaysOfTheWeekFromFile() {
        try {
            daysOfTheWeek = jsonDaysOfTheWeekReader.readDayList();
            for (Day day: daysOfTheWeek.getDays()) {
                addMealsToMealsForTheWeekFromFile(day);
            }
            System.out.println("Loaded weekly planner from file.");
        } catch (IOException e) {
            System.out.println("Unable to read from file.");
        }

    }

    // EFFECTS: loads weekly planner from file
    //private void loadWeeklyPlannerFromFile() {
    //  try {
    //      sunday = jsonSundayReader.readDay();
    //      monday = jsonMondayReader.readDay();
    //      tuesday = jsonTuesdayReader.readDay();
    //      wednesday = jsonWednesdayReader.readDay();
    //      thursday = jsonThursdayReader.readDay();
    //      friday = jsonFridayReader.readDay();
    //      saturday = jsonSaturdayReader.readDay();
    //      mealsForTheWeek = new MealList();
    //      addMealsToMealsForTheWeekFromFile(sunday);
    //      addMealsToMealsForTheWeekFromFile(monday);
    //      addMealsToMealsForTheWeekFromFile(tuesday);
    //      addMealsToMealsForTheWeekFromFile(wednesday);
    //      addMealsToMealsForTheWeekFromFile(thursday);
    //      addMealsToMealsForTheWeekFromFile(friday);
    //      addMealsToMealsForTheWeekFromFile(saturday);
    //      System.out.println("Loaded weekly planner from file.");
    //  } catch (IOException e) {
    //      System.out.println("Unable to read from file.");
    //  }
    //}

    //  MODIFIES: mealsForTheWeek
    //  EFFECTS: adds meals from a loaded day of the week to mealsForTheWeek
    private void addMealsToMealsForTheWeekFromFile(Day day) {
        for (Meal m: day.getMeals()) {
            mealsForTheWeek.addMeal(m);
        }
    }
}
