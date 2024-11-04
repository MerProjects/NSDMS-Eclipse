/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package haj.com.ui;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

// TODO: Auto-generated Javadoc
/**
 * The Class NotifyResource.
 */
@PushEndpoint("/{userid}/")
public class NotifyResource {
        
    /**
     * On message.
     *
     * @param message the message
     * @return the string
     */
    @OnMessage(encoders = {JSONEncoder.class})
    public String onMessage(String message) {
        return message;
    }
 /*   public FacesMessage onMessage(FacesMessage message) {
        return message;
    }
*/
}
