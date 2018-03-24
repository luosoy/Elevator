package com.luosoy.common.log.logback;

import org.apache.commons.lang3.StringUtils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluatorBase;
import com.luosoy.common.thread.ThreadIdUtil;


public class MDCEvaluator extends EventEvaluatorBase<ILoggingEvent> {

	@Override
	public boolean evaluate(ILoggingEvent arg0) throws NullPointerException, EvaluationException {
		String uuid = ThreadIdUtil.getThreadUUId();
		return !StringUtils.isEmpty(uuid);
	}

}
