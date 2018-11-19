# WebTests

This repo houses functional tests for a website.
The tests are, UI-driven, automated tests using gradle, jUnit, selenium, java.

- [Getting the code](#getting-the-code)
- [Compiling](#compiling)
- [Running Tests](#running-tests)
- [Test Results](#test-results)

# Prerequisites
Mac OS 10.0x or up

- Git

Windows 7 or up
- Git
- PowerShell 3.0 or up
- Microsoft .NET Framework 4 or up


# Getting the code

Clone this repo using a graphical git client or with the command line:

- All test class in:
    src/test/java/com/workmarket/webtest/tests
- All page source in:
    src/main/java/com/workmarket/webtest/pages


```
Mac OS:
cd ~
git clone https://github.com/lsecond/webtest.git

Windows:
cd %HOMEPATH%
git clone https://github.com/lsecond/webtest.git
```

Running the install script should install all platform-level dependencies.

Mac OS:
```
cd "~/webtest"
./mac_install.sh
```
Windows:
```
cd "%HOMEPATH%/webtest"
windows_install.bat
```

If you already have the test environment setup and do not want to run the setup script, you just need to
change Chrome driver setup in AbstractTest.java in com.workmarket.webtest.tests.

from line 49:

<pre><code>
if(System.getProperty("os.name").contains("Mac")){
            System.setProperty("webdriver.chrome.driver", <span style="background-color: #FFFF00">"/usr/local/bin/chromedriver"</span>);
            // replace with your chrome driver path
        } else {
            System.setProperty("webdriver.chrome.driver", <span style="background-color: #FFFF00">"C:\\tools\\selenium\\chromedriver.exe"</span>);
            // replace with your chrome driver path
        }
</code></pre>
# Compiling
You can use an IDE to work with the code, for example [IntelliJ](https://www.jetbrains.com/idea/).

Or, use gradle command line

```
gradle clean assemble
```

This compiles the code.

# Running Tests
1. Run all tests 

    ```
    gradle clean test
    ```


2. Run a test class using IntelliJ's run configurations or using:

    ```
    gradle clean test --tests *LoginPageTest
    ```
    
The test will launch the chrome and run certain test. 

# Test Results
If you ran the tests using IntelliJ the results will be visible through the UI.
If you ran them using gradle you can check the report:
```
  webtest/build/reports/tests/test/index.html
```
