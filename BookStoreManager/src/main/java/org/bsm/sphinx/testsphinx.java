/*
 * $Id: test.java 2876 2011-07-08 15:23:04Z tomat $
 */

package org.bsm.sphinx;

import java.util.*;

/**
 * Test class for sphinx API
 */
public class testsphinx {
    public static void main(String[] argv) throws SphinxException {
        String sphinxHost = "127.0.0.1";
        int sphinxPort = 9312;
        //创建客户端
        SphinxClient sphinxClient = new SphinxClient(sphinxHost, sphinxPort);
        //查询
        SphinxResult sphinxResult = sphinxClient.Query("gzc");
        List<Long> idList = new ArrayList<Long>();
        //检索后匹配到的内容
        for (SphinxMatch m : sphinxResult.matches) {
            //得到所到记录Id
            System.out.println(m.attrValues);
        }

    }
}

/*
 * $Id: test.java 2876 2011-07-08 15:23:04Z tomat $
 */
