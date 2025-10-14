[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/J_c8sizy)
# MassiveMotion
CS 245 Project 02

In this project, I created four different kinds of lists: ArrayList, LinkedList, DoublyLinkedList, and DummyHeadLinkedList. Each
list is it's own respective file that inherits the List class. Additionally, I created a Body class, which represents the celestial
bodies on a canvas. The important traits for the Body class are the x and y coordinates and the respective velocities, which allows
the move function to change the coordinates as if the Body is moving across the canvas. In MassiveMotion, a property file (specified
by a command line argument) is read to give a parameters to the simulation, which includes a list type to indicate which of the four 
lists I made should be used to keep track of bodies. A majority of the functions in the MassiveMotion class were modified compared
to what was given, with the only new function being generate. Generate creates a new Body on either the X or Y axis depending on which
character was inputted, with the locations/velocities being randomized (but still within the given parameters).

Between all of the list types, I believe the arraylist is most suited for tracking the different bodies. The 3 different functions
used in MassiveMotion are get, add, and remove. Adding is the same for all of the lists, at time complexity O(1). Arraylist is
slighty worse due to the times where the array needs to be grown in size, but it's still roughly constant time. Because all 3 of
the linkedlists have a tail, it's easy to add to the end of the list. Remove is also the same for all of the lists at time complexity
O(n). Arraylist has to shift down all of the values later than a removed value, and linkedlist has to iterate through the nodes to
reach the node that wants to be removed. However, for the get function, arraylist can just call on the exact index which is time
complexity O(1) whereas linkedlists have to iterate through the entire list with time complexity O(n), therefore making arraylist
the optimal list type to use to track bodies.

To run the code, use a terminal to first cd into the src folder. Then run "javac MassiveMotion.java", followed by "java MassiveMotion
MassiveMotion.txt", which should then open the canvas in a seperate window. The 'MassiveMotion.txt' file should be the property file
name, so can be replaced by whatever you've named it. If any of the specifications aren't given, the program will use values from
the default version present in the project instructions. Some optimizations include making sure to remove bodies as soon as they leave
the bounds. This avoids having to keep track of bodies that don't show up on the canvas anymore, saving space. Aside from this, the
generate function is used prior to the move/remove function in order to avoid bodies from flickering for a second before disappearing;
which is due to the chance that they're created but instantly moved off the canvas due to random velocities.