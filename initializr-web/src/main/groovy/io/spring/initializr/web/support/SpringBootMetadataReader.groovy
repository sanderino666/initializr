/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.initializr.web.support

import groovy.json.JsonSlurper
import io.spring.initializr.metadata.DefaultMetadataElement

import org.springframework.web.client.RestTemplate

/**
 * Reads metadata from the main spring.io website. This is a stateful
 * service: creates a new instance whenever you need to refresh the
 * content.
 *
 * @author Stephane Nicoll
 * @since 1.0
 */
class SpringBootMetadataReader {

	private final def content = """{
  "id": "Blueriq-initializer",
  "name": "Blueriq initializer",
  "repoUrl": "https://github.com/spring-projects/spring-boot",
  "siteUrl": "http://projects.spring.io/spring-boot",
  "category": "active",
  "stackOverflowTags": "Blueriq-initializer",
  "projectReleases": [
    {
      "releaseStatus": "SNAPSHOT",
      "refDocUrl": "http://docs.spring.io/spring-boot/docs/2.0.0.BUILD-SNAPSHOT/reference/htmlsingle/",
      "apiDocUrl": "http://docs.spring.io/spring-boot/docs/2.0.0.BUILD-SNAPSHOT/api/",
      "groupId": "com.Blueriq",
      "artifactId": "blueriq-initializer",
      "repository": {
        "id": "spring-snapshots",
        "name": "Spring Snapshots",
        "url": "https://repo.spring.io/libs-snapshot",
        "snapshotsEnabled": true
      },
      "generalAvailability": false,
      "preRelease": false,
      "versionDisplayName": "10.0.0",
      "current": false,
      "snapshot": true,
      "version": "10.0.0.SNAPSHOT"
    }
  ],
  "aggregator": false,
  "stackOverflowTagList": [
    "spring-boot"
  ]
}
"""

	/**
	 * Parse the content of the metadata at the specified url
	 */
	SpringBootMetadataReader(RestTemplate restTemplate, String url) {
		//def content = restTemplate.getForObject(url, String.class)
		//FIXME: RdH: getting the json from url instead of constant
		this.content = new JsonSlurper().parseText(content)
	}

	/**
	 * Return the boot versions parsed by this instance.
	 */
	List<DefaultMetadataElement> getBootVersions() {
		content.projectReleases.collect {
			def version = new DefaultMetadataElement()
			version.id = it.version
			def name = it.versionDisplayName
			version.name = (it.snapshot ? name + ' (SNAPSHOT)' : name)
			version.setDefault(it.current)
			version
		}
	}

}
