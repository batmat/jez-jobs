freeStyleJob('_start-them-all') {
    logRotator(-1, 10)
    steps {
      systemGroovyCommand """
startServer = "admin"
startNote   = "bulk start"
cause = new hudson.model.Cause.RemoteCause(startServer, startNote)
Jenkins.instance.items.each { job -> job.scheduleBuild(cause) }
      """
    }
}
