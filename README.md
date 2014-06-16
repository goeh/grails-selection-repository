# Persistent Selections for the Selection Plugin

The [selection plugin](http://grails.org/plugin/selection) provides unified search for information.
It uses a URI based syntax to select any information from any resource.

**This** plugin extends the functionality of the selection plugin by providing persistent storage for selections.
A user can save a selection (query filter) for future use. This plugin is tenant-aware and provides a
Twitter Bootstrap based user interface and GSP tags for managing saved selections.

**Example**

    def uri = new URI("gorm://person/list?lastName=A*")
    def id = selectionRepositoryService.put(uri, "person", "A people")

Later...

    def uri = selectionRepositoryService.get(id)
    def result = selectionService.select(uri, [offset:0, max:10])
    // The result now contains a list of people who's name begins with A.

## SelectionRepositoryService

## GSP Tags

### listRepo

Attribute | Description
--------- | --------------
location  | (required) The location (ex. domainName) to filter on.
username  | (optional) List only selections saved by a specific user.
tenant    | (optional) List only selections saved in a specific tenant. In a multi-tenant environment this parameter can be omitted because current tenant is picked up automatically.
var       | (optional) The name of the selection item, defaults to "it".
status    | (optional) The name of a variable to store the iteration index in.

The object available in the tag body via **var** has the following properties:

Property           | Description
------------------ | ---------------
String location    | Typically the root entity name for the query (com.foo.CustomerAddress -> customerAddress)
String username    | User that saved the selection
String name        | Selection name given by the user
String description | Selection description given by the user
URI uri            | The selection URI - this is the object you send to *selectionService.select(URI)* to execute the query

The following GSP code snippet shows how you can list all selections saved by user 'david'.
David can then click the link to execute the saved query and see the result in a list view.

    <ul>
        <select:listRepo location="customer" username="david" var="s">
            <li>
                <select:link controller="${s.controller}" action="${s.action ?: 'list'}" selection="${s.uri}">
                    ${s.name.encodeAsHTML()}
                </select:link>
            </li>
        </select:listRepo>
    </ul>
    

## Miscellaneous

- The [GR8 CRM ecosystem](http://gr8crm.github.io) uses selection-repo plugin in most of it's CRUD views.
