Camel Java Router Project
=========================

To build this project use

    mvn clean install

# Training - Part 1

* Download the code
* Read the code and understand what it does
* Copy the CamelTraining1 directory under data/ to the correct location
* Build using maven and install using the karaf console
* Install using mvn: notation
* Make sure it works as intended

# Training - Part 2

* The camel route does nothing interesting – let’s beef it up!
* Create a content based router, and move the message to out/good or out/bad based on the school field
* Use xpath to parse the xml

# Training - Part 3

* Now rewrite the route using XML and blueprint
* Use the blueprint archetype to create the project
* mvn archetype:generate -DarchetypeGroupId=org.apache.camel.archetypes -DarchetypeVersion=2.16.1 -DarchetypeArtifactId=camel-archetype-blueprint -DgroupId=se.oru.blue -DartifactId=school-choice -Dversion=1.0.0-SNAPSHOT
