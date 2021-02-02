# Texas Hamburger Company Admin REST API SpringBoot Application with MongoDB and MySQL

  *  Developed Microservices aroung REST Controllers.
  *  Implemented Spring Security using Basic Auth to authenticate and authorize users.
  *  Implemented Logging and Exception Handling using Log4j2 and @ControllerAdvice
  *  Created a Dockerfile and Docker-sompose for assemble images and to containerize the Hamburger Admin Application.
  *  Implemented Swagger-UI Documentation for Rest APIs.
  *  Implemented Pagination features for GET request using HATEOAS Component.
  
### Rest API Endpoints:
  
  * Location Controller:
     *  GET: /api/locations : Fetch all locations in the Location Entity (Optional: Request Parameter 'name' to filter by Location name)
     *  GET: /api/locations/{id} : Fetch location by id in the Location Entity
     *  GET: /api/locations/active/{active} : Fetch all locations in the Location Entity based on their active status
     *  POST: /api/locations : Create a new Location in the Location Entity
     *  PUT: /api/locations/{id}  : Update location by id in the Location Entity
     *  DELETE: /api/locations/{id} : Delete location by id in the Location Entity
     *  DELETE: /api/locations  : Delete all locations in the Location Entity
     
  * Menu Controller:
     *  GET: /api/menus : Fetch all Menu Items in the Menu Entity (Optional: Request Parameter 'name' and/or 'category' to filter by Menu Items name and/or category)
     *  GET: /api/menus/{id} : Fetch Menu Items by id in the Menu Entity
     *  GET: /api/menus/comboAllowed/{combo} : Fetch all Menu Items in the Menu Entity based on their comboAllowed status
     *  POST: /api/menus : Create a new Menu Item in the Menu Entity
     *  PUT: /api/menus/{id}  : Update Menu Item by id in the Menu Entity
     *  DELETE: /api/menus/{id} : Delete Menu Item by id in the Menu Entity
     *  DELETE: /api/menus  : Delete all Menu Items in the Menu Entity
     
   * PartyReservation Controller:
     *  GET: /api/reservations : Fetch all Reservations Items in the PartyReservation Entity (Optional: Request Parameter 'name' and/or 'partyType' to filter by Reservation name and/or partyType)
     *  GET: /api/reservations/{id} : Fetch Reservation by id in the PartyReservation Entity
     *  POST: /api/reservations : Create a new Reservation in the PartyReservation Entity
     *  PUT: /api/reservations/{id}  : Update Reservation by id in the PartyReservation Entity
     *  DELETE: /api/reservations/{id} : Delete Reservation by id in the PartyReservation Entity
     *  DELETE: /api/reservations  : Delete all Reservation in the PartyReservation Entity
     
   * OpenHours Controller:
     *  PUT: /api/hours/{id} : Update Open Hours for a Location by id in the Location Entity
     
   * APIDetails Controller:
     *  GET: /api/exectime : Get all API Execution Details in the APIDetails Entity (Optional: Request Parameter 'reqName' and/or 'reqTimeStamp' to filter by API Details name and/or timestamp)
