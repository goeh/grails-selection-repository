<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'selectionRepository.label', default: 'Selection')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>

<div class="page-header">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
</div>

<g:if test="${flash.message}">
    <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
</g:if>

<g:hasErrors bean="${selectionRepository}">
    <bootstrap:alert class="alert-error">
        <ul>
            <g:eachError bean="${selectionRepository}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </bootstrap:alert>
</g:hasErrors>

<fieldset>
    <g:form class="form-horizontal" action="create">
        <g:hiddenField name="location" value="${selectionRepository.location}"/>
        <g:hiddenField name="username" value="${selectionRepository.username}"/>
        <g:hiddenField name="uri" value="${selectionRepository.uri}"/>
        <g:hiddenField name="referer" value="${referer}"/>
        <fieldset>
            <f:with bean="selectionRepository">
                <f:field property="name">
                    <g:textField name="name" value="" autofocus="" placeholder="${message(code:'selectionRepository.name.placeholder', default:'')}"/>
                </f:field>
                <f:field property="description">
                    <g:textArea name="description" rows="3" cols="70" value="" placeholder="${message(code:'selectionRepository.description.placeholder', default:'')}"/>
                </f:field>
            </f:with>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">
                    <i class="icon-ok icon-white"></i>
                    <g:message code="default.button.create.label" default="Create"/>
                </button>
            </div>
        </fieldset>
    </g:form>
</fieldset>

</body>
</html>
