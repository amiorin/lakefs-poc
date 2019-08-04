# Intro
In this POC we are overriding the default S3 signer (AWSS3V4Signer) with LakefsSigner. LakefsSigner will use the AWSCredentials to retrieve new AWSCredentials of Lakefs that are specific to the path we want to read or write.

# AWS SDK Java POC
```sh
cd signer
zaws login --awsprofile saiki saiki PowerUser
mvn exec:java -Dexec.mainClass="lakefs.App"
```

# Presto POC
```sh
cd presto
zaws login --awsprofile saiki-dev saiki-dev PowerUser
# copy the temporary credentials to lakefs.App.java
mvn exec:java -Dexec.mainClass="lakefs.App"
```
http://elkhandadashov.com/2017/04/23/Recursively-Retrieving-Files-from-HDFS-via-Java-API/

# Spark POC
TODO