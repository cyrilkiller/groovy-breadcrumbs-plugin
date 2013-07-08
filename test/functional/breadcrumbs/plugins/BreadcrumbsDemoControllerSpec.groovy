package breadcrumbs.plugins


import breadcrumbs.plugins.pages.DemoPage;

import geb.spock.GebReportingSpec
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Stepwise;

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@Stepwise
class BreadcrumbsDemoControllerSpec  extends GebReportingSpec {

	
	
	def "retrieve breadcrumbs"() {
		when: "Go home page"
		to DemoPage
		
		then: "Breadcrumbs home"
		assert active.text() == "Home"
		assert li.size() == 1 
		
		when : "Click on subsub item "
		linkSubSubItem.click()
		
		then : "breadcrumbs contains three parts"
		assert li.size() == 3
		assert li[0].text().startsWith("pageThree")
		assert li[1].text().startsWith("pageThreeTwo")
		assert li[2].text().startsWith("pageThreeTwoTwo")

		when : "Click on annoted item "
		annoted.click()
		
		then : "breadcrumbs contains three parts"
		assert li.size() == 2
		assert li[0].text().startsWith("pageThree")
		assert li[1].text().startsWith("pageThreeOne")
		
		when : "Click on custom item "
		custom.click()
		
		then : "breadcrumbs contains two parts"
		assert li.size() == 2
		assert li[0].text().startsWith("SomeThing")
		assert li[1].text().startsWith("Create Something")
		
	}
}
