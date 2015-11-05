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
| 002 | CBO | Coupling Between Objects |  |  |
| 003 | CYCLO | McCabe’s CYCLOmatic complexity |  |  |
| 004 | DDP |  |  |  |
| 005 | FDP | Foreign Data Providers | The number of distinct classes in which the attributes accessed in cf. with the ATFD metric are defined |  |
| 006 | FEW |  |  |  |
| 007 | LAA | Locality of Attribute Accesses | The number of attributes from the method’s definition class, divided by the total number of variables accessed (including attributes used via accessor methods, see ATFD metric) |  |
| 008 | LCOM | Lack Of Cohesion between Methods |  |  |
| 009 | LOC | Lines Of Code |  |  |
| 010 | LOC_CLASS | Lines Of Code in a class |  |![progress](http://progressed.io/bar/100?title=neo)   |
| 011 | LOC_METHOD | Lines Of Code in a method |  |  |
| 012 | LOCACTUAL | total Lines Of Code |  |  |
| 013 | LOCPROB |  | number of Lines Of Code for data fields, methods, imported packages, and package declaration |  |
| 014 | MAXNESTING | MAXimum NESTING level |  |  |
| 015 | NACC | Number of ACCessors (getter/setter) |  |  |
| 016 | NAD | Number of Attributes in a class? |  |  |
| 017 | NOA | Number Of Attributes |  |  |
| 018 | NOAM | Number Of Accessor Methods |  |  |
| 019 | NOAV | Number Of Accessd Variables |  |  |
| 020 | NOM | Number Of Methods | Don't measured for Abstract methods, Interface, and inner classes? | ![progress](http://progressed.io/bar/100?title=lht) |
| 021 | NOP | Number Of Properties? |  |  |
| 022 | NOP* | Number Of Parameters |  |  |
| 023 | NOPA | Number Of Public Attributes | The Number of Public Attributes, which are not static and constant, of a class. Don't measured for Interface, and inner classes. | ![progress](http://progressed.io/bar/100?title=lht) |
| 024 | NOV | Number Of Variables per class |  |  |
| 025 | PAR | total number of PARameters in the selected scope |  |  |
| 026 | TCC | Tight Class Cohesion | the relative number of method pairs of a class that access in common at least one attribute of the measured class |  |
| 027 | WMC | Weighted Methods per Class |  the sum of the statical complexity of all methods in a class. The CYCLO metric is used to quantify the method’s complexity |  |
| 028 | WOC | Weight Of a Class | The number of ”functional” public methods, divided by the total number of public members |  |