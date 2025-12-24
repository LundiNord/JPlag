Electronic Circuit Networks
===========================

An electronic circuit consists of several components, contact points (_pins_) and connections (_wires_). 

Two contact points a and b are said to be **electronically equivalent** if they have a direct connection between them, or if there is a sequence of contact points a1, a2, ..., ak, such that there is a connection between (a,a1), (a1,a2), ..., (ak,b). A **network** is a maximum set of electronically equivalent contact points. _Máximo_ is used here to translate that no contact point outside the network is electronically equivalent to any point within the network.


Task
----

Given a set of contact points and their connections, your task is to discover the number of different networks in the circuit.

Input
-----

The first line has the number **N** of contact points existing (_1 ≤ N ≤ 100_). The second line has the number **L** of existing connections. Following are **L** lines, each with a pair of numbers indicating that contact points are connected by the link.Contact points are always identified by numbers from **1** up to **N**. Note that the links do not come in any specific order.


Output
------

A single line should be printed, containing the number of networks of the respective circuit.


Example
-------

### Input

```txt
14
11
1 11
7 11
11 12
12 2
12 8
3 13
13 4
13 14
14 9
14 5
10 6
```

### Output

```txt
3
```
