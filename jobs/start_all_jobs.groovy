freeStyleJob('_start-them-all') {
    logRotator(-1, 10)
    steps {
      systemGroovyCommand """
startServer = "admin"
startNote   = "bulk start"
cause = new hudson.model.Cause.RemoteCause(startServer, startNote)
jenkins.model.Jenkins.instance.items
                                   .findAll{ !it.name.equals('_start-them-all')}
                                   .each { job -> job.scheduleBuild(cause) }
      """
    }
}
