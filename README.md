Bus simulation happens based in the following three events,

1. person: A person arrives in the queue at a bus stop
Action: After a random (exponentially-distributed inter- arrival) time, another person is scheduled to arrive in the queue
2. arrival: A bus arrives at a bus stop
Action: If there is no one in the queue, the bus proceeds to the next stop, and the event of its arrival there is generated; otherwise, the event to be generated (at present time!) is the first person in the queue boarding the bus;
3. boarder: A person boards the bus
Action: The length of the queue diminishes by 1; If the queue is now empty, the bus proceeds to the next stop, otherwise the next passenger boards the bus

For the beginning, assume there are 15 bus stops, 5 buses, the (uniform) time to drive between two contiguous stops is 5 min., mean arrival rate at each stop is 5 persons/min, and boarding time is 2 seconds. 
Total simuation time is 8 hours.

Start with the buses distributed uniformly along the route (by generating appropriate arrival events) and generating one person event for each stop.




1) When the bus arrival event occurs, if the queue is empty, generate the arrival at the next bus stop at clock + drive_time. If the queue is not empty, generate the boarder event (at clock)
2) When the boarder event occurs, if the queue is empty (i.e., the last person boarded), generate the arrival at the next bus stop at clock + drive_time. If the queue is not empty, generate the boarder event (at clock + boarding_time)
3) At the person event, generate the next person event at the same stop at clock + (mean_inter-arrival_rate) * random (exponential)
Note: Keep time in float or double (a better way of doing things). The mean_inter-arrival_rate = 1/ mean_arrival_rate
