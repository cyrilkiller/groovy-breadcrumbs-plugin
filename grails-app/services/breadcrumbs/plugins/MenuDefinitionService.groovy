package breadcrumbs.plugins

import org.springframework.cache.annotation.Cacheable;

import breadcrumbs.MenuItem

class MenuDefinitionService  {

	static transactional = false
	
	static scope = 'session'
	
	static proxy = true
	
	
    def loadMenuDefinition() {
		def menus = []
		
		menus << new MenuItem(name : "page-one", message:"page.one.demo", controller: "BreadCrumbsDemo", action: "pageOne")
		menus << new MenuItem(name : "page-two", message:"page.two.demo", controller: "BreadCrumbsDemo", action: "pageTwo")
		MenuItem menuThree = new MenuItem(name : "page-three", message:"page.three.demo", controller: "BreadCrumbsDemo", action : "pageThree" )
		menuThree <<  new MenuItem(name : "page-three-one", message:"page.three.one.demo", controller: "BreadCrumbsDemo", action:"pageThreeOne")
		MenuItem menuThreeTwo = new MenuItem(name : "page-three-two", message:"page.three.two.demo", controller: "BreadCrumbsDemo", action:"pageThreeTwo")
		menuThreeTwo << new MenuItem(name : "page-three-two", message:"page.three.two.one.demo", controller: "BreadCrumbsDemo", action:"pageThreeTwoOne")
		menuThreeTwo << new MenuItem(name : "page-three-two", message:"page.three.two.two.demo", controller: "BreadCrumbsDemo", action:"pageThreeTwoTwo")
		menuThree << menuThreeTwo
		
		menus << menuThree
		MenuItem menuCreateSomething = new MenuItem(name : "page-something", message:"page.something.demo")
		menuCreateSomething <<  new MenuItem(name : "page-createsomething", message:"page.create.something.demo", controller: "BreadCrumbsDemo", action:"createsomething")
		menus << menuCreateSomething
		menus
    }
	
}
