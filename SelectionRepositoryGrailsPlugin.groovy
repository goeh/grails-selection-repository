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
 */

class SelectionRepositoryGrailsPlugin {
    def version = "0.9.3"
    def grailsVersion = "2.0 > *"
    def dependsOn = [:]
    def loadAfter = ['selection']
    def pluginExcludes = [
        "grails-app/domain/test/TestEntity.groovy",
        "grails-app/views/error.gsp"
    ]
    def title = "Repository for Persistent Selections"
    def author = "Goran Ehrsson"
    def authorEmail = "goran@technipelago.se"
    def description = '''\
This plugin is an add-on to the 'selection' plugin (grails.org/plugin/selection) and let users save
selections to the database for later use.
The plugin provides SelectionRepositoryService to interact with selections and a Twitter Bootstrap user interface
for saving and managing saved selections.
'''
    def documentation = "https://github.com/goeh/grails-selection-repository"
    def license = "APACHE"
    def organization = [ name: "Technipelago AB", url: "http://www.technipelago.se/" ]
    def issueManagement = [ system: "github", url: "https://github.com/goeh/grails-selection-repository/issues" ]
    def scm = [ url: "https://github.com/goeh/grails-selection-repository" ]
}
