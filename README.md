# DrivingFrenzy

This is a vehicles speed competition. Several vehicles run individually in a track to do the best time. 
From the Main menu in the ControlCenter you should launch one of the types of races (currently, only simpleRandomRace is implemented). 

The main parts in this project are: 
- ControlCenter, located in the ``main`` package. It is the one that has a ``main`` method and allows us to run races.  
- ``Vehicle``, located in the ``vehicles`` package. It is an interface showing the methods that the ControlCenter can use to simulate how the vehicles drive. 
    - In the same package there is a ``Scooter`` class, implementing this interface, as an example. 
- ``Track``, located in the ``race`` package. It is just a placeholder for ``Section`` objects - each Track will have one or more sections (think of sections as the different turns and straight roads that a real track could have)  
    - In the same package there is a ``Section`` interface defining which operations a section should have. There is also a ``StandardIndoorSection`` which represents just a simple road.
    - **The max speed in each section is not defined by the vehicle, but by the section. I.e, the max speed in a section is the same for a Ferrari and Go-Go-Action Bronco.**

The goals of this example are: 
- Show the use of interfaces - note that to actually do the competition we only need to access to "Vehicle" objects, we do not need to declare them as Scooter or any other vehicle type. 
- Show how we can create different objects of the same Class with different characteristics. 
- Show how we can use objects between functions. 


## The Control Center
The control center simulates careers. After the track and Vehicles are defined, it will: 
1. Choose the next vehicle. 
2. Ask the vehicle to adapt its speed by giving the vehicle information about the next section. **The vehicles must only use the theoretical max speed of the section to adapt**.
3. Check what is the new speed of the vehicle. That speed will be used for that specific section. 
4. Go back to point 2 until there are not any sections left. 
5. Go back to point 1 until there are not any vehicles left.
6. Show the result of each vehicle. 

Also, the Control Center will print information about the drivers and the track, and how each vehicle is doing.

## Initial steps

- Run the application several times as it is and try to follow the code, to understand how it works.  
- Create a method similar to ``simpleRandomRace`` in which: 
    - We have a track with 5 sections of 1000, 2000, 3000, 2000 and 1000 meters of length; you can choose the max speed.
    - We have just 3 vehicles. Give the vehicles proper descriptions and drivers. 
- Sort the results so we print the different vehicles/drivers in order. To do this, the method "start" can only be modified in the last part, the one with the comment  ``MODIFY THIS to show the results sorted by total time.`` 
- Implement a new type of vehicle. This vehicle is a Kart with 2 gears. 
    - Each gear will have a minimum and maximum speed. 
    - When calling to `adaptSpeed`, the Kart can do one of the following: 
        - Increase or decrease speed within the current gear. 
        - Shift a gear up (new speed will be the minimum of the second gear).
        - Shift a gear down (new speed can be any of within the range of the first gear).
- Create a new race (i.e, a new method in the Control Center) and test the new Kart.         

## Next steps 
- Create a race in which both Scooters and Karts compete together. Remember, **the ``start`` method cannot be modified**.
    - Choose 3 real one-gear Scooters and 3 real 2-gear Karts to get actual statistics on speed - don't spend more than 10 minutes searching, otherwise, just make up the numbers. 
- Create a new type of ``Section`` called ``StandardOutsideSection``. This one has its theoretical max speed, but also the actual one. The actual one is just a percentage of the theoretical one. 
    - For instance, think of a rainy day. Actual max speed could be ``0.7 * maxSpeed``. Or, think of a very sunny day; the actual max speed could be ``1.1 * maxSpeed``.
- Modify the ``start`` method: if a vehicle goes over the actual max speed of a section, it goes out of the track and gets disqualified. 

## Final steps 
- Automate the creation of a long track - at least, 50 kilometers with 5 sections. . 
- Create a new type of section, ``VariableClimateSection``. 
    - The oddity of this section is that the actual speed is randomized. For instance, a specific section may apply a 1.1 multiplier for a car, or a 0.9 for a different car (it represents a different time of the day).
        - The multiplier must be between 1.2 and 0.8. 
    - At the start of the race, randomly select a main climate: Sunny or Rainy. 
        - If Sunny, there are more chances per section of getting a large (>1) multiplier - 80 vs 20. If Rainy, there are more chances of getting a small multiplier.
    - When getting the actual max speed of the section, the Control Center takes it into account to print if it was dry or wet.
- Implement a new type of vehicles with 6 gears, normal cars. Also, these cars receive the prediction of the main climate. 
    - Implement the ``adaptSpeed`` method similar to the one in the Karts. However, the vehicle has a new attribute `driverStyle` which can be standard, aggressive or conservative. 
        - An aggressive driver will try to play with the odds to go faster than the theoretical max speed. For instance, on a Sunny day, it may decide to go over the max speed limit more often (also, it decides how much it will go over the speed limit). 
        - A conservative driver will always try to prevent getting out of the track.    
        - A standard driver will be conservative on rainy days, aggressive on sunny days.    

          



 