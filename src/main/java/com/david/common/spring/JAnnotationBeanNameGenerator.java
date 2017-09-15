package com.david.common.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * Custom Spring annotation bean naming policy tool class
 * @author David
 */
public class JAnnotationBeanNameGenerator extends
		AnnotationBeanNameGenerator {

	@Override
	protected String buildDefaultBeanName(BeanDefinition definition) {
		return definition.getBeanClassName();
	}
	
}
