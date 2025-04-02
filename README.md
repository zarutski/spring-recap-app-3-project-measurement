spring-course-recap-measurement-task
==================

Task consists of 2 pars:
1. sensor/measurements-storing systems (current repository)
2. client sending data (https://github.com/zarutski/spring-recap-app-3-project-sensor)

sensor/measurements system
--------------------------

* POST /sensors/registration (new sensor registration)

  ```
	 {
		"name": "Senson name"
	 }
  ```
   - unique name
   - allowed length is from 3 to 30 (inclusive)
   - when trying to register a sensor with an existing name, or violate the length restrictions, readable error message will be received
--------------------------
* POST /measurements/add (client's measurements recievement)

  ```
   {
		"value": 24.7, 
		"raining": false, 
		"sensor": {
			"name": "Sensor name"
		}
   }
  ```
   - recieve values from the client: temperature, rain presence indication (true/false), measurement's source (sensor)
   - db stores: temperature, rain presense indication, sensor name, current time (sets while saving on server)
   - validation of the values of all measurement fields (readable error messages)

  params:
   - value: not empty, allowed values from -100 to 100 (inclusive)
   - raining: not empty
   - sensor: must exist in the system (must be registered)

--------------------------
* GET /measurements (get all measurements)
* GET /measurements/rainyDaysCount (get rainy days count)
