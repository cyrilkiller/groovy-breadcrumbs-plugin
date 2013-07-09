package breadcrumbs.pugins

/**
 * Breadcrumbs TagLib
 * @author cyril
 */
class BreadCrumbsTagLib {

	/** Namespace of Breadcrumbs tagLib*/
	static namespace = 'crumbs'

	def breadCrumbsServiceProxy

	def grailsApplication

	/**
	 * Render the breadcrumbs
	 */
	def breadcrumbs = {attrs ->
		def breadcrumbs  = breadCrumbsServiceProxy.getAndDestroyBreadCrumbsPath()
		def divider = grailsApplication.config.breadcrumbs.divider
		out << render(plugin: 'breadcrumbs-plugins', template: "/tpl/breadcrumbs", model: [breadcrumbs: breadcrumbs, divider: divider])
	}
}
