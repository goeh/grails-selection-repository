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

class SelectionRepoTagLib {

    static namespace = "selection"

    def selectionRepositoryService

    /**
     * List the selection repository filtered by 'location' and optionally username and tenant.
     * The tag body has access to [id, name, description and uri] for each selection listed.
     *
     * @attr location REQUIRED The location (ex. domainName) to filter on.
     * @attr status (optional) The name of a variable to store the iteration index in.
     * @attr var (optional) The name of the selection item, defaults to "it".
     */
    def listRepo = {attrs, body ->
        if (!attrs.location) {
            throwTagError("Tag [repository] is missing required attribute [location]")
        }
        def result = selectionRepositoryService.list(attrs.location, attrs.username, attrs.tenant)
        int i = 0
        for(s in result) {
            def map = [(attrs.var ?: 'it'): s]
            if (attrs.status) {
                map[attrs.status] = i++
            }
            out << body(map)
        }
    }
}
