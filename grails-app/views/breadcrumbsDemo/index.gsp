<!DOCTYPE html>
<head>
	<title>BreadCrumbs-Plugins</title>
</head>
<body>
	<style>
		.breadcrumb li{
			display : inline;
		}
	</style>
	<p>${welcome}</p>
	<hr />
	<p>You are on page : ${page}</p>
	<div>
		<crumbs:breadcrumbs />
	</div>
	<hr />
	<ul>
		<li><g:link controller="BreadcrumbsDemo" action="pageOne">pageOne</g:link>	</li>
		<li><g:link controller="BreadcrumbsDemo" action="pageTwo">pageTwo</g:link>	</li>
		<li>
			<g:link controller="BreadcrumbsDemo" action="pageThree">pageThree</g:link>
			<ul>
				<li><g:link controller="BreadcrumbsDemo" action="pageThreeOne">pageThreeOne</g:link></li>
				<li><g:link controller="BreadcrumbsDemo" action="pageThreeTwo">pageThreeTwo</g:link>
					<ul>
						<li><g:link controller="BreadcrumbsDemo" action="pageThreeTwoOne">pageThreeTwoOne</g:link></li>
						<li><g:link controller="BreadcrumbsDemo" action="pageThreeTwoTwo">pageThreeTwoTwo</g:link>
					</ul>
				</li>
			</ul>
		</li>
		<li><g:link controller="BreadcrumbsDemo" action="annoted">annoted</g:link></li>
		<li><g:link controller="BreadcrumbsDemo" action="annotedRequest">annoted request</g:link></li>
		<li><g:link controller="BreadcrumbsDemo" action="annotedSession">annoted session</g:link></li>
	</ul>
	<hr />
	
	<g:link controller="BreadcrumbsDemo" action="createsomething">createsomething</g:link>
	
	<br />
	<g:if test="${page == "createsomething" }">
		Enjoy !!!
	</g:if>
</body>
