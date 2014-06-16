# Persistent Selections Plugin

The Grails [selection plugin](http://grails.org/plugin/selection) provides unified search for information.
It uses a URI based syntax to select any information from any resource.

**This** plugin "Persistent Selections" extends the functionality of the selection plugin by providing persistent storage for selections.

You provide the user with a query form and use the *selection* plugin to execute the query.
Then you can use this plugin to let the user save the query for future use.
Selections (queries) can be private or public which means that each user can have their own list of favorite queries
or you can provide a list of standard queries for users to select from.

The plugin is multi-tenant aware and provide a Twitter Bootstrap based user interface and GSP tags for managing saved selections.

**Example**

    def theQuery = new URI("gorm://person/list?lastName=A*")
    def savedQuery = selectionRepositoryService.put(theQuery, "person", "A people")

Later...

    def result = selectionService.select(savedQuery, [offset:0, max:10])
    // The result now contains a list of people who's name begins with A.

## SelectionRepositoryService

**List<Map> list(String location, String username = null)**

List selections in the repository.

Parameter | Description
--------- | -----------------
location  | Typically the root entity name for the query (com.foo.CustomerAddress -> customerAddress)
username  | Return only selections saved by a specific user

**URI get(Long id)**

Get the URI for a selection in the repository.

Parameter | Description
--------- | -----------------
id        | Lookup selection based on it's primary key


**URI put(URI selection, String location, String username, String name, String description = null)**

Save a selection in the repository.

Parameter   | Description
----------- | -----------------
selection   | The selection to save in the repository
location    | Typically the root entity name for the query (com.foo.CustomerAddress -> customerAddress)
username    | Save the selection in a specific user's repository. If not specified the selection will be available to all users
name        | A short name for the selection. This will typically be displayed in select menus
description | A text that explains what the selection returns, for example "This query will find all customers in France"

**void delete(Long id)**

Remove a selection from the repository

Parameter | Description
--------- | -----------------
id        | Primary key of the selection to remove

### Repository Object

The object returned by *list()* has the following properties:

Property           | Description
------------------ | ---------------
String location    | Typically the root entity name for the query (com.foo.CustomerAddress -> customerAddress)
String username    | User that saved the selection
String name        | Selection name given by the user
String description | Selection description given by the user
URI uri            | The selection URI - this is the object you send to *selectionService.select(URI)* to execute the query
Long id            | The unique ID for the selection 

## GSP Tags

**listRepo**

Attribute | Description
--------- | --------------
location  | (required) The location (ex. domainName) to filter on.
username  | (optional) List only selections saved by a specific user.
tenant    | (optional) List only selections saved in a specific tenant. In a multi-tenant environment this parameter can be omitted because current tenant is picked up automatically.
var       | (optional) The name of the selection item, defaults to "it".
status    | (optional) The name of a variable to store the iteration index in.

The object available in the tag body via **var** is described in section **Repository Object** above.

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

- The [GR8 CRM ecosystem](http://gr8crm.github.io) uses selection-repository plugin in most of it's CRUD views.
