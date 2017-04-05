# RSAKeyGenerator
A simple java application to generate RSA keys using random.org API

# Overview
Made with eclipse and Java 8.

[This is I generate an RSA key](https://github.com/unusep/RSAKeyGenerator/blob/master/src/HomeworkRsaGenerator.java)

[This is where I retrieve random numbers](https://github.com/unusep/RSAKeyGenerator/blob/master/src/RandomOrgGen.java)

# Architecture
IRsaGenerator depends on IRandomNumberGenerator (to generate random numbers for it)

# Implementation
## random.org API
Retrieves random numbers from [random.org](https://www.random.org/clients/http/). 

Requests for string of digits and concatenates them naively.

Returns a BigInteger representation of the numbers

## RSA key generator
Requests for 2 random numbers and checks that they are primes using BigInteger's probabilistic API.
