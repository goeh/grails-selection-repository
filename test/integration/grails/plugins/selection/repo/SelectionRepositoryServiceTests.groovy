package grails.plugins.selection.repo

import test.TestEntity

/**
 * Tests for SelectionStorageService
 */
class SelectionRepositoryServiceTests extends GroovyTestCase {

    def selectionService
    def selectionRepositoryService

    void testList() {
        selectionRepositoryService.put(new URI("gorm://testEntity/list?name=A*"), null, "testEntity", "joe.user", "Joe's private A people")
        selectionRepositoryService.put(new URI("gorm://testEntity/list?name=B*"), null, "testEntity", "joe.user", "Joe's private B people")
        selectionRepositoryService.put(new URI("gorm://testEntity/list?name=C*"), null, "testEntity", "liza.user", "Liza's C people")
        selectionRepositoryService.put(new URI("gorm://testEntity/list?name=D*"), null, "testEntity", null, "Public D people")

        assert selectionRepositoryService.list("testEntity", "joe.user").size() == 2
        assert selectionRepositoryService.list("testEntity", "liza.user").size() == 1
        assert selectionRepositoryService.list("testEntity").size() == 1
    }

    void testStoreRetrieveInvoke() {
        new TestEntity(number: "1", name: "Foo").save()
        new TestEntity(number: "2", name: "Bar").save()
        new TestEntity(number: "3", name: "Bert").save()
        new TestEntity(number: "4", name: "Folke").save()
        new TestEntity(number: "5", name: "David").save()

        def uri = selectionRepositoryService.put(new URI("gorm://testEntity/list?name=B*"), null, 'testEntity', 'joe.user', 'Test Selection')
        def result = selectionService.select(uri, [:])
        assert result.size() == 2
        result.each {
            assert it.name.startsWith("B")
        }
    }
}
