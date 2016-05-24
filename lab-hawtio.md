# Lab Hawtio

---

## Goal

The goal is to understand:

-	Which JMX information is published by ServiceMix?
- Understand how Hawtio improves the presentation of JMX information that is also viewable in jConsole & Jolokia.

## Exercise

###	Prerequisites

Item       | Value
---------- | ---
%SMX_HOME% | Location of ServiceMix installation

### Run jconsole

- Connect to **org.apache.karaf.main.Main**
- Open **MBeans**
- Check through the JMX information for **org.apache.camel**. 

|     |
| --- |
| Q: Can you see any Camel routes? |
| A: See **org.apache.camel > routes** |
| |
| Q: Can you see how many messages got processed in route **getPerson**? |
| A: See **org.apache.camel > routes > camel-cxf-reset-service > getPerson > Attributes > ExchangesCompleted ** |
| |

- Run a **GET** (see [Run curl GET](#run-get)). Did you notice the changed JMX counter **ExchangesTotal**?

### Install & Run Jolokia

#### ServiceMix - Install Jolokia
```
bundle:install -s mvn:org.jolokia/jolokia-osgi-bundle
bundle:list
http:list
```

#### Firefox - Check that service is alive
```
localhost:8181/cxf
```

#### curl - Request JMX information
```
curl -X GET http://localhost:8181/jolokia/read/java.lang:type=Memory/HeapMemoryUsage
```

### Install & Run Hawtio

#### ServiceMix - Clean restart

Make a clean restart in order to get a simpler view in Hawtio.
```
cd %SMX_HOME%
bin/stop
bin/start clean
bin/client
```

#### ServiceMix - Install Hawtio
```
feature:repo-add hawtio 1.4.60
feature:install hawtio-core
bundle:list
http:list
```

#### Firefox - Run Hawtio
```
localhost:8181/hawtio
```

- Checkout the **JMX** and **OSGi** categories.
- Did you notice that there is no category **Camel**?
- Install the **CAMEL CXF REST** example again:
```
feature:install examples-camel-cxf-rest
```
- Do you see category **Camel** now?
- Check out **Camel Contexts**. Do you see the counters?
- Run a **GET** (see [Run curl GET](#run-get)). Did you notice any changed counter?
- Check out **Camel Contexts > camel-2 > Routes > Route Diagram**.
- Check out **Camel Contexts > camel-2 > Routes > putPerson > Route Diagram**.
- Start debugging: **Debug > Add Breakpoint**. Run a **POST** (see [Run curl POST](#run-post)). Can you view the message headers & body?

### Run manual curl commands

#### Firefox - check that service is alive
```
localhost:8181/cxf
```
- Q: On which port is the REST service available?
- A: 8989

#### Run GET
```
curl -v -X GET 127.0.0.1:8989/rest/personservice/person/get/1
```
- Q: What HTTP response code did it return?
- A: 404 Not Found

#### Prepare POST file smx_post.xml
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<person>
    <age>21</age>
    <id>1</id>
    <name>John Smith</name>
</person>
```

#### Run POST
```
curl -v -X POST 127.0.0.1:8989/rest/personservice/person/post -H "Content-Type: application/xml" -T smx_post.xml
```
- Q: What HTTP response code did it return?
- A: 201 Created

#### Run 2nd GET (see above)
- Q: What HTTP response code did it return?
- A: 200 OK

#### Run DELETE
```
curl -v -X DELETE 127.0.0.1:8989/rest/personservice/person/delete/1
```
- Q: What HTTP response code did it return?
- A: 200 OK

#### Run 2nd DELETE
```
curl -v -X DELETE 127.0.0.1:8989/rest/personservice/person/delete/1
```
- Q: What HTTP response code did it return?
- A: 404 Not Found
