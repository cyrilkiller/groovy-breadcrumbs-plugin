groovy-breadcrumbs-plugin
=========================

This plugin gives you an Breadcrumb based on a definition of the navigation structure (implementation of a "menuDefinitionService" service).
A simple breadcrumb editable and robust.
the construction of the breadcrumb is based on the couple action / controller.
An annotation allows you to change the breadcrumb.
Very flexible and easy to implement.

Install
========================

Pending on [breadcrumbs-plugin](http://grails.org/plugins/pending/133#)

in waiting [fork me](https://github.com/cyrilkiller/groovy-breadcrumbs-plugin/fork)


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

Adding the taglig in your layout

```gsp
<crumbs:breadcrumbs /> 
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

if you using less, let me offer you

```less
/**
 * Tag breadcrumb
 */
@greyLightColor:			#f5f5f5;
@colorPrincipalBreadcrumb: 	#999999;
@colorBackgroundBreadcrumb: @greyLightColor;
 
.breadcrumb {
    padding: 8px 15px;
    list-style: none;
    background-color: @colorBackgroundBreadcrumb;
    font: 10pt Arial regular;
    
	> li {
    display: inline;
    text-shadow: 0 1px 0 #000000;
    > .divider {
      padding: 0 5px;
      color: @colorPrincipalBreadcrumb;
    }
	> a {
	  color: @colorPrincipalBreadcrumb;
	}
  }
  > .active {
  	color: @colorPrincipalBreadcrumb;
  }
}
```

Thank to
=========================

[laurent guerin ](https://github.com/lguerin?source=cc) (my master :))
 


