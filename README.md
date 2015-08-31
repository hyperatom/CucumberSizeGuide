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

### Local Development

If you are developing locally, you can install Java 7 and Maven on your developer machine or use Vagrant and Ansible
to spin up a virtual machine to host the project.

Simply run `vagrant up` from the command line at the root of this project.

To start the Node webserver, ssh into the vagrant box `vagrant ssh`, change to the project directory
`cd /var/www/SizeGuideTester/web` and run the `grunt` command. 

You should now be able to access the web application by visiting [192.168.50.51:9090](http://192.168.50.51:9090) 
from your web browser.

Start the tests by running `mvn test` from the root of the project within the vagrant box.

### Remote Deployment

First set up an Ubuntu server with a sudo user. Change the IP address and `ansible_ssh_user` of the `production` 
host to reflect your server in the `provisioning/ansible_hosts` file.

Add your public key to the `~/.ssh/authorized_hosts` file when logged in a your sudo user.

Execute the following command from the root of this project to provision the server:

`ansible-playbook -i provisioning/ansible_hosts --limit production provisioning/playbook.yml`

A Jenkins job will be be responsible for executing the `mvn test` command from the root 
of the project. This should be run as a timed job each Sunday so the reports will be ready for Monday.

Another job will be responsible for building and deploying code.

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

## Development Requirements

Ansible 1.9 is required to provision the server.

## Server Requirements

An Ubuntu server should be used to run this tool and should always be turned on since a constantly running
Node web server is required to serve the AngularJS application.

* Ubuntu
* Java 1.7
* Maven 3.2
* Node 0.12

## Jenkins Jobs

The size guide checker should be run on a remote server each Sunday as a timed job in Jenkins.
The job should execute the `mvn test` command from the root of this project.

This will be a long running job since the shell will exit when the tests have completed.
Four clothing categories (e.g. lingerie, men, women, kids) will take approximately 5 hours to test.
