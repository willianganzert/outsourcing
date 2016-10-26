package br.com.webapp.jms;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import br.com.webapp.dao.DepartmentDao;
import br.com.webapp.model.Department;

@Component
public class MessageReceiver {
	static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

	private static final String ORDER_RESPONSE_QUEUE = "department-queue";
	
	
	
	@JmsListener(destination = ORDER_RESPONSE_QUEUE)
	public void receiveMessage(final Message<Department> message) throws JMSException {
		DepartmentDao dao = new DepartmentDao();
		try {
			dao.insertOrUpdate(message.getPayload());
		} catch (Exception e) {
			LOG.error("Impossible insert departament.",e);
		}		
	}
}
