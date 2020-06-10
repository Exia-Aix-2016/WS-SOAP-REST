# Ws-SOAP-REST
Prosit Java EE SOAP AND REST
Workshop CESI about Java persistence API


All instruction are in tutoriel_DAD_trad_v2 Tuteur et Etudiants

## II. Requirement
### Distribution
Ubuntu version 20+
### OpenJDK

Version required: 8

```
sudo apt update
sudo apt install openjdk-8-jdk
```
### Maven
```
sudo apt install maven
```
## Setup Payara
### Get Payara
Follow the link to download the version needed for the project
https://search.maven.org/artifact/fish.payara.distributions/payara/5.191/zip

Unzip the file where you want, that's it, it's installed !


### Environment variables

Edit ~/.profile to add at the bottom of the file this :

```sh
export M2_HOME="/usr/share/maven"
GLASSFISH_HOME="<your path>/payara5"
export PATH="$PATH:$GLASSFISH_HOME/bin"
```
change "your path" by where your payara folder is



### Configure Payara
First of all to execute command on payara you must go into ./payara/bin/
and execute asadmin script

#### Start and stop
```
asadmin start-domain
asadmin stop-domain
```
To access to the admin console go to **localhost:$$$$**

$$$$ : admin port, will be different depending the base domain port
#### About Domain
For this workshop you can use the default domain, domain1 if you have some errors,
but you'll have to create bank, store and middleware domain, follow the instruction

#### Configure pool connection (bdd)
To configure the pool connection to enable payara to connect to mysql bdd type this command :

```
asadmin add-resources ./appli/bookStore/src/main/setup/glassfish-resources.xml
```
If after that your unable to connect, you can check in the admin console