# Portfolio project IDATA1003 - 2023

STUDENT NAME = Jon Bergland  
STUDENT ID = 561805

## Project description

This project represents a simplified system of train departures at a train station.
The project is limited to an overview of train departures at only one train station and only for
a time within one day. The time gets manually updated from a user menu.

The application presents a text-based user interface, in the form of a menu, with methods to add, 
set a specific track or delay and find specific train departures by their unique train number or destination.

## Project structure

The project is structured with the task requirements and assessment basis in the "Docs" folder and
the source code (source files and test) in the "src" folder. Within the "src" the test are packaged
in test/java/edu/ntnu/stud, and the source files are packaged in main/java/edu/ntnu/stud.

In the source files, the custom exceptions and verification classes are further packaged in their respective
folders.

## Link to repository

Link to gitlab:

https://gitlab.stud.idi.ntnu.no/jobergl/idatt1003-2023-mappe-traindispatchsystem

## How to run the project

The main entrypoint for the application is in the "TrainDispatchApp" file.

To start the program, run this file. If compiled correctly, an overview over the initial train departures will be 
printed out in the terminal along with a menu over the different operations the program can perform.
To run a specific type of operation in the corresponding number into the console.

## How to run the tests

IntelliJ:
After opening the project in IntelliJ, first right-click on the edu.ntnu.stud package and then click
on "Run Test in edu.ntnu.stud" (or use 'Ctrl + Shift + F10')

Visual Studio Code:
After opening the project in VSCode, click the "Testing" button on the menu on the left side of the project.
There you can click on "Run Test" to run all the test in the project.


## References


GeekforGeeks, unknow date. Sorting a HashMap accoring to values.
https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/


