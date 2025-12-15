Order
=====

An animal will be transported by land of a zoo to another under a rare species exchange protocol. The trip will be made in a rectangular cage, whose dimensions (length, width and height) will have to be within certain intervals. These dimensions are still conditioned both by the infrastructure of the transport network and by the type of vehicles that may ensure transportation in each section of the path. The problem was analyzed by a technical team that provided the capacity of each section of the network, taking into account the type of vehicles available. To reduce the animal's stress, it is strongly recommended that the cage has a maximum length. What will be this value?


Input
-----

The first line has five integers separated by one space: minimum and maximum width, minimum and maximum length, and minimum height of the cage. The second line has two integers that identify the origin and the destination. Each of the remaining lines, except for the latter, has five integers separated by spaces: the first two identify the extremes of a section, and the following the maximum values for the width, length and height of the cage if the route includes this section. The last line has the value -1. The dimensions are in decimeters. Any connection is bidirectional. Locations (extremes of sections) are identified by not necessarily consecutive integers.


Output
------

The length of the cage that will carry the animal (expressed in decimeters) or 0 if the transport is not possible.


Example
-------

### Input

```txt
20 30 25 40 15
4 3
1 6 25 30 50
4 6 30 27 20
6 3 15 35 25
50 7 29 20 35
1 50 30 25 20
3 7 23 25 15
4 50 22 30 15
1 3 20 30 23
7 4 30 40 16
-1
```

### Output

```txt
27
```


Credits
--------

DCC/FCUP 2008 - Ana Paula Tomás
