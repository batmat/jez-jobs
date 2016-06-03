node ('demo') {
  stage: "Clone"
  git 'https://github.com/jenkinsci/jenkins.git'

  stage "Install Maven"

  stage "Prebuild"
  withEnv(["PATH+MAVEN=${ tool 'maven-3' }/bin"]) {
    sh "mvn --batch-mode -V -e clean package -DskipTests dependency:go-offline -Dmaven.repo.local=${ pwd() }/.privaterepo"
  }
  stash includes: '**', name: 'prebuilt'
}

def tests = [:]
for (char letter='A';letter<'Z';++letter) {
  tests["$letter tests"] = {
    node ('demo') {
      unstash 'prebuilt'

      withEnv(["PATH+MAVEN=${ tool 'maven-3' }/bin"]) {
        sh "mvn --batch-mode -V -e package '-Dtest=$letter*Test' -DfailIfNoTests=false -Dmaven.repo.local=${ pwd() }/.privaterepo"
      }
    }
  }
}

stage "Unleash (Tests) Hell"
tests.failFast = true
parallel tests
