### Code Smells and Source Code Metrics

---

* Data Class: NACC, NOV, LOCPROB, LOCACTUAL, WOC, NOPA, NOAM(NACC), WMC
* Large Class: NAD, NDM, LOC\_CLASS, NOP, WMC, NOM, NOA, TCC, AFTD
* Feature Envy: CBO (CA, CE), LCOM, ATFD, LAA, FDP, FEW, DDP
* Long Method: LOC\_METHOD, CYCLO, MAXNESTING, NOAV
* Data Clumps: Defect density
* Lazy Class: NOM, WMC, LOC, CBO, NOP, CYCLO, NAD
* Long Parameter List: PAR, NOP*

### Metrics Names

---

* AFTD:
* CBO: coupling between objects
* CYCLO: McCabe’s cyclomatic complexity
* DDP:
* FDP: foreign data providers
* FEW:
* LAA: locality of attribute accesses
* LCOM: lack of cohesion between methods
* LOC: lines of code ![Progress](http://progressed.io/bar/28) 
* LOC_CLASS: lines of code in a class
* LOC_METHOD: lines of code in a method
* LOCACTUAL: total line of code
* LOCPROB: number of line of code for data fields, methods, imported packages, and package declaration
* MAXNESTING:
* NACC: number of accessors (getter/setter)
* NOA: 
* NOAM: number of accessor methods
* NOAV:
* NOP: number of properties
* NOP*: number of parameters
* NOPA: number of public attributes
* NOM: number of methods
* NOV: number of variables per class
* NAD: number of attributes in a class
* NDM: 
* PAR: number of parameters
* TCC:
* WMC: weighted methods per class
* WOC: weight of a class