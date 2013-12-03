package pl.sasqoc.assetbuilder;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;


class LogFormatter extends Formatter {

	public String format(LogRecord rec) {
		StringBuffer buf = new StringBuffer(1000);
		// buf.append(calcDate(rec.getMillis()));
		buf.append(formatMessage(rec)+"\n");
				
		return buf.toString();   
	}
	

} 
