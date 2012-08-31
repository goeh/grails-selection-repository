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
    // the plugin version
    def version = "0.7.3"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.0 > *"
    // the other plugins this plugin depends on
    def dependsOn = [selection:'0.7 > *']
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/domain/test/TestEntity.groovy",
        "grails-app/views/error.gsp"
    ]

    def title = "Repository for persistent selections"
    def author = "Goran Ehrsson"
    def authorEmail = "goran@technipelago.se"
    def description = '''\
This plugin complements the 'selection' plugin and let users save
selections to the database for later use.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/selection-repository"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "Technipelago AB", url: "http://www.technipelago.se/" ]

    // Any additional developers beyond the author specified above.
    //    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "github", url: "https://github.com/goeh/grails-selection-repository/issues" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/goeh/grails-selection-repository" ]
}
