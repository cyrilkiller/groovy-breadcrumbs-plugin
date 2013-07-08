groovy-breadcrumbs-plugin
=========================

This plugin gives you an Breadcrumb based on a definition of the navigation structure (implementation of a "menuDefinitionService" service).
A simple breadcrumb editable and robust.
the construction of the breadcrumb is based on the couple action / controller.
An annotation allows you to change the breadcrumb.
Very flexible and easy to implement.

Install
========================



Using
========================

The breadcrumbs plugin is based on a MenuDefinitionService is provided by the application. So les't go create the service

Run `create-service MenuDefinitionService`

In MenuDefinitionService create an method call `loadMenuDefinition` 

example 


```groovy

class MenuDefinitionService  {

  static transactional = false
	
	static scope = "session"
	
	static proxy = true
	
	
  def loadMenuDefinition() {
		def menus = []
    
      menus << new MenuItem(name : "page-one", message:"page.one.demo", controller: "BreadCrumbsDemo", action: "pageOne")c
    	MenuItem menuThreeTwo = new MenuItem(name : "page-two-", message:"page.two.demo", controller: "BreadCrumbsDemo", action:"pageThreeTwo")
  		menuThreeTwo << new MenuItem(name : "page-two.one", message:"page.two.one.demo", controller: "BreadCrumbsDemo", action:"pageThreeTwoOne")
  		menuThreeTwo << new MenuItem(name : "page-two.two", message:"pagetwo.two.demo", controller: "BreadCrumbsDemo", action:"pageThreeTwoTwo")
  		menuThree << menuThreeTwo
  }   
}

```

You can define as many levels as you want !!!

Just define the message in `messages.properties`

like `page.two.demo=pageTwo`

Declare Service `MenuDefinitionService` as proxy in `resources.groovy`

```groovy
beans = {
	...
	menuDefinitionServiceProxy(org.springframework.aop.scope.ScopedProxyFactoryBean) {
		targetBeanName = 'menuDefinitionService'
		proxyTargetClass = true
	}
	...
}
```

Now the end for define the divider of breadcrumbs

add grails configuration in config.groovy

```goovy
breadcrumbs.divider = "&gt;"
```

You can define all `> < / - ...` divider that you want
You can also define html character like `&gt; or &lt;` or `&yen;` if you want :)


Thank to
=========================




