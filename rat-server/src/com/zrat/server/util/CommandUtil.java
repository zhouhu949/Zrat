/**  
 * Copyright (c) 2010-2012 Bolu Soft, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Bolu Soft,
 * Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Bolu.
 *
 * BOLU MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. ERRY SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 * Oringinal Author:Alex Zhou
 * Create on:Sep 10, 2010
 **/
package com.zrat.server.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandUtil {

	private static Process p = null;
	
	public static String executeCommand(String command) throws IOException, InterruptedException {
		
			p = Runtime.getRuntime().exec( command );
			p.waitFor();
			InputStream is = p.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuffer result = new StringBuffer();
			while (br.readLine() != null) {
				result.append(br.readLine());
				result.append("\n");
			}
			
			return result.toString();

		
			
	}
	
	public static void destroyCommand(){
		if (p!=null)
		p.destroy();
	}

}
