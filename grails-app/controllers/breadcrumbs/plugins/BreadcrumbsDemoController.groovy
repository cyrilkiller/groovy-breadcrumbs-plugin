package breadcrumbs.plugins

import javax.swing.text.View;

import breadcrumbs.annotation.BreadCrumbs;
import breadcrumbs.scope.BreadCrumbsScopeEnum;

/**
 * sample controller
 * @author cyril
 *
 */
class BreadcrumbsDemoController {

	/** The breadcrumbs-plugin service */
	def breadCrumbsService
	
    def index() {
		[welcome: "Welcome to demo of breadcrumbs-plugins", page: "index"]
	}
	
	def pageOne(){
		render(view : "index", model: [welcome: "Welcome to demo of breadcrumbs-plugins", page: "pageOne"])
	}
	
	def pageTwo(){
		render(view : "index", model: [welcome: "Welcome to demo of breadcrumbs-plugins", page: "pageTwo"])
	}
	
	def pageThree(){
		render(view : "index", model: [welcome: "Welcome to demo of breadcrumbs-plugins", page: "pageThree"])
	}
	
	def pageThreeOne(){
		render(view : "index", model: [welcome: "Welcome to demo of breadcrumbs-plugins", page: "pageThreeOne"])
	}
	
	def pageThreeTwo(){
		render(view : "index", model: [welcome: "Welcome to demo of breadcrumbs-plugins", page: "pageThreeTwo"])
	}
	
	def pageThreeTwoOne(){
		render(view : "index", model: [welcome: "Welcome to demo of breadcrumbs-plugins", page: "pageThreeTwoOne"])
	}
	
	def pageThreeTwoTwo(){
		render(view : "index", model: [welcome: "Welcome to demo of breadcrumbs-plugins", page: "pageThreeTwoTwo"])
	}
	
	@BreadCrumbs(scope = BreadCrumbsScopeEnum.STATIC, actionName = "pageThreeOne" )
	def annoted(){
		render(view : "index", model: [welcome: "Welcome to demo of breadcrumbs-plugins", page: "Annoted"])
	}
	
	def createsomething(){
		render(view : "index", model: [welcome: "Welcome to demo of breadcrumbs-plugins", page: "createsomething"])
	}
	
	@BreadCrumbs(scope = BreadCrumbsScopeEnum.REQUEST, actionName = "requestAction" )
	def annotedRequest(){
		request.setAttribute("requestAction", "pageThreeTwo")
		render(view : "index", model: [welcome: "Welcome to demo of breadcrumbs-plugins", page: "Annoted"])
	}
	
	
	@BreadCrumbs(scope = BreadCrumbsScopeEnum.SESSION)
	def annotedSession(){
		breadCrumbsService.pushActionInSession("pageThreeTwoTwo")
		render(view : "index", model: [welcome: "Welcome to demo of breadcrumbs-plugins", page: "Annoted"])
	}
}
