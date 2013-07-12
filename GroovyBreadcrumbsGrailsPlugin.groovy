import org.springframework.aop.scope.ScopedProxyFactoryBean

class GroovyBreadcrumbsGrailsPlugin {
	def version = "1.0"
	def grailsVersion = "2.0 > *"
	def pluginExcludes = [
		"grails-app/views/error.gsp",
		"grails-app/views/index.gsp",
		"grails-app/views/breadcrumbsDemo/**",
		"grails-app/controllers/breadcrumbs/plugins/**",
		"grails-app/services/breadcrumbs/plugins/**",
		"grails-app/i18n/**"
	]

	def title = "Groovy Breadcrumbs Plugin for grails"
	def author = "Cyrilkiller"
	def authorEmail = ""
	def description = 'Provide a breadcrumbs based on a menu definition service'

	def documentation = "http://grails.org/plugin/breadcrumbs-plugins"

	def license = 'APACHE'
	def issueManagement = [system: 'GITHUB', url: 'https://github.com/cyrilkiller/groovy-breadcrumbs-plugin/issues']
	def scm = [url: 'https://github.com/cyrilkiller/groovy-breadcrumbs-plugin']

	def doWithSpring = {
		breadCrumbsServiceProxy(ScopedProxyFactoryBean) {
			targetBeanName = 'breadCrumbsService'
			proxyTargetClass = true
		}
	}
}
