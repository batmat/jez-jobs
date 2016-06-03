pipelineJob('jenkins-pipeline-naive-parallel') {
    definition {
        cps {
            script(readFileFromWorkspace('resources/naive-parallel.groovy'))
            sandbox()
        }
    }
}
