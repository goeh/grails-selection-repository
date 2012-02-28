package grails.plugins.selection.repo

import test.TestEntity

/**
 * Tests for SelectionStorageService
 */
class SelectionRepositoryServiceTests extends GroovyTestCase {

    def selectionService
    def selectionRepositoryService

    void testList() {
        selectionRepositoryService.put(new URI("gorm://testEntity/list?name=A*"), "testEntity", "joe.user", "Joe's private A people")
        selectionRepositoryService.put(new URI("gorm://testEntity/list?name=B*"), "testEntity", "joe.user", "Joe's private B people")
        selectionRepositoryService.put(new URI("gorm://testEntity/list?name=C*"), "testEntity", "liza.user", "Liza's C people")
        selectionRepositoryService.put(new URI("gorm://testEntity/list?name=D*"), "testEntity", null, "Public D people")

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

        def uri = selectionRepositoryService.put(new URI("gorm://testEntity/list?name=B*"), 'testEntity', 'joe.user', 'Test Selection')
        def result = selectionService.select(uri, [:])
        assert result.size() == 2
        result.each {
            assert it.name.startsWith("B")
        }
    }

    void testTenants() {
        // Store same selection under 5 different tenants.
        5.times {tenant ->
            selectionRepositoryService.put(new URI("gorm://testEntity/list?name=A*"), "testEntity", "joe.user", "Joe's private A people", null, tenant)
        }

        // Each tenant should only se it's own selection.
        5.times {tenant ->
            assert selectionRepositoryService.list("testEntity", "joe.user", tenant).size() == 1
        }

        assert selectionRepositoryService.list("testEntity", "joe.user").size() == 5
    }
}
