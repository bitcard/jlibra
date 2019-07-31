# jlibra
[![CircleCI](https://circleci.com/gh/ketola/jlibra.svg?style=svg)](https://circleci.com/gh/ketola/jlibra)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/dd682f23555c48aca137eb4c657d9497)](https://www.codacy.com/app/ketola/jlibra?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ketola/jlibra&amp;utm_campaign=Badge_Grade)
 
A Java library for interacting with the Libra blockchain

![Overview](docs/img/jlibra.png)

# Prerequisites

* JDK 8+
* Maven 3+

# Setup

* Clone this repo
* Build project with `mvn install`

# Usage

Start sample Main classes in `dev.jlibra.example` package for examples

[`GenerateKeysExample`](jlibra-examples/src/main/java/dev/jlibra/example/GenerateKeysExample.java)

[`GetAccountStateExample`](jlibra-examples/src/main/java/dev/jlibra/example/GetAccountStateExample.java)

[`GetAccountTransactionBySequenceNumberExample`](jlibra-examples/src/main/java/dev/jlibra/example/GetAccountTransactionBySequenceNumberExample.java)

[`ImportAccountMnemonicExample`](jlibra-examples/src/main/java/dev/jlibra/example/ImportAccountMnemonicExample.java)

[`MintExample`](jlibra-examples/src/main/java/dev/jlibra/example/MintExample.java)

[`TransferExample`](jlibra-examples/src/main/java/dev/jlibra/example/TransferExample.java)

# How-Tos & Step-by-Step Guides

How-Tos and Step-by-Step Guides are gathered in a [separate document](docs/HOWTO.md).

# Known Issues

**Transaction is not executed, but without errors (no events, no transaction in librabrowser.io)**

* The execution of the example main classes might terminate before the actual action is performed. 
  * To prevent this, add `Thread.sleep(5000)` after the last statement of the example.
* You might have specified too few gas. Try increasing gas usage.
