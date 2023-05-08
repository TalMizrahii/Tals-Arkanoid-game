# Tals-Arkanoid-game
This is an Arkanoid game project that was developed as part of an Object-Oriented Programming course, Bar Ilan University. The game contains several levels with increasing difficulty and is implemented in a GUI-friendly platform.

![Picture1](https://user-images.githubusercontent.com/103560553/189338805-3e25dfd9-a77e-4b0f-b8b0-fddaca9c5eda.png)


## About
This project was coded as a 7-part ongoing assignment on the 2nd semester of my 1st year at BIU. This project was coded using a single thread, which is rare for games such as this. Also, it uses no Java GUI Built-In objects, instead, I used a GUI implementation which is included in this repository.
I implemented a version of the Arkanoid game, as part of the OOP course i took during my bachelor degree.</br>
The game contains various levels with increasing difficulty, in a gui friendly platform.</br>
I implemented the game with Java language using Intellij IDEA Ultimate IDE, using the following OOP principals:
* Polymorphism and inheritance.
* Usage of basic design patterns in OOP, such as Observer, Builder, etc.
* Usage of several generic collections and data structures such as linked lists and arraylists.
* Working with GUI.

## Design Patterns

### Builder Design Pattern
The Builder design pattern was used in this project to create various game objects in a step-by-step manner. This allows for greater flexibility and ease of use, as each builder class is responsible for constructing a specific object. Builders were used to create balls, blocks, limits, and paddles in a clear and organized manner. This approach allows for more readable and maintainable code and makes it easy to modify the properties of each object during the construction process.

### Strategy Design Pattern
The strategy design pattern was used in the project to allow different behaviors for block placement and coloring in the first level of the Arkanoid game. The implementation of these strategies allows for more flexibility in creating different levels with different characteristics without having to change the core game code.

### Decorator Design Pattern
The decorator design pattern was used to add new functionalities to existing Animation objects. The WaitingForKeyPressDecorator class is the decorator, while the PauseScreen and KeyPressStoppableAnimation classes are the concrete components that are decorated. The decorator pattern allows for dynamically adding new functionalities to existing objects without changing their structure, while keeping the code clean and maintainable.

### Observer Design Pattern
The Observer design pattern was used to handle events triggered by different game objects. The game objects are implemented as Observables, and the listener classes are implemented as Observers. The listener classes subscribe to events triggered by the game objects and act accordingly, such as removing blocks or balls from the game or updating the player's score. The use of the Observer pattern allows for a flexible and modular code structure, where new listeners can easily be added without modifying the game objects themselves.

## Dependencies
* Windows / Linux / macOS
* Git
* Keyboard that contains: Spacebar (for stoppage), "P" button, and all arrows.
* Apache Ant

## Installing And Executing
If you wish to run the game you can downlaod the "TalsArkanoid1.jar" from the release section.
