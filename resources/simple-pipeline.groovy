node ('demo') {
  stage: "Clone"
  git 'https://github.com/jenkinsci/jenkins.git'

  stage "Install Maven"
  def mavenTool = tool 'maven-3'

  stage "Build it!"

  withEnv(["PATH+MAVEN=$mavenTool/bin"]) {

      sh "mvn --batch-mode -V -U -e clean install -Dsurefire.useFile=false"

  }
}
