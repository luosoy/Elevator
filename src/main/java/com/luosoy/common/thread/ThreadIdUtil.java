package com.luosoy.common.thread;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.UUID;


public final class ThreadIdUtil {

	/**
	 * The Constant UUID_NAME. handler_id
	 */
	public static final String UUID_NAME = "sequenceid";

	/**
	 * Instantiates a new thread id util.
	 */
	private ThreadIdUtil() {
	}

	/**
	 * Generate thread uu id.
	 */
	public static void generateThreadUUId() {
		UUID uuid = UUID.randomUUID();
		MDC.put(UUID_NAME, uuid.toString().replaceAll("-", ""));
	}

	/**
	 * 若外部传入UUID，则直接使用外部传入的UUID作为当前线程UUID。
	 * 
	 * @param uuid
	 */
	public static void setThreadUUId(String uuid) {
		MDC.put(UUID_NAME, uuid);
	}

	/**
	 * Gets the thread uu id.
	 * 
	 * @return the thread uu id
	 */
	public static String getThreadUUId() {
		return MDC.get(UUID_NAME);
	}

	/**
	 * Removes the thread uu id.
	 */
	public static void removeThreadUUId() {
		MDC.remove(UUID_NAME);
	}
	
	public static String orgThreadUUId() {
		String uuid = MDC.get(UUID_NAME);
		if (StringUtils.isBlank(uuid)) {
			uuid = UUID.randomUUID().toString().replaceAll("-", "");
		}
		MDC.put(UUID_NAME, uuid);
		return uuid;
	}
}
