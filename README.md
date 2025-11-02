# Welcome to COMP2000 - Object Oriented Programming Practices
## Session 2, 2025

Please ensure that you follow the weekly updates in this repository

You are free to clone this repository into your own hosted git environment, such as Github, Bitbucket, or Gitlab.

*However*, please be aware that any repository containing your assignment code **must** be made private. Any repository with assignment code that is public available, or found to be shared with other students, will be considered a violation of the academic integrity policy.



Assignment 2:

-This project was designed to simulate a live weather feed on the grid that are received from an external server showcasing the weather conditions.

-Grid dynamics:
        . Blue areas indicate rainfall or visibl flooding
        . Red or brown areas indicate heat temperature
        . Black arrows inside each cell indicate wind diresction and strength


-Design patterns:
        -Observer Pattern:
            . The client acts as a listener constantly monitoring the live weather feed
            .The Client notifies Stage when new data is received
            . Then Stage updates the affected cells by the weather data
        - Startegy pattern:
            .Actors are assigned a unique movement strategy
            .Movement behaviours could be modified without the need to change the Actor's code or any other code

Streams and Lambdas:
    When the server sends lines of data, the program processes them using a single clear stream operation:
      
    lines.map(line -> line.trim().split(" "))
     .filter(parts -> parts.length == 5)
     .forEach(parts -> {
     });

    this code filters out any invalid data lines, seperates the valid lines, and updates the weather feed to the affected cells. This method is shorter and simpler than traditional loops and manual parsing.


Interpreing weather values:

The server provides raw data, attributes, grid coordinates, and values between 0 and 1.

This program converts these values into visual effects on the grid:
.Rain: blue color indication simulating rising flood levels
.Temperature: red/brown tint to simulate the heat
.WindX/WindY: deirection and intensity of the wind

A decay effect also takes place after the rain spots, where it decays and is replaced with temp indication color as it evaporates when no new rain data exists.

