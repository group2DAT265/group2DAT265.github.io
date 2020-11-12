# JabRef - Group2 DAT265

## Individual Contribution and Group Report

Our individual contribution can be found in the Appendix in this document.
[https://docs.google.com/document/d/12XRkVkrnM08bRQg0eBXS33Lk4u0lWqmr9tMrsbtTAZ4/edit#heading=h.u8zeh9pjy074](https://docs.google.com/document/d/12XRkVkrnM08bRQg0eBXS33Lk4u0lWqmr9tMrsbtTAZ4/edit#heading=h.u8zeh9pjy074)

## CI/CD Documentation

Currently there are 5 differernt pipelines running

- Builing the system using ./gradlew build
- Running tests and checkstyle
- Scanning the code for static analysis for SonarQube
- Calculating Code coverage and posting to Codecov
- Creating Doxygen documentation with Gaphviz diagrams

### Only on master

The following pipelines run only on succesful pushes to the master branch:

- Creating Doxygen documentation with Gaphviz diagrams
- Scanning the code for static analysis for SonarQube

The doxygen documenation can be found [here](https://group2dat265.github.io/)

The SonarQube can be found [here](http://157.230.19.67:9000/dashboard?id=dat265group2)

### On all pushes and pull requests

The following pipelines run on a push to any branch and all pull requests:

- Builing the system using ./gradlew build
- Running tests and checkstyle
- Calculating Code coverage and posting to Codecov

The status of the project builing and the tests and checkstyle can be found under ach action in the [Github Actions tab](https://github.com/group2DAT265/group2DAT265.github.io/actions). Go to the action you want to look and and read the logs.

The test and checkstyle pipeline run the following steps:

- Checkstyle (Checks the formatting of the code according to the Java Coding Conventions)
- Unit tests (All tests that are not database, fetching or GUI)
- Database tests (Tests using Postgres and MySQL databases)
- GUI tests (Runs tests for the GUI)
- Fetcher tests (Run tests that fetch data from API endpoints)

The Codecoverage of codecov can be found [here](https://codecov.io/gh/group2DAT265/group2DAT265.github.io)
