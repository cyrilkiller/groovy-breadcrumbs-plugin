package breadcrumbs

import grails.test.mixin.TestFor
import java.lang.reflect.Method

import org.junit.rules.ExpectedException;

import spock.lang.*;


import breadcrumbs.annotation.BreadCrumbs;
import breadcrumbs.exception.BreadCrumbsException;
import breadcrumbs.scope.BreadCrumbsScopeEnum;

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(BreadCrumbsService)
class BreadCrumbsServiceSpec extends Specification {

	@Unroll
	def "Retrieve breadcrumbs for pair of action/controller "() {
		
		given :
		def menus = constructMenuItem()
		
		when: "trying to retrieves breadcrumbs"
		def path = service.retrievesItemMenu(menus, action, controller, param)

		then: "the number of parts is correct"
		assertEquals(countItem, path.size())

		where:
		countItem    	| controller 	| action   	| param
		3     			| 'controller'  | "fourth"	|null
		1     			| 'controller'  | "princ"   |null
		2     			| 'controller'  | "third"	|null
	}

	def "menu definition is null"(){
		given :
		def menus = null
		def path = []
		
		when : "trying to retrieves breadcrumbs"
		path = service.retrievesItemMenu(null, 'fourth', 'controller', null)
		
		then: "path is empty"
		assertEquals(0, path.size())
	}
		
	def "Action and controller not found in definition menu"(){
		given:
		def menus  = null
		def path = []
		
		when : "trying to retrieves breadcrumbs"
		path = service.retrievesItemMenu(null, 'undefined', 'undefined', null)
		
		then: "path is empty"
		assertEquals(0, path.size())
	}

	def "Pair of action/controller is null"(){
		
		given :
		def menus = constructMenuItem()
		def path  = []
		
		when : "trying to retrieves breadcrumbs"
		path = service.retrievesItemMenu(menus, null, null, null)
		
		then : "path is empty"
		assertEquals(0, path.size())
	}
	
	/**
	 * provided a menu definition for the test
	 * @return List of MenuItem
	 */
	private constructMenuItem(){
		def menus = []
		
		//Define a menu mar
		//Frist menu bar 
		menus << new MenuItem(name:'first', message:'first')
		//Second dmenu bar 
		menus << new MenuItem(name:'Second', message:'Second', action : 'princ', controller : 'controller')
		//Third menu bar item
		MenuItem menu =  new MenuItem(name:'Third', message:'Third', action : 'first', controller : 'controller')
			//A sub menu item of Third
			menu << new MenuItem(name:'firstSubItem', message:'firstSubItem', action : 'second', controller : 'controller')
			//An other sub menu of Third
			menu << new MenuItem(name:'secondSubItem', message:'secondSubItem', action : 'third', controller : 'controller')
			//An other sub menu of Third with sub item
			MenuItem underSubMenu = new MenuItem(name:'thirdSubItem', message:'thirdSubItem')
				underSubMenu << new MenuItem(name:'firstUnderSubItem', message:'firstUnderSubItem', action : 'fourth', controller : 'controller')
				underSubMenu << new MenuItem(name:'secondUnderSubItem', message:'secondUnderSubItem', action : 'fifth', controller : 'controller')
		
			menu << underSubMenu
		menus << menu
		
		menus
	}
	
}
