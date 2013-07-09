package breadcrumbspluginsgrailsplugin

import java.lang.reflect.Method

import org.apache.commons.lang.StringUtils
import org.springframework.context.i18n.LocaleContextHolder as LCH

import breadcrumbs.annotation.BreadCrumbs
import breadcrumbs.exception.BreadCrumbsException

/**
 * filter use for build breadcrumbs
 * @author cyril
 */
class BreadCrumbsFilters {

	/** i18n service */
	def messageSource

	/** Service as proxy {@link BreadCrumbsService} */
	def breadCrumbsServiceProxy

	/** The global grails application*/
	def grailsApplication

	/** Menu definition service provide by application */
	def menuDefinitionServiceProxy

	def filters = {
		all(controller:'*', action:'*') {
			before = {
				//Exclusion des requete Ajax
				if(!request.xhr){
					buildBreadCrumbs(controllerName, actionName, params, session)
				}
			}
			after = { Map model ->
				if(request.xhr){
					return
				}

				BreadCrumbs bm
				/* Retrieve whether target method is annoted */
				if(controllerName != null && actionName != null){
					Class clazz = breadCrumbsServiceProxy.retrievesArtifact("Controller", controllerName).clazz
					if(!clazz != null){
						return
					}

					Method m
					try{
						m = clazz.getMethod(actionName, null)
					}catch(NoSuchMethodException e){/* No method !!! Dynamic Scaffolding ... ??? */}

					if(!m || !m.isAnnotationPresent(BreadCrumbs)){
						return
					}

					bm = m.getAnnotation(BreadCrumbs)
					if(breadCrumbsServiceProxy.validate(bm))
					//delete breadcrumbs
					session["breadcrumbs"].path = null

					String ctrl = controllerName
					String act = actionName

					/* find params from scope */
					def parameters = breadCrumbsServiceProxy.findOverrideParams(bm)

					if(parameters["actionName"]){
						act = parameters["actionName"]
					}

					if(parameters["controllerName"]){
						ctrl = parameters["controllerName"]
					}

					buildBreadCrumbs(ctrl, act, params, session)
				}
			}
		}
	}

	/**
	 * Build breadcrumbs
	 * @param ctrl controller
	 * @param act action
	 * @param prs params
	 * @param sess session
	 */
	def buildBreadCrumbs(ctrl, act, prs, sess){

		ctrl = StringUtils.lowerCase(ctrl)
		act = StringUtils.lowerCase(act)

		if(sess["breadcrumbs"] == null){
			sess["breadcrumbs"] = [:]
		}

		if(sess["breadcrumbs"].path == null){
			def path = []
			def divider = ""
			try {
				/* Application service */
				def menus = menuDefinitionServiceProxy.loadMenuDefinition()
				path = breadCrumbsServiceProxy.retrievesItemMenu(menus, act, ctrl, prs)
			}catch(Exception e){
				/* no service is provided by the application */
				throw new BreadCrumbsException("No MenuDefinitionService is provided or not implement loadMenuDefinition method")
			}

			//Home Page
			if(path == null || path.size() == 0 ){
				path << messageSource.getMessage('breadcrumbs.home.label', null, LCH.getLocale())
			}

			sess["breadcrumbs"].path = path
		}
	}
}
