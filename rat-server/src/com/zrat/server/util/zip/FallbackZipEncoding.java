/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.zrat.server.util.zip;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * A fallback ZipEncoding, which uses a java.io means to encode names.
 *
 * <p>This implementation is not favorable for encodings other than
 * utf-8, because java.io encodes unmappable character as question
 * marks leading to unreadable ZIP entries on some operating
 * systems.</p>
 * 
 * <p>Furthermore this implementation is unable to tell, whether a
 * given name can be safely encoded or not.</p>
 * 
 * <p>This implementation acts as a last resort implementation, when
 * neither {@link Simple8BitZipEnoding} nor {@link NioZipEncoding} is
 * available.</p>
 * 
 * <p>The methods of this class are reentrant.</p>
 */
class FallbackZipEncoding implements ZipEncoding {
    private final String charset;

    /**
     * Construct a fallback zip encoding, which uses the platform's
     * default charset.
     */
    public FallbackZipEncoding() {
        this.charset = null;
    }

    /**
     * Construct a fallback zip encoding, which uses the given charset.
     * 
     * @param charset The name of the charset or <code>null</code> for
     *                the platform's default character set.
     */
    public FallbackZipEncoding(String charset) {
        this.charset = charset;
    }

    /**
     * @see
     * org.apache.tools.zip.ZipEncoding#canEncode(java.lang.String)
     */
    public boolean canEncode(String name) {
        return true;
    }

    /**
     * @see
     * org.apache.tools.zip.ZipEncoding#encode(java.lang.String)
     */
    public ByteBuffer encode(String name) throws IOException {
        if (this.charset == null) {
            return ByteBuffer.wrap(name.getBytes());
        } else {
            return ByteBuffer.wrap(name.getBytes(this.charset));
        }
    }

    /**
     * @see
     * org.apache.tools.zip.ZipEncoding#decode(byte[])
     */
    public String decode(byte[] data) throws IOException {
        if (this.charset == null) {
            return new String(data);
        } else {
            return new String(data,this.charset);
        }
    }
}
