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
 *  under the License.
 */
package grails.plugins.selection.repo

import javax.servlet.http.HttpServletResponse

/**
 * This controller handles saving and accessing selections stored in the repository.
 */
class SelectionRepositoryController {

    def selectionRepositoryService
    def grailsApplication

    def create() {
        switch (request.method) {
            case 'GET':
                def selection = new SelectionRepository(params)
                [selectionRepository: selection, referer: params.referer]
                break
            case 'POST':
                def uri = selectionRepositoryService.put(new URI(params.uri), params.location, params.username, params.name, params.description)
                if (params.referer) {
                    redirect(uri: params.referer - request.contextPath)
                } else {
                    redirect(controller: params.location, action: "list", id: uri.toString().encodeAsURL())
                }
                break
        }
    }

    def delete(Long id) {
        def selection = SelectionRepository.get(id)
        if (selection) {
            def ctrl = selection.location
            selectionRepositoryService.delete(id)
            if (params.referer) {
                redirect(uri: params.referer - request.contextPath)
            } else {
                redirect(controller: ctrl)
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND)
        }
    }
}
