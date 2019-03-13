# Infinite Technology ∞ Carburetor 🌀
Parameterized Groovy AST method Transformer

> **`Carburetor`** is a device that mixes air and fuel for internal combustion engines in the proper air–fuel ratio for combustion.<br/>
**`@Carburetor`**-based annotations are mixing the auto-generated "infrastructural" code with the user code - thus its name.

References:

- **[Carburetor documentation](https://github.com/INFINITE-TECHNOLOGY/CARBURETOR/wiki)**

|Attribute\Release type|Latest|Stable|
|----------------------|------|------|
|Version|4.0.0-SNAPSHOT|3.0.x|
|Branch|[master](https://github.com/INFINITE-TECHNOLOGY/CARBURETOR)|[CARBURETOR_3_0_X](https://github.com/INFINITE-TECHNOLOGY/CARBURETOR/tree/CARBURETOR_3_0_X)|
|CI Build status|[![Build Status](https://travis-ci.com/INFINITE-TECHNOLOGY/CARBURETOR.svg?branch=master)](https://travis-ci.com/INFINITE-TECHNOLOGY/CARBURETOR)|[![Build Status](https://travis-ci.com/INFINITE-TECHNOLOGY/CARBURETOR.svg?branch=CARBURETOR_3_0_X)](https://travis-ci.com/INFINITE-TECHNOLOGY/CARBURETOR)|
|Test coverage|[![codecov](https://codecov.io/gh/INFINITE-TECHNOLOGY/CARBURETOR/branch/master/graphs/badge.svg)](https://codecov.io/gh/INFINITE-TECHNOLOGY/CARBURETOR/branch/master/graphs)|[![codecov](https://codecov.io/gh/INFINITE-TECHNOLOGY/CARBURETOR/branch/CARBURETOR_3_0_X/graphs/badge.svg)](https://codecov.io/gh/INFINITE-TECHNOLOGY/CARBURETOR/branch/CARBURETOR_3_0_X/graphs)|
|Library (Maven)|[oss.jfrog.org snapshot](https://oss.jfrog.org/artifactory/webapp/#/artifacts/browse/tree/General/oss-snapshot-local/io/infinite/carburetor/4.0.0-SNAPSHOT)|[ ![Download](https://api.bintray.com/packages/infinite-technology/m2/carburetor/images/download.svg) ](https://bintray.com/infinite-technology/m2/carburetor/_latestVersion)|

## Purpose

> ❗ `Carburetor` is not an end-user solution. It is a basis for other end-user solutions (such as [Blackbox](https://github.com/INFINITE-TECHNOLOGY/BLACKBOX)) providing compile-time code transformation implementation and a runtime API.

Carburetor provides a foundation for other libraries to automatically generate Groovy Semantic handling code based on Carburetor configuration and inject it into User code during the Compilation stage resulting in a possibility to intercept and interact with application run-time events including their corresponding compile-time metadata (class name, method name, line start, line end, column start, column end, ASTNode class name):

- Method/constructor Start
- Method Result return
- Method/constructor End
- Method/constructor Exception
- Statement Start
- Statement Start
- Statement End
- Control Statements (return, break, continue, throw)
- Expression evaluation Start
- Expression evaluation Result
- Expression evaluation Exception
- Expression evaluation End

## In short

Carburetor-based annotations inject a lot of implementation-specific infrastructure code (such as logging, profiling, security, etc) without affecting the user program logic.

Granularity of injected code can be defined by the user (programmer) up to:
- Method Exceptions handling (exception and causing method arguments are handled)
- Method invocation handling (method arguments and result are handled)
- Statement-level handling
- Expression-level handling
