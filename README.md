# Portfolio project IDATA1003 - 2023
This file uses Mark Down syntax. For more information see [here](https://www.markdownguide.org/basic-syntax/).

STUDENT NAME = Jon Bergland  
STUDENT ID = 561805

## Project description

[//]: # (TODO: Write a short description of your project/product here.)

This project represents a simplified system of train departures at a train station.
The project is limited to an overview of train departures at only one train station and only for
a time within one day. The time gets manually updated from a user menu.

The application presents a text-based user interface, in the form of a menu, with methods to add, 
set a specific track or delay and find specific train departures by their unique train number or destination.

## Project structure

[//]: # (TODO: Describe the structure of your project here. How have you used packages in your structure. Where are all sourcefiles stored. Where are all JUnit-test classes stored. etc.)

The project is structured with the task requirements and assessment basis in the "Docs" folder and
the source code (source files and test) in the "src" folder. Within the "src" the test are packaged
in test/java/edu/ntnu/stud, and the source files are packaged in main/java/edu/ntnu/stud.

In the source files, the custom exceptions and verification classes are further packaged in their respective
folders.

## Link to repository

[//]: # (TODO: Include a link to your repository here.)

https://gitlab.stud.idi.ntnu.no/jobergl/idatt1003-2023-mappe-traindispatchsystem

## How to run the project

[//]: # (TODO: Describe how to run your project here. What is the main class? What is the main method?
What is the input and output of the program? What is the expected behaviour of the program?)

The main entrypoint for the application is in the "TrainDispatchApp" file.

To start the program, run this file. If compiled correctly, an overview over the initial train departures will be 
printed out in the terminal along with a menu over the different operations the program can perform.
To run a specific type of operation in the corresponding number into the console.

## How to run the tests

[//]: # (TODO: Describe how to run the tests here.)

IntelliJ:
After opening the project in IntelliJ, first right-click on the edu.ntnu.stud package and then click
on "Run Test in edu.ntnu.stud" (or use 'Ctrl + Shift + F10')

Visual Studio Code:
After opening the project in VSCode, click the "Testing" button on the menu on the left side of the project.
There you can click on "Run Test" to run all the test in the project.


## References

[//]: # (TODO: Include references here, if any. For example, if you have used code from the course book, include a reference to the chapter.
Or if you have used code from a website or other source, include a link to the source.)

https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/

https://www.stackoverflow.com

