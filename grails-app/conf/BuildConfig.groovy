grails.project.work.dir = 'target'

grails.project.dependency.resolution = {

	inherits 'global'
	log 'warn'

	repositories {
		grailsCentral()
		mavenLocal()
		mavenCentral()
	}

	dependencies {
		test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
		test "org.seleniumhq.selenium:selenium-support:2.31.0"
		test "org.seleniumhq.selenium:selenium-firefox-driver:2.31.0"

		test "org.gebish:geb-spock:0.9.0-RC-1"
	}

	plugins {
		build ':release:2.2.1', ':rest-client-builder:1.0.3', {
			excludes 'xml-apis', 'xmlParserAPIs'
			export = false
		}

		build(":tomcat:$grailsVersion") {
			export = false
		}

	   test(":spock:0.7") {
		   exclude "spock-grails-support"
	   }
	   test ":geb:0.9.0-RC-1"

	   runtime ":resources:1.2"
	   compile ":lesscss-resources:1.3.1"
	   compile ":remote-control:1.4"
	}
}
