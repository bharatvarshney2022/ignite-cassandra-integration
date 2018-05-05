# ignite-cassandra-integration
ignite-cassandra-integration

The purpose of this Project repository is to embed apache ignite on top of cassandra.
The Read/Write Operations will be faster and moreover it will have some enhanced features
which will be covered in next releases.
For fetching the real time data Twitter Stream API is used. 
Hence have the feature of both aggressively mounting data as well as huge batch size.


Steps to build the project:
1. Install java 8 and above version.
2. Install apache-ignite with the 2..3.0 version and set the IGNITE_HOME.
3. Install cassandra  3.11 release and set it inn classpath.
4. Inside the project cassandra default host and port are "127.0.0.1", 9042 respectively.
5. To Set the configuration of the apache ignite, apacheignite-cassandra.xml XML file needs to 
   be changed or modified.
6. Twitter API Authorization: For authentication purpose twitter.properties is used.
	These are the with the default parameter. It needs to change based on the project configuration
7. Prepare the keystore and tables on cassandra. To do it run the DBScript.txt  script file on cassandra.
	
8. Build: To build the project, run the below command(Maven must be set at classpath before execution)
    C:/>mvn clean install -Dmaven.test.skip=true		
	
9. After the completion of the build. Execute the below command to run the application.
    C:/>cd target
	C:/>java -jar nextgen-ignite-nosql-integration-0.0.1-SNAPSHOT.jar 

10. After running the command the Application must be Up.
11. To Test the application, hit the following URL 

		Select the data using Ignite Query
		http://{host}:{port}/datawarehouse/fetch/cassandra/ignite/
		
		Select the data with specific id using Ignite Query
		http://{host}:{port}/datawarehouse/fetch/cassandra/ignite/{id}
		
		Select the data using Ignite Query
		http://{host}:{port}/datawarehouse/fetch/cassandra/
		
		Select the data with specific id using Ignite Query
		http://{host}:{port}/datawarehouse/fetch/cassandra/id}
		
		Insert the data into the cassandra using ignite
		http://{host}:{port}/datawarehouse/put/cassandra/ignite/{id}/{message}/
