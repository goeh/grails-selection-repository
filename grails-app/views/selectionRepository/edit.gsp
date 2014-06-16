<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'selectionRepository.label', default: 'Selection')}"/>
    <title><g:message code="selectionRepository.edit.title" args="[entityName, selectionRepository]"/></title>
</head>

<body>

<div class="page-header">
    <h1><g:message code="selectionRepository.edit.title" args="[entityName, selectionRepository]"/></h1>
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

<g:form class="form-horizontal" action="edit">
    <g:hiddenField name="id" value="${selectionRepository.id}"/>
    <g:hiddenField name="version" value="${selectionRepository.version}"/>
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
            <button type="submit" class="btn btn-primary" name="_action_edit">
                <i class="icon-ok icon-white"></i>
                <g:message code="selectionRepository.button.save.label" default="Save"/>
            </button>
            <button type="submit" class="btn btn-danger" name="_action_delete"
                    onclick="return confirm('${message(code:'selectionRepository.button.delete.confirm.message', default:'Are you sure?')}')">
                <i class="icon-trash icon-white"></i>
                <g:message code="selectionRepository.button.delete.label" default="Delete"/>
            </button>
        </div>
    </fieldset>
</g:form>

</body>
</html>
