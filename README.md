# My Personal Project

## Project Proposal

My project is a meal planner. It will allow users to add the meals they plan to
make and/or buy for the week, along with a list of ingredients (for homemade 
meals). There will also be a pantry feature where users can add ingredients 
present in their home. Upon adding the required ingredients for any of the
week's meals, the application will check the pantry and inform the user which
ingredients they need to buy for that week.

This application can be used by anybody who wants to **plan their meals** for the
week or **track their eating habits**. I came up with the idea because I love cooking
and would personally use an application like this to help me organize my meals
and reduce the mental task of having to create a shopping list.

## User Stories

These are the user features I want to incorporate in my application:

- As a user, I want to be able to add meals to my weekly planner
- As a user, I want to be able to add and delete items from my pantry as I use
them in real life
- As a user, I want to be able to select a meal and see the ingredients 
required to make it
- As a user, I want to be able to view my planned meals and their type 
(eg - breakfast, lunch, dinner) for each day of the week 
- As a user, I want to be able to view an automatically generated shopping
list of ingredients I need for the week but haven't yet bought
- As a user, I want to have the option to save my weekly planner and pantry to file 
if I want to do so
- As a user, I want to have the option to reload my weekly planner and pantry 
from file and resume exactly where I left off at an earlier time if I want to do so


## Instructions for Grader (PHASE 3)

- You can generate the first required action related to adding Xs to a Y by typing the name of an ingredient and its
desired quantity in the respective text fields, and clicking the "+" button.
- You can generate the second required action related to adding Xs to a Y by selecting an ingredient from the displayed
ones and then clicking the trashcan button to delete it.
- You can locate my visual component by opening the application - you will see a picture of a pantry.
- You can save the state of my application by clicking the save button.
- You can reload the state of my application by clicking the load button.


## Phase 4: Task 2 
(My phase 4 deadline was extended to April 16, just for your information! Thank you)

Representative sample of events:

Sun Apr 16 16:18:45 PDT 2023

2.0 Banana added to pantry

Sun Apr 16 16:18:54 PDT 2023

12.0 Egg added to pantry

Sun Apr 16 16:19:20 PDT 2023

3.5 Onion added to pantry

Sun Apr 16 16:19:23 PDT 2023

2.0 Banana removed from pantry


## Phase 4: Task 3

If I had more time, I would've wanted to also display the weekly meal planner and shopping list in my GUI, instead of 
only the pantry. I also would've created a DayList class or put a field for list of days within the console weekly 
planner (and create methods for saving and loading a list of days in my persistence package), so that there would only 
be 1 day list instead of 7 days within the console weekly planner, as well as only 3 Json Readers and Writers (for 1 
list of days, 1 pantry, 1 shopping list) instead of 9 (for 7 days, 1 pantry, 1 shopping list). Alternatively, I would've
made day an enumeration with fixed days of the weeks. This would've made the application smoother and more efficient.