#!/bin/bash
sudo screen java -Djava.net.preferIPv4Stack=true -Djava.rmi.server.hostname=ec2-52-11-90-68.us-west-2.compute.amazonaws.com -jar RMI_MailServer.jar

