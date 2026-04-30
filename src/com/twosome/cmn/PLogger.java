/**
 * 파일명: PLogger.java <br>
 * 작성자: Wholesome-Gee  <br>
 * 생성일: 2026-04-23 <br>
 * 설　명: Log4j2 로거
 */

package com.twosome.cmn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Log4j2 로거
 */
public interface PLogger {
	final static Logger log = LogManager.getLogger(PLogger.class);
}
