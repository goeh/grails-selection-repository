/*
 *  Copyright 2012 Goran Ehrsson.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package grails.plugins.selection.repo

import javax.servlet.http.HttpServletResponse

/**
 * This controller handles saving and accessing selections stored in the repository.
 */
class SelectionRepositoryController {

    static allowedMethods = [create: ['GET', 'POST'], delete: 'POST']

    static WHITE_LIST = ['username', 'location', 'name', 'description', 'uriString']

    def selectionService
    def selectionRepositoryService
    def grailsApplication

    def list(String location, String username, Long tenant) {
        def result = selectionRepositoryService.list(location, username, tenant)
        return [username: username, location: location, result: result, totalCount: result.size()]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                def selection = new SelectionRepository(uriString: params.uri ?: params.id)
                bindData(selection, params, [include: WHITE_LIST])
                if (params.tenant) {
                    selection.tenantId = params.long('tenant')
                }
                [selectionRepository: selection, referer: params.referer]
                break
            case 'POST':
                try {
                    def selection = selectionService.decodeSelection(params.uri)
                    selectionRepositoryService.put(selection, params.location, params.username, params.name, params.description, params.long('tenant'))
                    flash.success = message(code: 'selectionRepository.created.message', args: [message(code: 'selectionRepository.label', default: 'Selection'), params.name])
                    if (params.referer) {
                        redirect(uri: params.referer - request.contextPath)
                    } else {
                        redirect(controller: params.location, action: "list", params: selectionService.createSelectionParameters(selection))
                    }
                } catch (Exception e) {
                    log.error(e)
                    params.uriString = params.remove('uri')
                    def selection = new SelectionRepository()
                    bindData(selection, params, [include: WHITE_LIST])
                    selection.validate()
                    render view: 'create', model: [selectionRepository: selection, referer: params.referer]
                }
                break
        }
    }

    def edit() {
        def selectionRepository = SelectionRepository.get(params.id)
        if (!selectionRepository) {
            flash.error = message(code: 'selectionRepository.not.found.message', args: [message(code: 'selectionRepository.label', default: 'Selection'), params.id])
            redirect action: 'list'
            return
        }

        switch (request.method) {
            case 'GET':
                return [selectionRepository: selectionRepository]
            case 'POST':
                if (params.version) {
                    def version = params.version.toLong()
                    if (selectionRepository.version > version) {
                        selectionRepository.errors.rejectValue('version', 'selectionRepository.optimistic.locking.failure',
                                [message(code: 'selectionRepository.label', default: 'Status')] as Object[],
                                "Another user has updated this seleciton while you were editing")
                        render view: 'edit', model: [selectionRepository: selectionRepository]
                        return
                    }
                }

                bindData(selectionRepository, params, [include: ['name', 'description']])

                if (!selectionRepository.save(flush: true)) {
                    render view: 'edit', model: [selectionRepository: selectionRepository]
                    return
                }

                flash.success = message(code: 'selectionRepository.updated.message', args: [message(code: 'selectionRepository.label', default: 'Selection'), selectionRepository.toString()])
                redirect action: 'list', params: [location: selectionRepository.location, username: selectionRepository.username, tenant: selectionRepository.tenantId]
                break
        }
    }

    def delete(Long id) {
        def selectionRepository = SelectionRepository.get(id)
        if (selectionRepository) {
            def map = [location: selectionRepository.location, username: selectionRepository.username, tenant: selectionRepository.tenantId]
            selectionRepositoryService.delete(id)
            if (params.referer) {
                redirect(uri: params.referer - request.contextPath)
            } else {
                redirect(action: 'list', params: map)
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND)
        }
    }

}
