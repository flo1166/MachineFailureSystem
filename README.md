## MachineFailureSystem
# General
This solves via a simulation the question how available a set of machines are over time. In this code a abstract method was implemented to calculate a probability sequence depending on the datatype (Standard Machine or Priority Machine).

# Structure
+ machine - this package has the machine objects
+ Machine - is a machine object
+ PriorityMachine - extends the machine object with a specific type of machine
+ StandardMachine - extends the machine object with a specific type of machine
+ simulation - is the package with the simulation
+ AvailabilityMethods - has the methods to check the simulation
+ Simulation - inhabits the simulation and check for the simulation
