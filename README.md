# Game of Life
Charlotte Metcalfe
BBC Graduate Scheme application 

This simulates Conway's Game of Life. It implements all of the rules and has Junit tests. 

## Running
You can run it directly in IntelliJ by running the Main class.

Or from the command line using gradle:

```
./gradlew assemble
java -jar build/libs/GameOfLife.jar
```

##Usage
 
There is a command line interface that presents users with 3 options for starting positions.

After selecting your starting position, press one to see the game advance. 

## Testing

There are Junit tests available to test the functionality. 

These can be accessed with gradle:

```
./gradlew test
```

## Areas of Improvement 

There are a number of ways this code can be improved. 

My next steps would be to reduce areas of unused grid. Currently the grid can grow to allow for new cells but does not 
reduce to remove areas of dead cells. 

It would also be nice to create a web
 app to improve the user experience. 
 
 It would also be great to give users the chance to select their own starting positions and board size. 