# assembler-hack

This is an assembler for a virtual Hack computer from https://www.nand2tetris.org.
The assembler specification is [here](https://b1391bd6-da3d-477d-8c01-38cdf774495a.filesusr.com/ugd/44046b_89a8e226476741a3b7c5204575b8a0b2.pdf). 

## Motivation

In this project I implemented a simple assembler for a simple CPU to refine 
my knowledge of low-level software.

## Getting Started

### Prerequisites
- Install Java SDK 25+.
- Install [Maven](https://maven.apache.org/index.html).
- Create `~/.m2/toolchains.xml` pointing to your Java SDK. The file example:
```text
<?xml version="1.0" encoding="UTF-8"?>
<toolchains>
  <toolchain>
    <type>jdk</type>
    <provides>
      <version>25</version>
      <vendor>any</vendor>
    </provides>
    <configuration>
      <jdkHome>/path/to/openjdk-25.0.1</jdkHome>
    </configuration>
  </toolchain>
</toolchains>
```
Read about toolchains in greater detail [here](https://maven.apache.org/plugins/maven-toolchains-plugin/index.html).

### Installation
```shell
mvn compile
```

### Run
1. Prepare your assembler code. For example, [Add.asm](./examples/Add.asm).
2. Translate your assembler code into a binary code:
```shell
mvn exec:java -Dexec.args="examples/Add.asm"
```
This command creates a file `examples/Add.hack`, containing the binary code.