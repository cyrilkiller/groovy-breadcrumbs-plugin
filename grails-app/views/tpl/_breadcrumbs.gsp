<g:def var="breadcrumbs"  value="${breadcrumbs }" />
<g:def var="divider"  value="${divider }" />
<g:def var="home" value="${home}" />
<g:def var="type" value="${type}" />
<ul class="breadcrumb" >
	<g:if test="${home}" >
		<li>	
			<g:if test="${type=="message"}">
				${message(code: home.message)}
			</g:if>
			<g:elseif test="${type=="image"}">
				<g:img file="${home.message }" />
			</g:elseif>
			<g:if test="${breadcrumbs.size > 0 }">
				<span class="divider">${divider}</span>
			</g:if>
		</li>
	</g:if>
	<g:each in="${breadcrumbs}" var="breadcrumb" status="index">
		<g:if test="${index < breadcrumbs.size - 1}">
			<li>
				${message(code: breadcrumb.message)} <span class="divider">${divider}</span>	
			</li>
	  	</g:if>
	  	<g:else>
			<li class="active">
				${message(code: breadcrumb.message)}
			</li>  	
	  	</g:else>
	</g:each>
</ul>

