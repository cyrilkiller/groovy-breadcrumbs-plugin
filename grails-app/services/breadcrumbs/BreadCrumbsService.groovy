package breadcrumbs

import org.apache.commons.lang.StringUtils
import org.codehaus.groovy.grails.web.util.WebUtils

import breadcrumbs.annotation.BreadCrumbs
import breadcrumbs.exception.BreadCrumbsException
import breadcrumbs.scope.BreadCrumbsScopeEnum

/**
 * The breadcrumbs service
 * @author cyril
 */
class BreadCrumbsService {

	static transactional = false

	static scope  = 'session'

	/** Session namespace for breadcrumbs*/
	private static final String NAMESPACE = "breadcrumbs"

	/** key for retrieve actionName  */
	private static final String ACTION_NAME = "actionName"

	/** key for retrieve controllerName  */
	private static final String CONTROLLER_NAME = "controllerName"

	/** A empty value */
	private static final String EMPTY = ""

	def grailsApplication

	/**
	 *  Service as proxy
	 */
	static proxy = true

	/**
	 * Return breadcrumbs list
	 * @return {@link String}
	 */
	def getBreadCrumbsPath() {
		retrievesFromSession("path", "breadcrumbs", false)
	}

	/**
	 * Return breadcrumbs list and remove it from session
	 * @return {@link String}
	 */
	def getAndDestroyBreadCrumbsPath(){
		retrievesFromSession("path", "breadcrumbs", true)
	}

	/**
	 * Retrieves params from breadcrumbs scope
	 * @param breadCrumbs
	 * @return {@link Map}
	 */
	def findOverrideParams(BreadCrumbs breadCrumbs){
		if(1.equals(breadCrumbs.scope().value())){
			return retrievesFromSession(breadCrumbs)
		}
		if(2.equals(breadCrumbs.scope().value())){
			return retrievesFromRequest(breadCrumbs)
		}
		if(3.equals(breadCrumbs.scope().value())){
			return retrievesFromStatic(breadCrumbs)
		}
	}

	/**
	 * Check if BreadCrumbs annotation is valid
	 * @param breadCrumbs
	 * @throws BreadCrumbsException
	 */
	def validate(BreadCrumbs breadCrumbs){

		def session = WebUtils.retrieveGrailsWebRequest().session
		def request = WebUtils.retrieveGrailsWebRequest().request

		/* Parameter scope */
		BreadCrumbsScopeEnum scope = breadCrumbs.scope()

		if(scope.value() < 1 && scope.value() > 3 ){
			throw new BreadCrumbsException('Scope not found')
		}

		if(scope.equals(1)){
			//Session scope
			if(session[NAMESPACE].actionName == null || session[NAMESPACE].controllerName == null){
				throw new BreadCrumbsException('You must define actionNameInSession or controllerNameInSession in SESSION scope ')
			}else if(!"".equals(breadCrumbs.actionName()) || !"".equals(breadCrumbs.controllerName())){
				throw new BreadCrumbsException('Cannot use actionName or controllerName with SESSION scope')
			}
		}else if(scope.equals(2)){
			//Request scope
			if("".equals(breadCrumbs.actionName()) && "".equals(breadCrumbs.controllerName())){
				throw new BreadCrumbsException('One of actionName or controllerName required in REQUEST scope')
			}
			if(!"".equals(breadCrumbs.actionName()) && request[breadCrumbs.actionName()] == null){
				throw new BreadCrumbsException('actionName : %s not found in REQUEST scope'.format(breadCrumbs.actionName()))
			}
			if(!"".equals(breadCrumbs.controllerName()) && request[breadCrumbs.controllerName()] == null){
				throw new BreadCrumbsException('controllerName : %s not found in REQUEST scope'.format(breadCrumbs.controllerName()))
			}
		}else if(scope.equals(3)){
			//Static scope
			if("".equals(breadCrumbs.actionName()) && "".equals(breadCrumbs.controllerName())){
				throw new BreadCrumbsException('One of actionName or controllerName required in STATIC scope')
			}
		}

		true
	}

	/**
	 * Retrieves params from session scope
	 * @param breadCrumbs
	 * @return {@link Map}
	 */
	private retrievesFromSession(BreadCrumbs breadCrumbs){
		def parameters = [:]
		def session = WebUtils.retrieveGrailsWebRequest().session

		if(session[NAMESPACE].actionName != null && !EMPTY.equals(session[NAMESPACE].actionName)){
			parameters[ACTION_NAME] = session[NAMESPACE].actionName
		}

		if(session[NAMESPACE].controllerName != null && !EMPTY.equals(session[NAMESPACE].controllerName)){
			parameters[CONTROLLER_NAME] = session[NAMESPACE].controllerName
		}
		parameters
	}

	/**
	 * Retrieves params from request scope
	 * @param breadCrumbs
	 * @return {@link Map}
	 */
	private retrievesFromRequest(BreadCrumbs breadCrumbs){
		def parameters = [:]

		def request = WebUtils.retrieveGrailsWebRequest().request

		if(request[breadCrumbs.actionName()] != null && !EMPTY.equals(request[breadCrumbs.actionName()])){
			parameters[ACTION_NAME] = request[breadCrumbs.actionName()]
		}

		if(request[breadCrumbs.controllerName()] != null && !EMPTY.equals(request[breadCrumbs.controllerName()])){
			parameters[CONTROLLER_NAME] = request[breadCrumbs.controllerName()]
		}
		parameters
	}

	/**
	 * Retrieves params from static scope
	 * @param breadCrumbs
	 * @return {@link Map}
	 */
	private retrievesFromStatic(BreadCrumbs breadCrumbs){
		def parameters = [:]

		if(breadCrumbs.actionName() != null && !EMPTY.equals(breadCrumbs.actionName())){
			parameters[ACTION_NAME] = breadCrumbs.actionName()
		}

		if(breadCrumbs.controllerName() != null && !EMPTY.equals(breadCrumbs.controllerName())){
			parameters[CONTROLLER_NAME] = breadCrumbs.controllerName()
		}
		parameters
	}

	/**
	 * Put action name in breadcrumbs namespace session
	 * @param _actionName
	 */
	def pushActionInSession(String _actionName){

		def session = WebUtils.retrieveGrailsWebRequest().session

		if(_actionName ==  null || EMPTY.equals(_actionName)){
			throw new BreadCrumbsException('actionName cannot be null or empty ')
		}

		if(session[NAMESPACE] == null){
			session[NAMESPACE] = [:]
		}

		session[NAMESPACE][ACTION_NAME] = _actionName
	}

	/**
	 * Put controller name in breadcrumbs namespace session
	 * @param _controllerName
	 * @return
	 */
	def pushControllerInSession(String _controllerName){

		def session = WebUtils.retrieveGrailsWebRequest().session

		if(_controllerName ==  null || EMPTY.equals(_controllerName)){
			throw new BreadCrumbsException('controllerName cannot be null or empty ')
		}

		if(session[NAMESPACE] == null){
			session[NAMESPACE] = [:]
		}

		session[NAMESPACE][CONTROLLER_NAME] = _controllerName
	}

	/**
	 * Find an attribute from session
	 * @param attributeName
	 * @return {@link Object}
	 */
	def private retrievesFromSession(String attributeName, String namespace,  boolean destroy){
		def session = WebUtils.retrieveGrailsWebRequest().session
		def attribute

		if(namespace != null){
			attribute = session[namespace][attributeName]
			if(destroy.equals(true)){
				session[namespace][attributeName] = null
			}
		}else{
			attribute = session[attributeName]
			if(destroy.equals(true)){
				session[attributeName] = null
			}
		}

		attribute
	}

	/**
	 * retrieves the parts of breadcrumbs
	 * @param _menus
	 * @param _action
	 * @param _controller
	 * @param _params
	 * @return {@link List} of {@link String}
	 */
	def retrievesItemMenu(List<MenuItem> _menus, String _action, String _controller, Map<String, Object> _params){

		def path = []

		if(_menus != null && _menus.size()> 0){
			{menus ->
				MenuItem active = null
				menus.each(){
					if((it.controller != null && StringUtils.lowerCase(it.controller).equals(_controller))
						&& it.action != null && StringUtils.lowerCase(it.action).equals(_action)){
						path << it.message
						active = it
					}else{
						if(active == null && it.subMenuItems != null && it.subMenuItems.size() > 0){
							MenuItem m = owner.call(it.subMenuItems)
							if( m != null){
								path << it.message
								active = it
							}
						}
					}
				}
				active
			}(_menus)
		}
		path.reverse()
	}

	/**
	 * Retrieve an Artifact
	 * @param artifactType
	 * @param artifactName
	 * @return Artifact
	 */
	def retrievesArtifact(String artifactType, String artifactName){
		return grailsApplication.getArtefactByLogicalPropertyName(artifactType, artifactName)
	}
}
