freeStyleJob('jenkins-freestyle-classic') {
    logRotator(-1, 10)
    label "demo"
    scm {
        github('jenkinsci/jenkins', 'master')
    }
    steps {
        maven {
          mavenInstallation('maven-3')
          goals('-B -V clean install -Dsurefire.useFile=false')
        }
    }
}
