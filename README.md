groovy-breadcrumbs-plugin
=========================

This plugin gives you an Breadcrumb based on a definition of the navigation structure (implementation of a "menuDefinitionService" service).
A simple breadcrumb editable and robust.
the construction of the breadcrumb is based on the couple action / controller.
An annotation allows you to change the breadcrumb.
Very flexible and easy to implement.

Install
========================

```groovy
compile ":groovy-breadcrumbs-plugin:1.0.1"
```

Using
========================

The breadcrumbs plugin is based on a MenuDefinitionService is provided by the application. So lets go create the service

Run `create-service MenuDefinitionService`

In MenuDefinitionService create an method called `loadMenuDefinition`

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
	 	menus << menuThreeTwo
	  
	 	menus
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

Adding the taglib in your layout

```gsp
<crumbs:breadcrumbs />
```

Using BreadCrumbs Annotation
================================

To redefine breadcrumb use ``@BreadCrumbs``

The breadcrumb can be reset in three ways

* static
* session
* request

Example with static scope
```groovy
@BreadCrumbs (scope = BreadCrumbsScopeEnum.STATIC, actionName = "actionName", ControllerName = "ControllerName")
def controllerMethod() {
  ....
}
```

Example with request scope
```groovy
@BreadCrumbs (scope = BreadCrumbsScopeEnum.REQUEST, actionName = "actionName", ControllerName = "ControllerName")
def controllerMethod() {
  ....
}
```

Example with session scope
```groovy
@BreadCrumbs (scope = BreadCrumbsScopeEnum.SESSION)
def controllerMethod() {

	breadCrumbsService.pushActionInSession ("actionName")
	and / or
	breadCrumbsService.pushControllerInSession ("controllerName")
 	 ....
}
```

activate clickable Mode
=========================
```groovy
breadcrumbs.enable.clickable=true
```

Configure Home Item
=========================
```groovy
breadcrumbs.enable.home=true
```

Two mode are possible

mode image 
-------------------------
```groovy
breadcrumbs.home.type="image"
```
mode text
-------------------------
```groovy
breadcrumbs.home.type="message"
```

Add a ```getHomeItem()```

Example for image mode

```groovy
def getHomeItem(){
	def message = "breadcrumbs/menu_home.png"
	def home  = new MenuItem(name:"page-home", message: message);
	home
}
```

Or for message mode

```groovy
def getHomeItem(){
	def home  = new MenuItem(name:"page-home", message: "message key");
	home
}
```

for style
=========================

define a css for

```css
.breadcrumb
```

for

```css
.breadcrumb > li
```

and for active item

```css
.breadcrumb > li > .active
```


Thank to
=========================

[laurent guerin ](https://github.com/lguerin?source=cc)
