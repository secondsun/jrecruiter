/*
*	http://www.jrecruiter.org
*
*	Disclaimer of Warranty.
*
*	Unless required by applicable law or agreed to in writing, Licensor provides
*	the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
*	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
*	including, without limitation, any warranties or conditions of TITLE,
*	NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
*	solely responsible for determining the appropriateness of using or
*	redistributing the Work and assume any risks associated with Your exercise of
*	permissions under this License.
*
*/
package org.jrecruiter.web.actions.admin;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Test the Struts 2 Admin Index Action
 *
 * @author Gunnar Hillert
 * @version $Id: EnumConverterTest.java 162 2008-02-26 04:55:29Z ghillert $
 */
public class IndexActionTest extends TestCase {

    public void testExecute() throws Exception {
    	IndexAction indexAction = new IndexAction();

    	String ret = indexAction.execute();
    	Assert.assertEquals("success", ret);
    }
}

