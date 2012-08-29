package com.exchange.utils;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class ExportUtil {
	public static void  main(String[] args){
		Configuration config=new Configuration().configure();
		SchemaExport export=new SchemaExport(config);
		export.create(true, true);
	}
}
