package org.yuzz.xml;
import java.lang.annotation.Retention;


@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public  @interface AllowedTags {
	public String[] value();
}
