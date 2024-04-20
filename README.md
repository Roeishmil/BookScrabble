# BookScrabble - Advanced Programing
BookScrabble is a Java-based implementation of the classic word game Scrabble, developed as part of the Advanced Programming course. This project is divided into two parts, each comprising three milestones.


## Video links
- Demo video of the board - will be added
- Project presentation video - will be added

## Game Overview
### The Game Board
The game board is represented by a 15x15 grid of squares, with the bonus slots marked according to the values specified in the rules. Players place their letter tiles on the board to create words, scoring points based on the values of the tiles and their placement on the bonus slots.

![Image](https://camo.githubusercontent.com/cf769f732b6642ed3c36a11c0ef6a21b8773b1fd186b947e4e12bdaf4cdf6d53/68747470733a2f2f692e6962622e636f2f7a7332584d72382f57686174732d4170702d496d6167652d323032332d30352d30322d61742d31372d34312d34352e6a7067)

The board has some bonus slots, which are marked as follows:

- The central square (marked with a star) doubles the value of the word written on it.
- Light blue squares double the value of the letter placed on them.
- Blue squares triple the value of the letter placed on them.
- Yellow squares double the value of the entire word.
- Red squares triple the value of the entire word.

### Tile bag
The tile bag contains tiles with english letters and assigned point values, corresponding to the rarity of each letter. The tile Bag has a total of 98 available tiles at the beginning of the game, with a specific number of tiles for each letter.

![Image](https://user-images.githubusercontent.com/118439273/229486188-1f5ab09f-8f78-4e12-b2d1-8aea60616c7c.png)

### Gameplay
Players take turns placing their letter tiles on the board to form words. The goal is to score the highest points by strategically placing high-value tiles on bonus squares and creating longer words.

To score points, a player must form a valid word horizontally or vertically on the board, with each new word connecting to at least one existing word. The score for each word is calculated by summing the values of the letters and applying any bonuses from the bonus squares.
The first player must place a word on the central square, which doubles the value of that word.To form a valid word, the player places their letter tiles in a horizontal or vertical row, either using existing letters on the board or adding their tiles to create a new word. The formed word must be a valid entry in the game's dictionary, verified by the server's dictionary.

## Features
- **Board Representation**: The game board is represented by a 2D array, with support for bonus tiles.
- **Object-Oriented Design**: The project follows an object-oriented design approach, encapsulating different game concepts and functionality into separate classes, promoting code reusability and maintainability.
- **Server-Client Architecture**: The game follows a server-client model, enabling multiple players to connect and play together.
- **Word Validation**: The Board class includes methods to validate the legality of a word placement on the board, considering factors such as the word's position, intersections with existing words, and the first word being placed on the central square.
- **Word Placement**: Players can place words on the board, and the program validates their legality based on the game rules.
- **Dictionary Integration**: The project includes a dictionary management system, allowing players to query and challenge words.
- **Caching and Bloom Filters**: Efficient caching mechanisms and Bloom filters are employed for optimized dictionary lookups.

## Project Structure
The project follows an object-oriented design approach, with each class responsible for a specific aspect of the game. The classes are organized in a cohesive manner, with each class focusing on a specific aspect of the game's functionality.

The project is organized into the following main components:

- `Board.java`: Handles the game board, word placement, and scoring logic.
- `Tile.java`: Represents individual letter tiles and manages the tile bag.
- `Word.java`: Encapsulates a word formed from tiles.
- `Dictionary.java`: Manages dictionary data and provides methods for querying and challenging words.
- `DictionaryManager.java`: Handles dictionary operations and caching mechanisms.
- `BloomFilter.java`: Implements a Bloom filter for efficient word existence checks.
- `MyServer.java`: Runs the game server and handles client connections.
- `BookScrabbleHandler.java`: Processes client requests related to querying and challenging words.
- `Client.java`: Represents a client application that connects to the server.
- `ClientHandler.java`: This interface defines the contract for handling client connections.
- `IOSearcher.java`: This class provides functionality for searching words within text files.
- `LFU.java`: This class implements the Least Frequently Used (LFU) cache replacement policy.
- `LRU.java`: This class implements the Least Recently Used (LRU) cache replacement policy.
- `Word.java`: This class represents a word in the game, including its tiles, position, and orientation.

## Acknowledgments
- Special thanks to my girlfriend and future wife for her guidance and emotional support.
- the entire project was made by following the instructions giving by Dr. Eliahu Khalastchi and with the gudince of the course instructor and teaching assistants.  
