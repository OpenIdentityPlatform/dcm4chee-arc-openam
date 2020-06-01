# dcm4chee-arc-openam
Example Docker cofiguration files for secure [dcm4chee-arc-light](https://github.com/dcm4che/dcm4chee-arc-light) application with OpenAM and OpenIG

## Quick Start

### Prepare hosts file:
Add the following entries to the hosts file:
127.0.0.1  ext.openam.org.ru

### Clone and run repostory:
```
$ git clone https://github.com/OpenIdentityPlatform/dcm4chee-arc-openam.git
$ cd dcm4chee-arc-openam
$ docker-compose up
```

Docker compose will create services

1. `openam` - [OpenAM](https://github.com/OpenIdentityPlatform/OpenAM) - based authentication service
1. `opendj` - configuration and user data store on [OpenDJ](https://github.com/OpenIdentityPlatform/OpenDJ)
1. `opendj-arc` - data store for [DCM4CHEE Archive](https://github.com/dcm4che/dcm4chee-arc-light)
1. `openig` -  gateway to protect access to DCM4CHEE Archive based on [OpenIG](https://github.com/OpenIdentityPlatform/OpenIG)
1. `db` - PosgreSQL database for DCM4CHEE Archive
1. `arc` - DCM4CHEE Archive service

Open DCM4CHEE Archive application in a browser: [http://ext.openam.org.ru:8080/dcm4chee-arc/ui2](http://ext.openam.org.ru:8080/dcm4chee-arc/ui2)

Username: ivan, password: 11111111

To access OpenAM admin console open the following url in a browser:
[http://ext.openam.org.ru:8080/openam/UI/Login](http://ext.openam.org.ru:8080/openam/UI/Login)

Username: amadmin, password ampassword

