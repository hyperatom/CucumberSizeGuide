# Automated Size Guide Tester

This tool automates the process of checking for correct size guide content on product description pages.
The tool checks whether the size guide button is present on the page, and if the sizes table is visible
when opening the overlay.

## Stakeholders

Anna Mildwater (Anna.Mildwater@marks-and-spencer.com) is the primary stakeholder for this tool and should be
the first point of contact for non-technical queries.

Alex Bowen (Alex.Bowen@marks-and-spencer.com) is the technical lead for this project and should be contacted
only for technical queries.

## Getting Set Up

First add your public key to the `~/.ssh/authorized_hosts` file when logged in as the `ubuntu` user.

Execute the following command from the root of this project to provision the server:
`ansible-playbook -i provisioning/ansible_hosts provisioning/playbook.yml`

Further modifications may be necessary such as the target host located in the `provisioning/ansible_hosts` file.

A Jenkins job should be responsible for logging into the host and executing the `mvn test` command from the root 
of the project. This should be run as a timed job each Sunday so the reports will be ready for Monday.
Another job will be responsible for building and deploying code to the server.

## Architecture

The tool consists of two main parts; the Java backend which runs Selenium tests and outputs reports in JSON format,
and the AngularJS front-end which displays the JSON reports in a meaningful way.

### Back-End

Currently, a separate JSON report is generated for each category that is tested.

* `web/men-report.json`
* `web/women-report.json`
* `web/kids-report.json`
* `web/lingerie-report.json`

An additional file (`generated.json`) is generated on completion of a test run and contains the current timestamp.

### Front-End

AngularJS is used to present the JSON reports provided by the back-end, in a meaningful way. The report view displays
the results of the errors in tabs where each is a category e.g. men, women, kids, lingerie.

Each category contains two sets of errors, those where the size guide button was not present, and where the sizes
table was not displayed. If a product page fails one of these tests, the page URL will be listed in the respective section.

## Technology Stack

* Java 1.7
* Maven 3.2
* Cucumber
* Selenium
* Node 0.12
* AngularJS

## Server Requirements

An Ubuntu server should be used to run this tool and should always be turned on since a constantly running
Node web server is required to serve the AngularJS application.

* Ubuntu
* Java 1.7
* Maven 3.2
* Node 0.12

## Jenkins Jobs

The size guide checker should be run on a remote server each Sunday as a timed job in Jenkins.
The job should SSH into the server hosting the Maven application and run the `mvn test` command.
This will be a long running job since the shell will exit when the tests have completed.
Four clothing categories (e.g. lingerie, men, women, kids) will take approximately 5 hours to test.
