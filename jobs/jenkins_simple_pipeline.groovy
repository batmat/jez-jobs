pipelineJob('jenkins-pipeline-simple') {
    definition {
        cps {
            script(readFileFromWorkspace('resources/simple-pipeline.groovy'))
            sandbox()
        }
    }
}
