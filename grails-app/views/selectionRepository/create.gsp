<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'selectionRepository.label', default: 'Selection')}"/>
    <title><g:message code="selectionRepository.create.title" args="[entityName]"/></title>
</head>

<body>

<div class="page-header">
    <h1><g:message code="selectionRepository.create.title" args="[entityName]"/></h1>
</div>

<g:if test="${flash.message}">
    <div class="alert alert-info">${flash.message}</div>
</g:if>

<g:hasErrors bean="${selectionRepository}">
    <div class="alert alert-error">
        <ul>
            <g:eachError bean="${selectionRepository}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </div>
</g:hasErrors>

<g:form class="form-horizontal" action="create">
    <g:hiddenField name="location" value="${selectionRepository.location}"/>
    <g:hiddenField name="username" value="${selectionRepository.username}"/>
    <g:hiddenField name="uri" value="${selectionRepository.uri}"/>
    <g:hiddenField name="tenant" value="${selectionRepository.tenantId}"/>
    <g:hiddenField name="referer" value="${referer}"/>
    <fieldset>
        <div class="control-group">
            <label class="control-label"><g:message code="selectionRepository.name.label" default="Name"/></label>

            <div class="controls">
                <g:textField name="name" value="${selectionRepository.name}" autofocus=""
                             placeholder="${message(code: 'selectionRepository.name.placeholder', default: '')}"/>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"><g:message code="selectionRepository.description.label"
                                                    default="Description"/></label>

            <div class="controls">
                <g:textArea name="description" rows="3" cols="70" value="${selectionRepository.description}"
                            placeholder="${message(code: 'selectionRepository.description.placeholder', default: '')}"/>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">
                <i class="icon-ok icon-white"></i>
                <g:message code="selectionRepository.button.save.label" default="Save"/>
            </button>
        </div>
    </fieldset>
</g:form>

</body>
</html>
