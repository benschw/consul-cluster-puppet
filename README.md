## Consul / Spring Boot
- Provision Consul cluster, Spring Boot application nodes, Sensu cluster with Puppet
- Register and discover Spring Boot microservices with Consul
- Wire up Spring Boot Actuator health checks with Consul and Sensu
- Integrate Codahale metrics with Spring Boot Actuator
- Emit metrics about Consul DNS lookups (timing, failures, etc.)
- _**todo**_Aggregate metrics into Graphite with Sensu


### links once it's up
- Dashboards
	- [consul](http://172.20.20.12:8500/ui/#/dc1/services)
	- [sensu](http://172.20.20.17:3000/#/events)
- Demo
	- [demo](http://172.20.20.13:8080/demo)
	- [demo health](http://172.20.20.13:8081/health)
	- [demo metrics](http://172.20.20.13:8081/metrics)