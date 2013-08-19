package breadcrumbs.plugins

import geb.spock.GebReportingSpec
import grails.plugin.remotecontrol.RemoteControl
import spock.lang.Stepwise
import breadcrumbs.plugins.pages.DemoPage

@Stepwise
class BreadcrumbsDemoControllerSpec  extends GebReportingSpec {

	def remote = new RemoteControl()

	def "retrieve breadcrumbs without home item "() {
		setup:
		remote.exec{ app.config.breadcrumbs.enable.home=false}
//		when: "Go home page"
//		to DemoPage

//		then: "Breadcrumbs home"
//		assert active.text() == "Home"
//		assert li.size() == 1

		when : "Click on subsub item "
		to DemoPage
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
	
	def "retrieve breadcrumbs with home item "() {
		setup:
		remote.exec{ app.config.breadcrumbs.enable.home=true}

		when : "Click on subsub item "
		to DemoPage
		linkSubSubItem.click()

		then : "breadcrumbs contains three parts"
		assert li.size() == 4
		
	}

}
