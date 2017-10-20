# cs360Scrum-

MVC Architecture: 
1. Model : 
    a. Serves as base/foundation
    b. Worked on by view/controller
    c. Stores data retreived according to commands from comtroller and displayed in view
2. View : 
    a. Updation
    b. GUI Setup
    c. Generates new output to user based on changes in the model
3. Controller : 
    a. Gameplay
    b. other controls
    c. Will contain instances of model and view
    d. Can send commands to and update the model; also sends commands to its associated view to change the view's presentation of the            model. 
    
General Process to be followed in the program : 
1. User will see a splash screen
2. User will select option for game type
3. User will see a UI generated with all components -> Part of View
4. User will start the game
5. User will select a location to place the tile on the board : 
    1. Control will call for updation  
       1. First place current tile in the location
       2. Need to obtain surrounding tiles as neighbours
         -> Check useful ones and filter them (usefulNeigbours are those which have a value; Hint: usefulNeighbours might be disabled by               default!)
       3.  Check for successful placement : 
         1. If successful placement : 
            1. Update Tiles : 
               1. Change to empty values
               1. Update the GUI -> Call to view
            2. Update Points : 
               1. Check and update bonus points if required. Else updates general points
            3. Update moves if gametype is limited moves game
         2. If unsuccessful placement : 
            1. Make sure placed tile updates with the value
            2. Update moves if required by gametype
