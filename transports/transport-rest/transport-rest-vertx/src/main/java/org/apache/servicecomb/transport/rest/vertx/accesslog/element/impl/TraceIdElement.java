/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.transport.rest.vertx.accesslog.element.impl;

import java.util.Map;

import org.apache.servicecomb.common.rest.RestProducerInvocation;
import org.apache.servicecomb.transport.rest.vertx.accesslog.AccessLogParam;
import org.apache.servicecomb.transport.rest.vertx.accesslog.element.AccessLogElement;
import org.springframework.util.StringUtils;

public class TraceIdElement implements AccessLogElement {

  private static final String TRACE_ID_NOT_FOUND = "-";

  @Override
  public String getFormattedElement(AccessLogParam accessLogParam) {
    Map<String, Object> data = accessLogParam.getRoutingContext().data();
    if (null == data) {
      return TRACE_ID_NOT_FOUND;
    }

    RestProducerInvocation restProducerInvocation = (RestProducerInvocation) data
        .get("servicecomb-rest-producer-invocation");
    if (null == restProducerInvocation) {
      return TRACE_ID_NOT_FOUND;
    }

    String traceId = restProducerInvocation.getContext("X-B3-TraceId");

    if (StringUtils.isEmpty(traceId)) {
      return TRACE_ID_NOT_FOUND;
    }
    return traceId;
  }
}
