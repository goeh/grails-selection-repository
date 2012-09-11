<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'selectionRepository.label', default: 'Selection')}"/>
    <title><g:message code="selectionRepository.list.title" args="[entityName]"/></title>
</head>

<body>

<header class="page-header">
    <h1>
        <g:message code="selectionRepository.list.title" args="[entityName]"/>
        <small>${username?.encodeAsHTML()}</small>
    </h1>
</header>

<table class="table table-striped">
    <thead>
    <tr>
        <th><g:message code="selectionRepository.name.label" default="Name"/></th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${result}" status="i" var="s">
        <tr>
            <td><g:link action="edit" id="${s.id}">${s.name.encodeAsHTML()}</g:link></td>
        </tr>
    </g:each>
    </tbody>
</table>

</body>
</html>
