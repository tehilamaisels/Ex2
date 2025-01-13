Ex2 Spreadsheet Project
Introduction
This project implements a spreadsheet simulation, allowing users to manage and evaluate data in a grid of rows and columns. Each cell can store numbers, text, or formulas, with dynamic calculations and error handling for dependencies and invalid inputs.

Main Components
Ex2Sheet Class
The Ex2Sheet class represents the core of the spreadsheet. It provides methods to manipulate cells, evaluate formulas, and save or load the spreadsheet's state.

Constructor Methods
Ex2Sheet(int w, int h)
Creates a spreadsheet with the specified width and height.

Ex2Sheet()
Initializes a spreadsheet with default dimensions.

Key Methods
Cell Manipulation:

void set(int x, int y, String value): Assigns a value to a cell, validating numbers and formulas.
Cell get(int x, int y): Retrieves the cell at the given coordinates.
Cell get(String entry): Accesses a cell using a reference like B3.
Data Evaluation:

String value(int x, int y): Returns the evaluated value of a cell.
void eval(): Updates all cells' values.
int[][] depth(): Computes the dependency depth for each cell.
File Operations:

void save(String fileName): Saves the spreadsheet to a file.
void load(String fileName): Loads data into the spreadsheet, replacing existing content.
SCell Class
The SCell class handles individual cells, storing their content and managing types like text, numbers, and formulas.

Core Features
Type Detection: Automatically determines if the cell content is text, a number, or a formula.
Data Normalization: Ensures numeric values are formatted consistently.
Formula Validation: Validates and computes formulas, handling parentheses and operators.
Main Methods
void setData(String s): Updates the cell content and type.
String getData(): Retrieves the cell's content.
boolean isNumber(String text): Checks if a string is a valid number.
boolean isForm(String text): Validates if a string is a well-formed formula.
Ex2GUI Class
The Ex2GUI class provides an interactive graphical interface for the spreadsheet. Users can:

Click cells to edit their content.
Input formulas (e.g., =A1+B2).
Save and load spreadsheets from the GUI.
The GUI leverages the StdDraw library for rendering the interface.

Index2D Interface
The Index2D interface represents 2D coordinates in the spreadsheet.

Key Methods
String toString(): Converts coordinates to a string format like C5.
boolean isValid(): Validates if the coordinates are within the spreadsheet's bounds.
int getX(), int getY(): Retrieve the X and Y coordinates, respectively.
Error Handling
The project includes robust error detection for:

Invalid Formulas: Returns ERR_FORM for malformed formulas.
Circular Dependencies: Detects cycles and flags them with ERR_CYCLE.
How to Use
Run the Program: Launch the Ex2GUI class.
Edit Cells:
Enter numbers, text, or formulas (e.g., =A1*B2 + 3).
View computed results in real time.
Save/Load: Use the GUI or programmatic methods to save and reload spreadsheets.
Example Formula Syntax
Basic Math: =5+3 → 8
Cell Reference: =A1+B2
Complex Formula: =(A1+B2)*2
Acknowledgments
This project was developed as part of Ariel University’s Intro to Computer Science course. It is based on guidelines provided by Boaz Ben Moshe.

Notes
Dependencies: The project uses the StdDraw library for GUI rendering.
Limitations:
Advanced formulas (e.g., functions like SUM) are not supported.
The depth of calculations may affect performance for large spreadsheets.
