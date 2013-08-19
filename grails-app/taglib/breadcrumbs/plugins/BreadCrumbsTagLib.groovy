package breadcrumbs.plugins

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
		def template = "/tpl/breadcrumbs"
		def divider = grailsApplication.config.breadcrumbs.divider
		def displayHome = breadCrumbsServiceProxy.getHomeItem()
		//About home item
		def homeType = "message" 
		if(displayHome){
			homeType = grailsApplication.config.breadcrumbs.home.type
		}

		//Switch to clickable template if set in config
		if(grailsApplication.config.breadcrumbs.enable.clickable){
			template "/tpl/clickable/breadcrumbs"
		}
		out << render(plugin: 'groovy-breadcrumbs-plugin', template: template, model: [breadcrumbs: breadcrumbs, divider: divider, home : displayHome, type: homeType])
	}
}
