# NeutrinoAPI SDK

Neutrino API client for Java using Apache

| Feature          |           |
|------------------|-----------|
| Language         | Java      |
| Platform Version | 11        |
| External Deps    | Yes       |
| HTTP Library     | Apache    |
| HTTP/2           | No        |
| TLS Version      | 1.3       |

## Getting started

First you will need a user ID and API key pair: [SignUp](https://www.neutrinoapi.com/signup/)

## To Build 
```sh
$ mvn clean install
```

## To Initialize 
```java
NeutrinoAPI neutrinoAPI = new NeutrinoAPI("<your-user-id>", "<your-api-key>");
```

## Running Examples

```sh
$ mvn compile exec:java \
-Dfile.encoding=UTF-8 \
-Dexec.mainClass=com.neutrinoapi.examples.EmailValidate
```

You can find examples of all APIs in _src/main/java/com/neutrinoapi/examples_

## For Support 
[Contact us](https://www.neutrinoapi.com/contact-us/)