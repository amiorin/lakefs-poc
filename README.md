# Intro
In this POC we are overriding the default S3 signer (AWSS3V4Signer) with LakefsSigner. LakefsSigner will use the AWSCredentials to retrieve new AWSCredentials of Lakefs that are specific to the path we want to read or write.

```sh
cd signer
zaws login --awsprofile saiki saiki PowerUser
mvn exec:java -Dexec.mainClass="lakefs.App"
```