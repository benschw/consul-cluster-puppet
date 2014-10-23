## Consul / Spring Boot
This is the example stack for a 2 part blog post I wrote:

- [Provisioning Consul with Puppet](http://txt.fliglio.com/2014/10/consul-with-puppet/)
- [Consul, Spring Boot, Actuator, and Codahale Metrics](http://txt.fliglio.com/2014/10/spring-boot-actuator/)


- Provision Consul cluster, Spring Boot application nodes
- Register and discover Spring Boot microservices with Consul
- Wire up Spring Boot Actuator health checks with Consul
- Integrate Codahale metrics with Spring Boot Actuator
- Emit metrics about Consul DNS lookups (timing, failures, etc.)

### Usage

run `deps.sh` to grab puppet dependencies, `build.sh` to build the java app, then `vagrant up`

	./deps.sh
	./build.sh
	vagrant up

- deps.sh will pull in puppet module dependencies
- build.sh builds the demo spring boot app: (just runs ./gradlew build)

### links (once it's up)
- Dashboard
	- [consul](http://172.20.20.13:8500/ui/#/dc1/services)
- Demo
	- [demo](http://172.20.20.20:8080/demo)
	- [demo health](http://172.20.20.20:8081/health)
	- [demo metrics](http://172.20.20.20:8081/metrics)

