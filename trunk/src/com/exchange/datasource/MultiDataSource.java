package com.exchange.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;



public class MultiDataSource extends AbstractRoutingDataSource{
	private static final Logger log=LoggerFactory.getLogger(MultiDataSource.class);
	@Override
	protected Object determineCurrentLookupKey() {
		String type=DataSourceContext.getType();
		log.debug(type);
		return type;
	}

}
