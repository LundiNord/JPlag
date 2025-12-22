# ToDos

- [x] if with condition information
- [x] switch statements
- [x] loop ai
- [x] exceptions
- [x] hash maps, sets
- [ ] comparators
- [ ] think about copy of bound JavaObject
- [ ] ConditionalExpression (a ? b : c) (hard)
- [x] Array assign
- [ ] Enums
- [ ] Streams
- [ ] Lambdas
- [ ] Import System?
- [ ] Array Init in Method fields
- [x] try/catch
- [ ] finally
- [ ] TreeSet
- [x] Comments/Javadocs
- [ ] Uninitialized variables to default value
- [ ] all java datatypes
- [ ] goto
- [x] return inside if or loop
- [ ] "if (opts.name == null || opts.name.isBlank())" don't evaluate the second part if the first is true

## Edge Cases to Test

- [x] if without else
- [ ] for loop with break/continue
- [ ] switch with literal returns
- [x] nested loops
- [x] Set<Map.Entry<Vegetable, Integer>> sortedEntries = new TreeSet<>(Comparator.comparing((Map.Entry<Vegetable,
  Integer>::getKey)));]
- [x] if with || in condition
- [x] if with && in condition
- [x] if with else-if
- [ ] nested list initializer in a class field
- [x] while with assign in condition

## Object AI

- [ ] Maps
- [ ] Sets
- [ ] Comparators
- [ ] dead methods and classes

## Long Term

- [ ] explicitly carry object types everywhere
- [ ] split void type and unknown type
- [ ] loop invariants detector

## Limitations

-X cpg does not seem to work with for without { }.
-X cpg does not seem to work with multiple declarations in one line (int x, y;).
