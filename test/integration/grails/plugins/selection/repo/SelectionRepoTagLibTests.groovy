package grails.plugins.selection.repo


import grails.test.GroovyPagesTestCase

/**
 * Test the SelectionRepoTagLib.
 */
class SelectionRepoTagLibTests extends GroovyPagesTestCase {

    def selectionRepositoryService

    void testTagLibIterate() {
        selectionRepositoryService.put(new URI("gorm://testEntity/list?name=A*"), "testEntity", null, "A people")
        selectionRepositoryService.put(new URI("gorm://testEntity/list?name=B*"), "testEntity", null, "B people")
        selectionRepositoryService.put(new URI("gorm://testEntity/list?name=C*"), "testEntity", null, "C people")
        selectionRepositoryService.put(new URI("gorm://testEntity/list?name=C*"), "testEntity", "joe", "D people")

        def template = '<select:listRepo location="testEntity"><li>\${it.name}</li></select:listRepo>'
        assert applyTemplate(template) == '<li>A people</li><li>B people</li><li>C people</li>'

        template = '<select:listRepo location="testEntity" var="sel" status="i"><li>\${i+1}. \${sel.name}</li></select:listRepo>'
        assert applyTemplate(template) == '<li>1. A people</li><li>2. B people</li><li>3. C people</li>'


        template = '<select:listRepo location="testEntity" var="sel" username="joe"><li>\${sel.name}</li></select:listRepo>'
        assert applyTemplate(template) == '<li>D people</li>'
    }
}
