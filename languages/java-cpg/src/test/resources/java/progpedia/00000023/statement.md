Electronic Business
===================

A flower _online_ store forwards its customers' orders to associated non-virtual stores. These stores should give a quick response, being responsible for delivering the bucket to the customer. The map of the distribution region is divided into non-overlapping areas. In each area, there is only one store. This store serves the zone if it has availability. If, for any reason, it cannot do so, the online store contacts the remaining stores in increasing order of _distance_ to the destination address, until it finds one that accepts the service. For distance calculation, it considers only paths formed by segments that connect stores in neighboring regions. It is intended to implement a program to support this operation, indicating how stores must be contacted to respond to an order.


Input
-----

The first line has the total number of regions (or stores) and the identifier of the destination region. The regions are defined by consecutive numbers, starting from 1. In each of the following lines, there are the identifiers of two adjacent regions and the distance between them (approximate to the units). Ends with -1.


Output
------

The order in which stores must be contacted (ties are resolved by indicating stores in ascending order of identifier).


Example
-------

### Input

```txt
9 7
1 2 32
1 3 14
1 4 15
2 5 35
3 2 20
5 3 22
3 4 25
5 6 24
2 6 40
7 2 40
7 8 33
6 7 15
6 8 25
9 6 22
9 8 21
5 9 17
5 4 33
9 4 45
-1
```

### Output

```txt
7 6 8 9 5 2 3 1 4
```


Credits
-------

DCC/FCUP 2009 - Ana Paula Tomás
