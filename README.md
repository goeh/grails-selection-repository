#Persistent Selections for the Selection Plugin

##This plugin makes it possible to store selections in a repository.

The selection plugin provides unified search for information.
It uses a URI based syntax to select any information from any resource.

**Example**

    def uri = new URI("gorm://person/list?lastName=A*")
    def id = selectionRepositoryService.put(uri, "person", "A people")

Later...

    def uri = selectionRepositoryService.get(id)
    def result = selectionService.select(uri, [offset:0, max:10])
