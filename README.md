# Color Conquest – Two-Player Network Game

**Author:** @KhaledKadri  
**License:** MIT  

## Description
Color Conquest is a two-player networked game where players compete to dominate a color grid. Each player chooses colors to expand their territory from their starting corner. The game ends when the board is fully colored or only two colors remain, and the player controlling the largest area wins.

## Features
- Real-time multiplayer over a network  
- Interactive and visually appealing color grid  
- Gradient-based graphics for colored tiles  
- Automatic score calculation and winner announcement

## How to Play
1. Player 1 runs the game as the **server**  
2. Player 2 runs the game as the **client** and connects to Player 1 using their IP address  
3. Each player selects colors from the buttons at the bottom of the board  
4. The chosen color spreads to all connected tiles of that color, expanding the player’s territory  
5. The game continues until one player dominates the board or when only two colors are left

## Client-Server Architecture
The game uses a **client-server model** to enable networked multiplayer.

### Server
- Opens a **socket** on port `5000` and waits for a client connection  
- Tracks **Player 1's game state**  
- Sends and receives messages representing moves (color selections) in real time

### Client
- Connects to the server using its IP and port  
- Tracks **Player 2's game state**  
- Sends chosen colors and receives opponent’s moves to update the board

### Communication
- Moves are sent as simple **color strings**  
- Uses **threads** to listen for messages (`Reception`) and to send messages (`Envoi`) without freezing the GUI  
- Ensures that both players see an **up-to-date game board** simultaneously
- The server accepts the client connection  
- Both sides continuously send and receive messages (color moves)  
- The GUI updates in real-time to reflect the current game state

