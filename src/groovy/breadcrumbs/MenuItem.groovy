package breadcrumbs

/**
 * Define a item menu
 * @author cyril
 *
 */
class MenuItem {
	/** Name of MenuItem*/
	String name
	/** Label of MenuItem */
	String message
	/** Action of MenuItem */
	String action
	/** Controller of MenuItem */
	String controller
	/** Sub MenuItem */
	def subMenuItems = []
	/** Parameters (request) of MenuItem */
	def params = [:]
	/** Css class of MenuItem */
	def cssClass = []
	/** Assigne a sub MenuItem */
	def leftShift(MenuItem menuItem){
		subMenuItems << menuItem
	}
}
