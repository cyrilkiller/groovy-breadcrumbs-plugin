<g:def var="breadcrumbs"  value="${breadcrumbs }" />
<g:def var="divider"  value="${divider }" />
<ul class="breadcrumb" >
	<g:each in="${breadcrumbs}" var="breadcrumb" status="index">
		<g:if test="${index < breadcrumbs.size - 1}">
			<li>${message(code: breadcrumb)}<span class="divider">${divider}</span></li>
	  	</g:if>
	  	<g:else>
			<li class="active">${message(code: breadcrumb)}</li>  	
	  	</g:else>
	</g:each>
</ul>

