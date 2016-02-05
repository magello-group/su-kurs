# Lab apiman

---

## Goal

The goal is to understand:

- Which capabilities does apiman provide?

## Exercise

###	Prerequisites

Item       | Value
---------- | ---
%SMX_HOME% | Location of ServiceMix installation

### Install & Run apiman

Follow Installation & Run instructions under [www.apiman.io][1] for the **WildFly** variant

or simply run:

```
mkdir ~/apiman-1.2.1.Final
cd ~/apiman-1.2.1.Final
curl http://download.jboss.org/wildfly/9.0.2.Final/wildfly-9.0.2.Final.zip -o wildfly-9.0.2.Final.zip
curl http://downloads.jboss.org/apiman/1.2.1.Final/apiman-distro-wildfly9-1.2.1.Final-overlay.zip -o apiman-distro-wildfly9-1.2.1.Final-overlay.zip
unzip wildfly-9.0.2.Final.zip
unzip -o apiman-distro-wildfly9-1.2.1.Final-overlay.zip -d wildfly-9.0.2.Final
cd wildfly-9.0.2.Final
./bin/standalone.sh -c standalone-apiman.xml
```

#### Firefox - Use the apiman UI
```
http://localhost:8080/apimanui/
login: admin/admin123!
```

For User Guide, see [www.apiman.io][2]

##### Create organization

##### Create API

- There are guidance tips what still is missing before the API can get published. Just follow those.

##### Configure the API Endpoint

```
http://localhost:8989/rest
```

##### Set up at least one Plan, or make the API public

- Make it public.

##### Publish the API

##### Determine the Managed Endpoint

```
https://localhost:8443/apiman-gateway/EkklotOrg/EkklotApi/1.0
```

#### Run REST against this managed endpoint

```
curl -k -v -X GET https://localhost:8443/apiman-gateway/EkklotOrg/EkklotApi/1.0/personservice/person/get/1
```

#### Try out apiman

- View Metrics
- Enable Security: Implementation > API Security > Basic Authentication
- Create and publish the modified API
- Add Policy > Quota Policy > Set to 3 per day
- Add Policy > Basic Authentication Policy
  - Identity Source > Static
  - User/Password


  [1]: http://www.apiman.io/latest/download.html
  [2]: http://www.apiman.io/latest/user-guide.html