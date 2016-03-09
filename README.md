# spring-async

maven, tomcat 7, spring mvc, async, siege
========
> summary of the base tool used.

server.xml config
========
> server.xml for tomcat7, use Http11NioProtocol for the protocol of the connector, thread pool limits wihtin 50 for load test.

load test: siege
=======
> siege -c 200 -b -t2 -f sync_url.txt
