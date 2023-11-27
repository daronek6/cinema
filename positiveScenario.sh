#!/bin/bash

start_time=$(date +"%Y-%m-%d")"T00:00:00" #Today
end_time=$(date --date="8 hour" +"%Y-%m-%dT%H:%M:%S") #From current time +8h

screenings=$(curl localhost:8080/screenings?start=$start_time'&'end=$end_time) #gets list of Screening object sorted my movie name and start time
echo $screenings

screeningInfo=$(curl localhost:8080/screening/1) #gets screening information and currently reserved seats
echo $screeningInfo

reservation=$(curl -X POST localhost:8080/reservation/screening/1 -H "Content-Type: application/json" -d '{"name":"Tommy","surname":"Lidźons-Ćńś","seats": [{"row":0,"col":0},{"row":0,"col":1},{"row":0,"col":4},{"row":1,"col":0}],"adultTickets":2,"studentTickets":1,"childrenTickets":1}')
echo $reservation # ^ posts reservation informations and returns reservation information with added total and expiration

screeningInfo=$(curl localhost:8080/screening/1) #now this screening shows reserved seats
echo $screeningInfo