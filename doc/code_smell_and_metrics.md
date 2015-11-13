## Code Smells and Source Code Metrics

* Data Class: NACC, NOV, LOCPROB, LOCACTUAL, WOC, NOPA, NOAM(NACC), WMC
* Large Class: NAD, NDM, LOC\_CLASS, NOP, WMC, NOM, NOA, TCC, ATFD
* Feature Envy: CBO (CA, CE), LCOM, ATFD, LAA, FDP, FEW, DDP
* Long Method: LOC\_METHOD, CYCLO, MAXNESTING, NOAV
* Data Clumps: Defect density
* Lazy Class: NOM, WMC, LOC, CBO, NOP, CYCLO, NAD
* Long Parameter List: PAR, NOP*

## Metrics Names

| NO | Metric Label | Metric Name | Description | Progress |
|:-----|:-----|:-----|:------|:-----|
| 001 | ATFD | Access To Foreign Data | The number of attributes from unrelated classes accessed directly or by invoking accessor methods | ![progress](http://progressed.io/bar/5?title=lht) |
| 002 | CBO | Coupling Between Objects | The coupling between object classes (CBO) metric represents the number of classes coupled to a given class (efferent couplings and afferent couplings). This coupling can occur through method calls, field accesses, inheritance, arguments, return types, and exceptions. | ![progress](http://progressed.io/bar/100?title=neo) |
| 003 | CYCLO | McCabe’s CYCLOmatic complexity |  | ![progress](http://progressed.io/bar/100?title=neo) |
| 004 | DDP |  |  |  |
| 005 | FDP | Foreign Data Providers | The number of distinct classes in which the attributes accessed in cf. with the ATFD metric are defined |  |
| 006 | FEW |  |  |  |
| 007 | LAA | Locality of Attribute Accesses | The number of attributes from the method’s definition class, divided by the total number of variables accessed (including attributes used via accessor methods, see ATFD metric) |  |
| 008 | LCOM | Lack Of Cohesion between Methods | A class's lack of cohesion in methods (LCOM) metric counts the sets of methods in a class that are not related through the sharing of some of the class's fields. The original definition of this metric (which is the one used in ckjm) considers all pairs of a class's methods. In some of these pairs both methods access at least one common field of the class, while in other pairs the two methods to not share any common field accesses. The lack of cohesion in methods is then calculated by subtracting from the number of method pairs that don't share a field access the number of method pairs that do. Note that subsequent definitions of this metric used as a measurement basis the number of disjoint graph components of the class's methods. Others modified the definition of connectedness to include calls between the methods of the class. The program ckjm follows the original (1994) definition by Chidamber and Kemerer. |  ![progress](http://progressed.io/bar/100?title=neo) |
| 009 | LOC | Lines Of Code | The lines are counted from java binary code and it is the sum of number of fields, number of methods and number of instructions in every method of given class. | ![progress](http://progressed.io/bar/100?title=neo)  |
| 010 | LOC_CLASS | Lines Of Code in a class |  |![progress](http://progressed.io/bar/100?title=neo)   |
| 011 | LOC_METHOD | Lines Of Code in a method |  | ![progress](http://progressed.io/bar/100?title=neo) |
| 012 | LOCACTUAL | total Lines Of Code |  | ![progress](http://progressed.io/bar/100?title=neo)  |
| 013 | LOCPROB |  | number of Lines Of Code for data fields, methods, imported packages, and package declaration |  |
| 014 | MAXNESTING | MAXimum NESTING level |  | ![progress](http://progressed.io/bar/100?title=lht) |
| 015 | NACC | Number of ACCessors (getter/setter) |  |  |
| 016 | NAD | Number of Attributes in a class? |  | NOA |
| 017 | NOA | Number Of Attributes |  |  ![progress](http://progressed.io/bar/100?title=neo)  |
| 018 | NOAM | Number Of Accessor Methods | The number of accessor (getter and setter) methods of a class. | ![progress](http://progressed.io/bar/100?title=lht) |
| 019 | NOAV | Number Of Accessd Variables | The total number of variables accessed directly or through accessor methods from the measured
operation. Variables include parameters, local variables, but also instance variables and global
variables declared in classes belonging to the system. |  |
| 020 | NOM | Number Of Methods | Don't measured for Abstract methods, Interface, and inner classes? | ![progress](http://progressed.io/bar/100?title=lht) |
| 021 | NOP | Number Of Properties? |  |  |
| 022 | NOP* | Number Of Parameters |  |  |
| 023 | NOPA | Number Of Public Attributes | The Number of Public Attributes, which are not static and constant, of a class. Don't measured for Interface, and inner classes. | ![progress](http://progressed.io/bar/100?title=lht) |
| 024 | NOV | Number Of Variables per class |  |  |
| 025 | PAR | total number of PARameters in the selected scope |  |  |
| 026 | TCC | Tight Class Cohesion | the relative number of method pairs of a class that access in common at least one attribute of the measured class |  |
| 027 | WMC | Weighted Methods per Class | A class's weighted methods per class WMC metric is simply the sum of the complexities of its methods. As a measure of complexity we can use the cyclomatic complexity, or we can abritrarily assign a complexity value of 1 to each method. The ckjm program assigns a complexity value of 1 to each method, and therefore the value of the WMC is equal to the number of methods in the class. |  ![progress](http://progressed.io/bar/100?title=neo) |
| 028 | WOC | Weight Of a Class | The number of ”functional” public methods, divided by the total number of public members |  |
