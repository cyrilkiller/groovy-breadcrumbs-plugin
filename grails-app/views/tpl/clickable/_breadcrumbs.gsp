<g:def var="breadcrumbs"  value="${breadcrumbs }" />
<g:def var="divider"  value="${divider }" />
<g:def var="home" value="${home}" />
<g:def var="type" value="${type}" />
<ul class="breadcrumb" >
	<g:if test="${home}" >
		<g:if test="${type=="message"}">
			<li>
				<a href="${resource(dir: '')}" class="homepage">${message(code: home.message)}</a>
			</li>
		</g:if>
		<g:elseif test="${type=="image"}">
			<li>
				<a href="${resource(dir: '')}" class="homepage">
					<g:img file="${home.message }" />
				</a>
			</li>
		</g:elseif>
		<g:if test="${breadcrumbs.size > 0 }">
			<span class="divider">${divider}</span>
		</g:if>
	</g:if>
	<g:each in="${breadcrumbs}" var="breadcrumb" status="index">
		<g:if test="${index < breadcrumbs.size - 1}">
			<li>
				<g:if test="${breadcrumb.controller && breadcrumb.action }">
					<g:link controller="${breadcrumb.controller }" action="${breadcrumb.action}">
						${message(code: breadcrumb.message)}
					</g:link>
				</g:if>
				<g:else>
					${message(code: breadcrumb.message)}
				</g:else>
				<span class="divider">${divider}</span>	
			</li>		
	  	</g:if>
	  	<g:else>
			<li class="active">
				${message(code: breadcrumb.message)}
			</li>  	
	  	</g:else>
	</g:each>
</ul>

