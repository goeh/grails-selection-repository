package grails.plugins.selection.repo

class SelectionRepositoryTagLib {

    static namespace = "selection"

    def selectionRepositoryService

    /**
     * List the selection repository filtered by 'location' and optionally username and tenant.
     * The tag body has access to [id, name, description and uri] for each selection listed.
     * Attributes
     * status (optional) - The name of a variable to store the iteration index in.
     * var (optional) - The name of the selection item, defaults to "it".
     */
    def listRepo = {attrs, body ->
        if (!attrs.location) {
            throwTagError("Tag [repository] is missing required attribute [location]")
        }
        def list = selectionRepositoryService.list(attrs.location, attrs.username, attrs.tenant)
        list.eachWithIndex {s, i ->
            def map = [(attrs.var ?: 'it'): s]
            if (attrs.status) {
                map[attrs.status] = i
            }
            out << body(map)
        }
    }
}
