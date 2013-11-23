Sudoku
======

The Sudoku project is a project with the target to create a generator for random Sudokus.
In order to do this, it has several usefull parts like a general Sudoku definition, 
different Sudoku output methods and a solving algorithm for Sudokus.


PROJECT PACKAGES:

de.catchycube.sudoku.general:
  This package contains the basic Sudoku class.
  A Sudoku object handles all the number fields for the Sudoku and also wich ones where preset or not.
  It also can be checked for validity and it is possible to check if a number is valid for a certain spot.

de.catchycube.sudoku.generator:
  This package contains the Sudoku generator class.
  It can create random Sudokus and offers different settings, but some of them are still NYI!
  
de.catchycube.sudoku.gui.swing
  This package contains everything needed for the included Java Swing GUI including a main method.
  All texts used in the GUI are loaded from the language package.
  
de.catchycube.sudoku.gui.swing.applet
  This package consists of classes needed for the Applet, wich can be integrated into HTML-Websites.
  This package also uses the language package and some of the texts used by the JFrame GUI.
  
de.catchycube.sudoku.gui.swing.language
  This package offers a Language class wich is capable of reading files and parsing them into a Map.
  Also the class always has one language saved as current so you can access the current language from every class in the project.
  The package also contains a german language file, but you can easily create your own language file and change the file to load in the main method of MainWindow or the constructor of SudokuApplet
  
de.catchycube.sudoku.properties
  This package offers a way to load properties from files and access them with keys later.
  It basically works like the languages and there also is a default properties file wich you could extend or your users could change it.
  However, currently there is no way of saving properties integrated, so if you want to implement a properties dialog and want to save the properties you would have to figure out a way (shoud not be to hard)
  
de.catchycube.sudoku.input
  This package is meant to offer different ways of loading Sudokus but currently only offers the possibility to load plain text files.
  
de.catchycube.sudoku.output
  This package offers several ways of output for Sudokus: The easiest way of output is printing to a PrintStream (e.g. System.out),
  but you can also write to text files, save as an image or print to any Graphics object.
  
de.catchycube.sudoku.solver
  This package contains the solving algorithm wich can solve any Sudoku that has at least one solution.
  It consists of to parts:
  The first part uses logical reasons to put numbers in fields, wich where inspired by several pretty good Sudoku solving human beeings.
  Some Sudokus can not be solved by only using logical reasons, so the algorithm can start brute forcing, but in order to limit computing time or simulate a humans way of solving you can limit the brute force depth.
