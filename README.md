<<<<<<< HEAD
# Tests Suite Execution

This document provides instructions on running the test suite using Maven from the terminal.

## Prerequisites

Ensure you have the following installed and configured:

- **Java 21.0.6**
- **Maven 3.9.4** (Ensure it is properly set up in your system's environment variables)  //project management tool

Additionally, make sure that all project dependencies and necessary configuration files are correctly set up before running the tests.

## Running the Tests Suite

To execute the test suite using Maven, follow these steps:

1. Open your terminal.
2. Navigate to the root directory of the project where the `pom.xml` file is located.
3. Run the following command:

   ```bash
   mvn clean test -Dfile="xmlsuitefilepath" -DmpNameAndLanguage="meetingplaceNameAndLanguage"
   ```

### Example Commands

#### Running the Web Sanity Suite

For the meeting place **"Mar Shopping Algarve"** (Language: Portuguese, Browser: Chrome):
```bash
mvn clean test -Dfile="sanitysuite/web/SanitySuite.xml" -DmpNameAndLanguage="marShoppingAlgarvePt" -Dbrowser="chrome"
```

For the meeting place **"Mar Shopping Matosinhos"** (Language: Portuguese, Browser: Edge):
```bash
mvn clean test -Dfile="sanitysuite/web/SanitySuiteEdge.xml" -DmpNameAndLanguage="marShoppingMatosinhosPt" -Dbrowser="edge"
```

## Available Meeting Places

Currently, the following meeting places are supported:

1. **Mar Shopping Algarve** (Supports two languages: `pt`, `en`)
2. **Mar Shopping Matosinhos** (Supports one language: `pt`)
3. **Livat Hammersmith** (Supports one language: `en`, with two URLs: `prod` and `testing`)

## Available Tests Suites

The test suite is categorized into three main folders:

1. **Smoke Suite**
    - Includes three XML files:
        - Mobile-size browser tests
        - Full web-size tests for Chrome
        - Full web-size tests for Edge

2. **Sanity Suite**
    - Follows the same structure as the smoke suite.

3. **Regression Suite**
    - Also contains separate XML files for different browser sizes and types.

Each suite is designed to test different aspects of the application:
- **Smoke Suite**: Basic checks to ensure core functionalities work.
- **Sanity Suite**: Focuses on more detailed functional testing.
- **Regression Suite**: Ensures that new changes do not break existing functionality.


=======
# Tests Suite Execution

This document provides instructions on running the test suite using Maven from the terminal.

## Prerequisites

Ensure you have the following installed and configured:

- **Java 21.0.6**
- **Maven 3.9.4** (Ensure it is properly set up in your system's environment variables)  //project management tool

Additionally, make sure that all project dependencies and necessary configuration files are correctly set up before running the tests.

## Running the Tests Suite

To execute the test suite using Maven, follow these steps:

1. Open your terminal.
2. Navigate to the root directory of the project where the `pom.xml` file is located.
3. Run the following command:

   ```bash
   mvn clean test -Dfile="xmlsuitefilepath" -DmpNameAndLanguage="meetingplaceNameAndLanguage"
   ```

### Example Commands

#### Running the Web Sanity Suite

For the meeting place **"Mar Shopping Algarve"** (Language: Portuguese, Browser: Chrome):
```bash
mvn clean test -Dfile="sanitysuite/web/SanitySuite.xml" -DmpNameAndLanguage="marShoppingAlgarvePt" -Dbrowser="chrome"
```

For the meeting place **"Mar Shopping Matosinhos"** (Language: Portuguese, Browser: Edge):
```bash
mvn clean test -Dfile="sanitysuite/web/SanitySuiteEdge.xml" -DmpNameAndLanguage="marShoppingMatosinhosPt" -Dbrowser="edge"
```

## Available Meeting Places

Currently, the following meeting places are supported:

1. **Mar Shopping Algarve** (Supports two languages: `pt`, `en`)
2. **Mar Shopping Matosinhos** (Supports one language: `pt`)
3. **Livat Hammersmith** (Supports one language: `en`, with two URLs: `prod` and `testing`)

## Available Tests Suites

The test suite is categorized into three main folders:

1. **Smoke Suite**
    - Includes three XML files:
        - Mobile-size browser tests
        - Full web-size tests for Chrome
        - Full web-size tests for Edge

2. **Sanity Suite**
    - Follows the same structure as the smoke suite.

3. **Regression Suite**
    - Also contains separate XML files for different browser sizes and types.

Each suite is designed to test different aspects of the application:
- **Smoke Suite**: Basic checks to ensure core functionalities work.
- **Sanity Suite**: Focuses on more detailed functional testing.
- **Regression Suite**: Ensures that new changes do not break existing functionality.


>>>>>>> f87a7e36b3caf97212464a161aa12ab913dcfde2
