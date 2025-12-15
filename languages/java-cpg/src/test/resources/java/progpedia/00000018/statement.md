Minimalist Optics
=================

It is intended to define the links between nodes of a network taking into account the expected gross income for each direct connection between two nodes.If possible, it should be preserve the network connectivity and minimize the costs of its global maintenance and, having already guaranteed these two conditions, maximize global net income. Maintenance costs are identical for all calls.


Input
-----

In the first line three integers are given: the number of nodes in the network, the number of possible calls and the cost of maintaining each connection.This is followed by a line with three integers, for each connection: the first two identify their extremes and the third is their gross performance. Nodes are identified by consecutive numbers, starting from 1.


Output
------

`rendimento optimo` (i.e., optimal income) followed by the net income value of any optimal network or the message `impossivel` (i.e., impossible) if it is not possible to guarantee connectivity.

Examples
--------

### Input 1

```sh
8 9 2
1 2 6
1 5 6
4 8 6
3 6 3
7 4 2
2 5 4
6 2 4
5 6 1
2 3 3
```

### Output 1

```sh
impossivel
```

### Input 2

```sh
8 13 2
1 2 6
1 5 6
8 7 6
4 8 6
3 4 4
3 6 3
7 4 2
2 5 4
6 2 4
5 6 1
4 6 9
7 6 5
2 3 3
```

### Output 2

```sh
rendimento optimo: 27
```


Credits
--------

DCC/FCUP 2009 - Ana Paula Tomás
