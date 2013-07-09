package breadcrumbs.plugins.pages

import geb.Page

class DemoPage extends Page {

	static url = "http://localhost:8080/breadcrumbs-plugin/"

	static content = {
		//activeItem {$(".breadcrumb > .active")}
		breadcrumbsUl { $(".breadcrumb") }
		li {$(breadcrumbsUl).find("li")}
		active {$(breadcrumbsUl).find(".active")}
		linkSubSubItem {$("a[href='/breadcrumbs-plugin/breadcrumbsDemo/pageThreeTwoTwo']")}
		annoted {$("a[href='/breadcrumbs-plugin/breadcrumbsDemo/annoted']")}
		custom {$("a[href='/breadcrumbs-plugin/breadcrumbsDemo/createsomething']")}
	}
}
