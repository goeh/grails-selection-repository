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

/**
 * This selection handler provides access to persisted selections.
 * selection://host/servlet?query=foo
 */
class RepositorySelection {

    def selectionService
    def selectionRepositoryService

    /**
     * Check that the URI scheme is 'repo'.
     * @param uri the URI to check support for
     * @return true if uri.scheme is 'repo'
     */
    boolean supports(URI uri) {
        return uri?.scheme == 'repo'
    }

    /**
     * Execute a selection stored in the selection repository.
     *
     * @param uri an URI with the format repo:<id> where id is the primary key of the persisted selection.
     * @param params selection parameters
     * @return result ov executing the selection
     */
    def select(URI uri, Map params) {
        // Convert the specified primary key to Long
        def id = Long.valueOf(uri.schemeSpecificPart)
        // Find selection in repository
        def storedURI = selectionRepositoryService.selection(id)
        if(log.isDebugEnabled()) {
            log.debug "$uri = $storedURI"
        }
        // Now execute the selection and return the result.
        selectionService.select(storedURI, params)
    }
}
