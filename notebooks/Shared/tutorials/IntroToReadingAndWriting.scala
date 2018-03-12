// Databricks notebook source
// MAGIC %md
// MAGIC # Working with Storage
// MAGIC 
// MAGIC Spark has the ability to read content not only from streaming sources (such as Azure's Event Hubs and IoT Hub), but also from files. Out of the box, Spark supports several file formats, such as `csv`, `json`, `avro`, and `parquet`. Spark also provides the ability for you to work with custom formats.
// MAGIC 
// MAGIC ## Methods for reading and writing
// MAGIC 
// MAGIC Spark provides two general sets of reading & writing methods:
// MAGIC  - `read()`, `write()`, and `save()` - you will use these with static content
// MAGIC  - `readStream()`, `writeStream()`, and `start()` - you will use these with streaming content
// MAGIC  
// MAGIC ## Connecting to Azure
// MAGIC 
// MAGIC When working with content in Azure, you'll first need to configure your Spark session to have a properly-authenticated connection to Azure blob storage. Databricks has fully documented the process, [here](https://docs.databricks.com/spark/latest/data-sources/azure/azure-storage.html), including details about mounting a blob container as a file system mount.
// MAGIC 
// MAGIC Note that, in the Databricks example, they show how to read a parquet file (`spark.read.parquet()`). Just remember that this is one of many built-in formats, and there is no dependency between Azure and a specific file format.
// MAGIC 
// MAGIC If you are working with a Storage account that is in your subscription, then you'll have access to both the account name and account key. With these two parameters, you may configure Azure storage with those two configuration elements:
// MAGIC 
// MAGIC ```
// MAGIC spark.conf.set(
// MAGIC   "fs.azure.account.key.{YOUR STORAGE ACCOUNT NAME}.blob.core.windows.net",
// MAGIC   "{YOUR STORAGE ACCOUNT ACCESS KEY}")
// MAGIC ```
// MAGIC 
// MAGIC However: If someone else is granting you access to a given container (or if you don't want to embed an entire storage account's key within your app), you'll need to use a Shared Access Signature, which is a  key generated for granting access to a given blob or container. To use a SAS, the call is slightly different:
// MAGIC 
// MAGIC ```
// MAGIC spark.conf.set(
// MAGIC   "fs.azure.sas.{YOUR CONTAINER NAME}.{YOUR STORAGE ACCOUNT NAME}.blob.core.windows.net",
// MAGIC   "{COMPLETE QUERY STRING OF YOUR SAS FOR THE CONTAINER}")
// MAGIC ```
// MAGIC 
// MAGIC At this point, you're all set, and can read and write files to Azure blob storage. For example:
// MAGIC 
// MAGIC ```
// MAGIC val df = spark.read.json("wasbs://{YOUR CONTAINER NAME}@{YOUR STORAGE ACCOUNT NAME}.blob.core.windows.net/{YOUR DIRECTORY NAME}/yourfile.json")
// MAGIC ```
// MAGIC 
// MAGIC ## Additional resources
// MAGIC 
// MAGIC  - [Spark data sources](https://docs.databricks.com/spark/latest/data-sources/index.html)