package com.example.fixba.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.slf4j.NDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {

	public static final String TRANSACTION_ID = "transaction-id";

	@Override
	public boolean preHandle(
					HttpServletRequest request,
					HttpServletResponse response,
					Object handler) {
		String traceID = this.getOrGenerateCorrelationId();
		MDC.put(TRANSACTION_ID, traceID);
		return true;
	}

	@Override
	public void afterCompletion(
					HttpServletRequest request,
					HttpServletResponse response,
					Object handler,
					Exception ex) {
		ContentCachingRequestWrapper requestWrapper
						= (ContentCachingRequestWrapper) request;
		if(response.getStatus() >= 400) {
			log.error("USER: " + request.getRemoteUser());
			log.error("Request id: " + MDC.get(TRANSACTION_ID));
			log.error( "Data: " + new String(requestWrapper.getContentAsByteArray()));
		}
	}

	private String getOrGenerateCorrelationId() {
		final String correlationId = MDC.get(TRANSACTION_ID);
		if(correlationId == null) {
			return UUID.randomUUID().toString();
		}
		return correlationId;
	}
}
