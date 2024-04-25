Task 1
- Run through main class, using main method.
- no alterations are needed
- to change details about the race, i.e the distance, the number of lanes, and horse attributes, this is done in the main class, with simple tweaks to arguments when creating instances of the Horse and Race classes
- as per my improvements, more than 3 lanes can be implemented and so more than 3 horses, as it is in its current state, there are only 3, but more can be added.
- There are no dependencies, everything functions as intended

Task 2 GUI development
- Run through the RaceGUI class, uusing the main method
- no alterations needed
- There is no GUI representation of the race itself, the race is shown in the terminal when the start race button in the GUI is clicked.
- Explanation of the GUI
	- Top left is a slider for the race distance, there is also a button to add obstacles, however this has no function attached to it, the button is just there.
	- There are 3 panels for 3 horses, in my GUI the race is limited to 3 lanes and 3 horses as it is in its current state, alterations can be made to have more,
	here you can enter names for the horses, choose the breed, coat colour and a symbol for each horse.
	- Then there is the start race button, which calls Race.startRace() with all the information about the race distance and the horses. As i said before there is no GUI implementation of the race itself.
	- there is a close button below the start race button, which terminates the program.
	- Lastly, there is a statistics panel, which displays the winning horse, its finishing time and average speed (m/s). If there is no winner, it will say that too.

- There are no dependencies that would not allow the program to run well, the only things to note are that obstacles button does not have a function and the coat colour, and breed of horse have no effect.

This is the end of the README of Muhammed Nazmul Yaseen Haque's HorseRace simulator OOP coursework.

