# My Personal Project (z6k8l)

## A Simple Self-service Application for Passengers at an Airport 

**An Introduction about the Application**:

- *Main Functionality*

The main function of this application is to provide booking and check-in services for passengers at an airport.
These services include: plane ticket booking, choosing seats, baggage check-ins, payment and printing 
boarding pass and itinerary(confirmation).
 

- *User Group*

This application is designed for passengers or airport staff.


- *Design Motivation*

Due to pandemic, it has been a long time since last time I traveled by plane. Kinda miss the feeling...
So why not creating an app to pretend that I can book a flight to wherever whenever I want and get ready 
for the journey. It seems fun...


**User Stories**

*Phase 1*

- As a user, I want to be able to search a ticket just for reference;

  - As a user, I want to be able to input my destination;

  - As a user, I want to be able to input my departing time slot(today);
  
  - As a user, I want to be able to see the search results;
  
- As a user, I want to be able to book a ticket;

  - As a user, I want to be able to choose a flight from search results;

  - As a user, I want to be able to input my name, and ID(i.e. passport number);
  
  - As a user, I want to be able to choose a seat;
  
- As a user, I want to be able to confirm and see the result;

- As a user, I want to be able to cancel my previous booking;

- As a user, I want to be able to make another booking.


*Phase 2*

- As a user, I want to be able to save all current airline booking info;

- As a user, I want to be able to load the airline booking info from the last time saved from a file;


*Phase 4: Task 2*

- In `Flight` class in `model` package, `isSeatOccupied` method throws `InvalidSeatException`. The exception is 
additionally tested.

- In `ui` package, all panels except `ContentPanel` inherit the `ContentPanel`, which is the super type. All the panels 
have different individual functionalities, though inheriting most methods in the supertype, they override 
`loadOptionPanel` method in different ways.


*Phase 4: Task 3*

- Low cohesion

  - Some classes have more than one responsibility, for example: `Flight` has list of Passengers and it has to deal with
  seat operation as well, in this case, I could have a separate `Seat` class which only deals with seats;
  If so, many things in the project will be reformed.
  
  - Another one is: `ServiceApp` and `ServiceAppGUI`, they are so powerful such that they incorporate many 
  functionalities users will do, which is typically low in cohesion. From now, we can see some functionalities could be
  extracted into different manager classes like `SearchManager`, `CancelManager`, `BookingManager`, so that if in the 
  future, search has more features to add, `ServiceApp` will not look too heavy.
  
- Insufficient use of diversified data structure

  - Data structures in this project are quite limited, like ArrayList, or ArrayList of ArrayList. Map or HashSet could 
  have been used, for example: in `Airlines`, flight info could be stored as Map with String flight number as key, and 
  `Flight` as value since we don't really care about the order of flights in airlines, and it is easy to fetch a certain 
  flight.
