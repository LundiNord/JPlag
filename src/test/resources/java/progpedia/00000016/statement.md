Alphabet Soup
=============

Two words formed by capital letters are given. It is assumed that they are ordered lexicographically, being the first smaller or equal to the second. It is intended to find the pair of letters that distinguishes them and that determines this order, if this pair exists. The pair exists as long as the first word is neither a proper prefix nor equal to the second.


Input
-----

Two lines, each with a word. Each word has 20 letters at most and may have repetitions.


Output
------

A line with the pair of letters requested, if it exists. Otherwise, you will have the word `Nenhum` (i.e., None).


Examples
--------

### Input 1

```sh
MPMCAC
MPAMJ
```

### Output 1

```sh
MA
```

### Input 2

```sh
CCM
CCMP
```

### Output 2

```sh
Nenhum
```

### Input 3

```sh
MPAMJ
MPMCAC
```

### Output 3

```sh
AM
```


Credits
-------

DCC/FCUP 2010 - Ana Paula Tomás
