package com.venilry.web.pojo.generator;

import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

public class CategoryIdGenerator extends IdentityGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor s, Object obj) {
		Serializable id = s.getEntityPersister(null, obj).getClassMetadata().getIdentifier(obj, s);
		if (id != null && Integer.valueOf(id.toString()) > 0) {
			return id;
		} else {
			return super.generate(s, obj);
		}
	}

}