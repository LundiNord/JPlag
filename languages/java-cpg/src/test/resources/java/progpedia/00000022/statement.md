Infrastructure
==============

An animal will be transported in a rectangular cage whose dimensions (length, width and height) will have to be within certain intervals. These dimensions are still conditioned both by the infrastructure of the transport network and by the type of vehicles that may ensure transportation in each section of the path. The problem was analyzed by a technical team that provided the capacity of each section of the network, taking into account the type of vehicles available. Considering the restrictions of the cage and the network, it is intended to determine the **number of sections** that could pass.


Input
-----

The first line has the number of places (extremes of sections).

In the second line, five integers separated by a space: minimum and maximum width, minimum and maximum length, and minimum cage height. The third line has two integers that identify origin and destination. Each of the remaining lines, except for the latter, has five integers separated by spaces: the first two identify the extremes of a section, and the following the maximum values for the width, length and height of the cage, if the route includes this section. The last line has the value -1. The dimensions are in decimeters. Any connection is bidirectional. The locations are identified by **consecutive** integers.


Output
------

The number of sections in which it could pass, taking into account the dimension restrictions, but **not** the origin and destination.


Examples
--------

### Input 1

```txt
6
20 25 15 40 5
4 1
1 4 10 30 23
3 5 22 30 15
1 2 25 30 50
5 2 20 30 50
3 2 30 27 20
2 4 15 35 25
5 6 29 20 35
1 5 30 25 20
4 6 23 25 15
6 3 30 40 16
-1
```

### Output 1

```txt
8
```

### Input 2

```txt
6
20 30 25 40 15
3 4
1 2 25 30 50
3 2 19 27 20
2 4 15 35 25
5 6 29 20 35
1 5 30 25 20
4 6 23 25 15
3 5 12 30 15
1 4 20 30 23
6 3 30 20 16
-1
```

### Output 2

```txt
4
```
  

Credits
--------

DCC/FCUP 2009 (Prova Prática - A) - Ana Paula Tomás
