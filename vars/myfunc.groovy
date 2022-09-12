import hudson.util.Secret
import groovy.json.JsonSlurper;

def call(String token, String addr) {
    def cmd = "curl --silent --header \"X-Vault-Token: ${token}\" -H \"X-Vault-Wrap-Ttl: 300s\" --request POST \"${addr}/v1/auth/pipeline/role/pipeline-role/secret-id\""
    /*def sout = new StringBuffer(), serr = new StringBuffer()
    def proc = cmd.execute()
    proc.consumeProcessOutput(sout, serr)
    proc.waitForOrKill(1000)
    println sout*/
    def resp_json = new ProcessBuilder( 'sh', '-c', cmd).redirectErrorStream(true).start().text
    
    // Parse json and obtain token
    def jsonSlurper = new JsonSlurper();
    def pipeline_vault_token = jsonSlurper.parseText(resp_json).wrap_info.token;
    return Secret.fromString​(pipeline_vault_token)
}