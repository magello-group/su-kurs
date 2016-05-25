Karaf
=====

# Training - Part 1

* Start karaf as a background process and figure out what the username, password and port for the Karaf console is and then connect using SSH. (TIPS: etc!)

# Training - Part 2

* Drag the blueprint file from KarafTraining1.xml to <smx>/deploy
* Look in the log using log:tail and see the message
* Copy the cfg-file to the correct location, and restart the bundle
* Check the log again
* Set the configuration using the karaf console
* Restart the bundle
* Check the log again
* When done, uninstall the bundle

# Training - Part 3

* Generate blueprint and spring-dm bundles using maven artifacts
* mvn archetype:generate -DarchetypeGroupId=org.apache.camel.archetypes -DarchetypeVersion=2.16.1  -DarchetypeArtifactId=camel-archetype-blueprint
* Replace camel-archetype-blueprint with camel-archetype-spring-dm for spring-dm
* installera i Karaf med bundle:install mvn:[group]/[id]/[version]
