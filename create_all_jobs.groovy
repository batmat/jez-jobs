import groovy.json.*

def jsonSlurper = new JsonSlurper()

def repoList = jsonSlurper.parseText(readFileFromWorkspace("repos.github"))

repoList.each { repo ->
  def jobName = repo.name
  def gitUrl = repo.clone_url
  println "$jobName => $gitUrl"

  workflowJob(jobName) {
        scm {
            git {
              remote {
                  name('origin')
                  url(gitUrl)
              }
              createTag(false)
            }
        }
        definition {
            cps {
              script """
node {
   stage 'Checkout'

   git url: "$gitUrl"mak

   def mvnHome = tool 'maven-3'

   stage 'Build'

   sh "\${mvnHome}/bin/mvn --batch-mode -Dsurefire.useFile=false clean verify"
}
              """
            }
        }
    }
}
