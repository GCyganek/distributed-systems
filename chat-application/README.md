Chat application
===

This chat application implemented in Java using sockets allows you to chat with other clients connected to the same server using UDP (message starting with "U ") or TCP (default).
Multicast chatting is also available when starting a message with "M ".

Running server on localhost -> `gradle run --args="server_port"`

Running client -> `gradle run --args="server_host_name server_port multicast_address multicast_port"`

Demo
---

Server running on localhost:8080

![server](https://i.imgur.com/aqBhloX.png)

Client1 with the same multicast addres as Client2

![client1](https://i.imgur.com/4hEN04x.png)

Client2 with the same multicast addres as Client1

![client2](https://i.imgur.com/PBaPLgm.png)

Client3 with different multicast address

![client3](https://i.imgur.com/fGKfFRC.png)
