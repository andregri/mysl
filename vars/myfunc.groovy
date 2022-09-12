import hudson.util.Secret

def call(String token, String addr) {
    def cmd = "curl --silent --header \"X-Vault-Token: ${token}\" -H \"X-Vault-Wrap-Ttl: 300s\" --request POST \"${addr}/v1/auth/pipeline/role/pipeline-role/secret-id\""
    def sout = new StringBuffer(), serr = new StringBuffer()
    def proc = cmd.execute()
    proc.consumeProcessOutput(sout, serr)
    proc.waitForOrKill(1000)
    println sout
    def jsonSlurper = new JsonSlurper();
}