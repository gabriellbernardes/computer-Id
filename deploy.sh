

mvn verify -P stage

../wildfly-23.0.2.Final/bin/jboss-cli.sh -c controller=localhost:7990 --user=admin --password=admin --command="deploy ./target/jobs.war --force"