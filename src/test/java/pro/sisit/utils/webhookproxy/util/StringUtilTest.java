package pro.sisit.utils.webhookproxy.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringUtilTest {

    @Test
    void testBuildGitHttpURL() {
        String ssh = "git@192.168.5.156.nip.io:etalon/jetalon_libs/webhook-proxy.git";
        String expected = "http://192.168.5.156.nip.io/etalon/jetalon_libs/webhook-proxy.git";

        Assertions.assertEquals(expected, StringUtil.buildGitURL(ssh, false));
    }

    @Test
    void testBuildGitHttpsURL() {
        String ssh = "git@192.168.5.156.nip.io:etalon/jetalon_libs/webhook-proxy.git";
        String expected = "https://192.168.5.156.nip.io/etalon/jetalon_libs/webhook-proxy.git";

        Assertions.assertEquals(expected, StringUtil.buildGitURL(ssh, true));
    }

}
